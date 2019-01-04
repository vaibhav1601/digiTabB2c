package z_aksys.solutions.digiappequitybb.Receiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class AlarmReceiver extends BroadcastReceiver {

    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent background = new Intent(context, BackgroundService.class);
        context.startService(background);
    }


    public static class BackgroundService extends Service {


        private boolean isRunning;
        private Context context;
        private Thread backgroundThread;
        private Runnable myTask = new Runnable() {
            public void run() {
                Log.d("service>>>", "service>>>");
                stopSelf();
            }
        };

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            this.context = this;
            this.isRunning = false;
            this.backgroundThread = new Thread(myTask);
        }

        @Override
        public void onDestroy() {
            this.isRunning = false;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            if (!this.isRunning) {
                this.isRunning = true;
                this.backgroundThread.start();
            }
            return START_STICKY;
        }

    }


}
