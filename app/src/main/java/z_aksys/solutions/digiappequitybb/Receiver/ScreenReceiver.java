package z_aksys.solutions.digiappequitybb.Receiver;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import z_aksys.solutions.digiappequitybb.Activity.MainActivity;

public class ScreenReceiver extends BroadcastReceiver {

    public static boolean isScreen;
    public Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.v("$$$$$$", "In Method:  ACTION_SCREEN_OFF");
            // onPause() will be called.
            isScreen = false;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Log.v("$$$$$$", "In Method:  ACTION_SCREEN_ON");
            //onResume() will be called.

            //Better check for whether the screen was already locked
            // if locked, do not take any resuming action in onResume()
          /*  Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);*/
            //Suggest you, not to take any resuming action here.
            //isScreen=true;
        } else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
            Log.v("$$$$$$", "In Method:  ACTION_USER_PRESENT");
            //Handle resuming events
            isScreen = true;

           /* final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    AngelPitchUtil.pitchContune(App.getContext());
                }
            }, 200);
*/


            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }


    }


    public static class YourServiceRestarterBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            context.startService(new Intent(context, ScreenReceiver.UpirService.class));
        }
    }


    public static class UpirService extends Service {

        Thread thread;
        private boolean isRunning;
        private Context context;
        private Runnable myTask = new Runnable() {
            public void run() {
                Log.d("running thread", "running thread");
                // AngelPitchUtil.pitchContune(App.getContext());


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
            this.thread = new Thread(myTask);


            /*new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d(" your previous pitch"," your previous pitch");
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setTitle("Do you want to continue with your previous pitch ?")
                            .setCancelable(false)
                            .setPositiveButton("Ok" ,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    final AlertDialog alert = builder.create();
                    alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    alert.show();
                }
            }, 500);
          //  startActivity(new Intent(this, MainActivity.class));
          //  finish();
        }*/

        }

        @Override
        public void onDestroy() {
            this.isRunning = false;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            if (!this.isRunning) {
                this.isRunning = true;
                this.thread.start();
                //new Handler().post()
            }
            return START_STICKY;
        }

    }


}


