package z_aksys.solutions.digiappequitybb.Activity;

import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import z_aksys.solutions.digiappequitybb.API.PitchServices;
import z_aksys.solutions.digiappequitybb.API.RetrofitClient;
import z_aksys.solutions.digiappequitybb.App;
import z_aksys.solutions.digiappequitybb.Dao.FeedbackModelDao;
import z_aksys.solutions.digiappequitybb.Dao.QuestionModelDao;
import z_aksys.solutions.digiappequitybb.Dao.ShareDao;
import z_aksys.solutions.digiappequitybb.Database.PitchRoomDatabase;
import z_aksys.solutions.digiappequitybb.Fragment.FaqFragment;
import z_aksys.solutions.digiappequitybb.Fragment.GoalFragment;
import z_aksys.solutions.digiappequitybb.Fragment.LearnFragment;
import z_aksys.solutions.digiappequitybb.Fragment.NewsFragment;
import z_aksys.solutions.digiappequitybb.Fragment.PitchHomeFragment;
import z_aksys.solutions.digiappequitybb.Fragment.ShareFragment;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Receiver.AlarmReceiver;
import z_aksys.solutions.digiappequitybb.Receiver.NetworkChangeReceiver;
import z_aksys.solutions.digiappequitybb.Receiver.ScreenReceiver;
import z_aksys.solutions.digiappequitybb.Request.FeedBackRequest;
import z_aksys.solutions.digiappequitybb.Request.QuestionRequest;
import z_aksys.solutions.digiappequitybb.Request.ShareRequest;
import z_aksys.solutions.digiappequitybb.Response.FeedBackResponse;
import z_aksys.solutions.digiappequitybb.Response.QuestionResponse;
import z_aksys.solutions.digiappequitybb.Response.ShareMarketResponse;
import z_aksys.solutions.digiappequitybb.Response.ShareResponse;
import z_aksys.solutions.digiappequitybb.custom.UnsafeOkHttpClient;
import z_aksys.solutions.digiappequitybb.custom.ViewPagerNoSwipe;
import z_aksys.solutions.digiappequitybb.model.Feedback;
import z_aksys.solutions.digiappequitybb.model.QuestionStatus;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

import static z_aksys.solutions.digiappequitybb.App.getContext;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appKey;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appSec;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static PitchRoomDatabase pitchRoomDatabase;
    public static QuestionModelDao questionModelDao;
    public static FeedbackModelDao feedbackModelDao;
    public static ShareDao shareDao;
    public static List<QuestionStatus> questionStatusList;
    public static List<Feedback> feedbackList;
    public static QuestionRequest questionRequest;
    public static List<ShareRequest> shareList;
    public static FeedBackRequest feedBackRequest;
    public static PitchServices pitchServices;
    static TextView tv_check_connection;
    private TabLayout tabLayout;
    private ViewPagerNoSwipe viewPager;
    private BroadcastReceiver mReceiver;
    private Context context;
    private TextView tabOne, tabTwo, tabThree, tabFour, tabFive, tabSix, tabSeven;
    private AppCompatImageView btn_goal;
    private BroadcastReceiver mNetworkReceiver;
    private TextView niftyValue, niftyup, tv_sunsex, sensexValue, sunsexyup, tv_gold, goldValue, goldyup, tv_Usder, usdrValue, usdrup;
    private static Boolean isSms, isWhatsApp, isEmil;
    private PowerManager.WakeLock wl;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("pitch application");
        // actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

        this.context = this;
        questionStatusList = new ArrayList<>();
        feedbackList = new ArrayList<>();
        shareList = new ArrayList<>();
        Intent alarm = new Intent(this.context, AlarmReceiver.class);
        boolean alarmRunning = (PendingIntent.getBroadcast(this.context, 0, alarm, PendingIntent.FLAG_NO_CREATE) != null);
        if (alarmRunning == false) {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this.context, 0, alarm, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 1800000, pendingIntent);
        }
        btn_goal = (AppCompatImageView) findViewById(R.id.btn_goal);
        viewPager = (ViewPagerNoSwipe) findViewById(R.id.viewpager);
        tv_check_connection = (TextView) findViewById(R.id.tv_check_connection);
        niftyValue = (TextView) findViewById(R.id.niftyValue);
        niftyup = (TextView) findViewById(R.id.niftyup);
        tv_sunsex = (TextView) findViewById(R.id.tv_sunsex);
        sensexValue = (TextView) findViewById(R.id.sensexValue);
        sunsexyup = (TextView) findViewById(R.id.sunsexyup);
        tv_gold = (TextView) findViewById(R.id.tv_gold);
        goldValue = (TextView) findViewById(R.id.goldValue);
        goldyup = (TextView) findViewById(R.id.goldyup);
        tv_Usder = (TextView) findViewById(R.id.tv_Usder);
        usdrValue = (TextView) findViewById(R.id.usdrValue);
        usdrup = (TextView) findViewById(R.id.usdrup);
        linearLayout = (LinearLayout) findViewById(R.id.ll_main_Share);
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Wake lock");
        wl.acquire();
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        pitchServices = RetrofitClient.getInstance().getApi();
        setupTabIcons();


        if (AngelPitchUtil.checkConnection(getApplicationContext())) {

            callServicesShareMarket();

        } else {
            Toast.makeText(getApplicationContext(), "please connect the internet", Toast.LENGTH_LONG).show();
        }


        btn_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoalFragment goalFragment = new GoalFragment();
                goalFragment.show(getSupportFragmentManager(), "goal");

            }
        });

        mNetworkReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastForNougat();


    }

    private void callServicesShareMarket() {

        OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://compliance.angelbroking.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        PitchServices restAPI = retrofit.create(PitchServices.class);

        Call<ShareMarketResponse> call = restAPI.getShareMarket();
        call.enqueue(new Callback<ShareMarketResponse>() {
            @Override
            public void onResponse(Call<ShareMarketResponse> call, Response<ShareMarketResponse> response) {

                if (response.isSuccessful()) {
                    setData(response.body().getData().getExchangeData());
                }

            }

            @Override
            public void onFailure(Call<ShareMarketResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

    private void setData(ArrayList<ShareMarketResponse.ExchangeData> exchangeData) {

        if (!ObjectUtils.isEmpty(exchangeData)) {

            try {
                tv_Usder.setText(exchangeData.get(0).getSymbol());
                usdrValue.setText(exchangeData.get(0).getCurrValue());
                tv_sunsex.setText(exchangeData.get(1).getSymbol());
                sensexValue.setText(exchangeData.get(1).getCurrValue());
                tv_check_connection.setText(exchangeData.get(2).getSymbol());
                niftyValue.setText(exchangeData.get(2).getCurrValue());
                tv_gold.setText(exchangeData.get(3).getSymbol());
                goldValue.setText(exchangeData.get(3).getCurrValue());
            }catch (Exception e){

            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

       /* if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
             super.onBackPressed();
           // finish();
        }else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {

            Log.d("Count","Count"+getSupportFragmentManager().getBackStackEntryCount());

        }else{
            getSupportFragmentManager().popBackStack();

        }
*/


       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }

    /**
     * Adding custom view to tab
     */


    public static void dialog(boolean value) {

        if (value) {
            tv_check_connection.setText("We are back !!!");
            tv_check_connection.setBackgroundColor(Color.GREEN);
            tv_check_connection.setTextColor(Color.WHITE);

            new AsyncTaskQuestionResult(App.getContext()).execute();

            new AsyncFeedbackResult(App.getContext()).execute();

            new AsyncShareResult(App.getContext()).execute();

        } else {
            tv_check_connection.setVisibility(View.VISIBLE);
            tv_check_connection.setText("Could not Connect to internet");
            tv_check_connection.setBackgroundColor(Color.RED);
            tv_check_connection.setTextColor(Color.WHITE);
        }
    }

    private static void callServicesFeedback(List<Feedback> feedbackList) {

        List<FeedBackRequest> feedBackRequestList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(feedbackList)) {
            for (int i = 0; i < feedbackList.size(); i++) {
                feedBackRequest = new FeedBackRequest();
                feedBackRequest.setEmp_no(feedbackList.get(i).getEmp_number());
                feedBackRequest.setContact(ObjectUtils.getIntFromString(feedbackList.get(i).getMobile_number()));
                feedBackRequest.setEmail(feedbackList.get(i).getEmail());
                feedBackRequest.setCustomer_name(feedbackList.get(i).getCustomer_name());
                feedBackRequest.setWhatsapp_no(feedbackList.get(i).getComments());

                feedBackRequestList.add(feedBackRequest);
            }

            JSONArray arr_strJson2 = new JSONArray(feedBackRequestList);


            Call<FeedBackResponse> call = pitchServices.postAllFeedBack("application/json", appKey, appSec, feedBackRequestList);
            call.enqueue(new Callback<FeedBackResponse>() {
                @Override
                public void onResponse(Call<FeedBackResponse> call, Response<FeedBackResponse> response) {

                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "sucessfeedback>>>>>>>>>>" + response.message(), Toast.LENGTH_LONG).show();

                        new AsyncTaskFeedBackDelete(getContext()).execute();

                    }

                }

                @Override
                public void onFailure(Call<FeedBackResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();


                }
            });


        }
    }

    private static void callServices(List<QuestionStatus> questionStatusList) {
        JSONArray jsonArray = new JSONArray();

//        showProgress();
        List<QuestionRequest> questionRequestList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(questionStatusList)) {
            for (int i = 0; i < questionStatusList.size(); i++) {
                questionRequest = new QuestionRequest();
                questionRequest.setEmp_no("test");
                questionRequest.setTopic_id(ObjectUtils.getIntFromString(questionStatusList.get(i).getTopic_id()));
                questionRequest.setLesson_id(ObjectUtils.getIntFromString(questionStatusList.get(i).getLesson_id()));
                questionRequest.setIs_completed(ObjectUtils.getIntFromString(questionStatusList.get(i).getStatus()));
                questionRequest.setScore(questionStatusList.get(i).getScore());
                questionRequestList.add(questionRequest);
                System.out.println("offline question request>>" + questionRequestList.size());


            }


            Call<QuestionResponse> myResponsecall = pitchServices.postAllQuestion("application/json", appKey, appSec, questionRequestList);
            myResponsecall.enqueue(new Callback<QuestionResponse>() {
                @Override
                public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {

                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "sucess" + response.message(), Toast.LENGTH_LONG).show();

                        new AsyncTaskQuestionDelete(getContext()).execute();

                    }

                }

                @Override
                public void onFailure(Call<QuestionResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();


                }
            });


        } else {
            tv_check_connection.setVisibility(View.GONE);
        }


    }

    private static void callServicesShare(List<ShareRequest> shareList) {

        if (!ObjectUtils.isEmpty(shareList)) {

            retrofit2.Call<ShareResponse> call = pitchServices.postShare("application/json", appKey, appSec, shareList);
            call.enqueue(new Callback<ShareResponse>() {
                @Override
                public void onResponse(Call<ShareResponse> call, Response<ShareResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), " " + response.message(), Toast.LENGTH_LONG).show();

                        new AsyncShareResultDelete(getContext()).execute();

                    }

                }

                @Override
                public void onFailure(Call<ShareResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();


                }
            });

        }


    }


    private void setupTabIcons() {

        tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("News");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab1, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);
        //tabLayout.getTabAt(0).setIcon(R.drawable.ic_news_f);

        tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Pitch");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab2, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

       /* TextView tabCalc = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabCalc.setText("Calculators");
        tabCalc.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.baseline_keyboard_white_18dp, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabCalc);
*/
        tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("Share");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab3, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFour.setText("Learn");
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab4, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFour);


        tabFive = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFive.setText("FAQs");
        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab5, 0, 0);
        tabLayout.getTabAt(4).setCustomView(tabFive);


        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().toString().equalsIgnoreCase("News")) {
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getText().toString().equalsIgnoreCase("News")) {
                    linearLayout.setVisibility(View.GONE);
                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getText().toString().equalsIgnoreCase("News")) {
                    linearLayout.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), 4);
        adapter.addFrag(new NewsFragment(), "News");
        adapter.addFrag(new PitchHomeFragment(), "Pitch");
        adapter.addFrag(new ShareFragment(), "Share");
        adapter.addFrag(new LearnFragment(), "Learn");
        adapter.addFrag(new FaqFragment(), "FAQs");

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Fragment getCurrentFragment() {
        String fragmentTag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.btnProceedPolicy);
        return currentFragment;
    }

    private void registerBroadcastReceiver() {

        final IntentFilter theFilter = new IntentFilter();
        /** System Defined Broadcast */
        theFilter.addAction(Intent.ACTION_SCREEN_ON);
        theFilter.addAction(Intent.ACTION_SCREEN_OFF);
        theFilter.addAction(Intent.ACTION_USER_PRESENT);

        BroadcastReceiver screenOnOffReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String strAction = intent.getAction();

                KeyguardManager myKM = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
                if (strAction.equals(Intent.ACTION_USER_PRESENT) || strAction.equals(Intent.ACTION_SCREEN_OFF) || strAction.equals(Intent.ACTION_SCREEN_ON))
                    if (myKM.inKeyguardRestrictedInputMode()) {
                        System.out.println("Screen off " + "LOCKED");
                    } else {
                        System.out.println("Screen off " + "UNLOCKED");
                    }

            }
        };

        getApplicationContext().registerReceiver(screenOnOffReceiver, theFilter);
    }

    @Override
    protected void onPause() {
        // WHEN THE SCREEN IS ABOUT TO TURN OFF
        if (ScreenReceiver.isScreen) {
            // THIS IS THE CASE WHEN ONPAUSE() IS CALLED BY THE SYSTEM DUE TO A SCREEN STATE CHANGE
            System.out.println("SCREEN TURNED OFF");
        } else {
            // THIS IS WHEN ONPAUSE() IS CALLED WHEN THE SCREEN STATE HAS NOT CHANGED
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        // ONLY WHEN SCREEN TURNS ON
        if (!ScreenReceiver.isScreen) {
            // THIS IS WHEN ONRESUME() IS CALLED DUE TO A SCREEN STATE CHANGE
            System.out.println("SCREEN TURNED ON");


            //  AngelPitchUtil.pitchContune(getApplicationContext());

            /*final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    AngelPitchUtil.pitchContune(getApplicationContext());
                }
            }, 200);*/


        } else {
            // THIS IS WHEN ONRESUME() IS CALLED WHEN THE SCREEN STATE HAS NOT CHANGED
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    AngelPitchUtil.pitchContune(MainActivity.this);
                }
            }, 200);

        }
        super.onResume();
    }

    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
        wl.release();
    }

    private static class AsyncTaskQuestionResult extends AsyncTask<String, String, Boolean> {


        Context context;

        public AsyncTaskQuestionResult(Context context) {

            this.context = context;


        }


        @Override
        protected Boolean doInBackground(String... strings) {


            pitchRoomDatabase = PitchRoomDatabase.getDatabase(getContext());
            questionModelDao = pitchRoomDatabase.questionModelDao();
            questionStatusList = questionModelDao.getAllQuestionStates();
            System.out.println("offline question list>>>" + questionStatusList.size());


            return true;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            // execution of result of Long time consuming operation
            //  callAdaptor();

            callServices(questionStatusList);
            tv_check_connection.setVisibility(View.GONE);
        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {


        }


    }

    private static class AsyncFeedbackResult extends AsyncTask<String, String, Boolean> {


        Context context;


        public AsyncFeedbackResult(Context context) {

            this.context = context;


        }


        @Override
        protected Boolean doInBackground(String... strings) {


            pitchRoomDatabase = PitchRoomDatabase.getDatabase(getContext());
            feedbackModelDao = pitchRoomDatabase.feedbackModelDao();
            feedbackList = feedbackModelDao.getAllFeedback();

            System.out.println("offline feedbackList list>>>" + feedbackList.size());


            return true;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            // execution of result of Long time consuming operation
            //  callAdaptor();

            callServicesFeedback(feedbackList);

        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {


        }


    }

    private static class AsyncTaskQuestionDelete extends AsyncTask<String, String, Boolean> {

        Context context;


        public AsyncTaskQuestionDelete(Context context) {

            this.context = context;

        }


        @Override
        protected Boolean doInBackground(String... strings) {


            pitchRoomDatabase = PitchRoomDatabase.getDatabase(getContext());
            questionModelDao = pitchRoomDatabase.questionModelDao();
            int questionid = questionModelDao.deleteQuestionStatus();

            System.out.println("deletedquestionid" + questionid);


            return true;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            // execution of result of Long time consuming operation
            //  callAdaptor();
            tv_check_connection.setVisibility(View.GONE);
        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {


        }


    }

    private static class AsyncTaskFeedBackDelete extends AsyncTask<String, String, Boolean> {

        Context context;


        public AsyncTaskFeedBackDelete(Context context) {

            this.context = context;

        }


        @Override
        protected Boolean doInBackground(String... strings) {


            pitchRoomDatabase = PitchRoomDatabase.getDatabase(getContext());
            feedbackModelDao = pitchRoomDatabase.feedbackModelDao();
            int feedbackid = feedbackModelDao.deleteFeedbackStatus();

            System.out.println("deleteFacebbokid" + feedbackid);


            return true;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            // execution of result of Long time consuming operation
            //  callAdaptor();
            tv_check_connection.setVisibility(View.GONE);
        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {


        }


    }

    private static class AsyncShareResult extends AsyncTask<String, String, Boolean> {


        Context context;

        public AsyncShareResult(Context context) {

            this.context = context;


        }


        @Override
        protected Boolean doInBackground(String... strings) {


            pitchRoomDatabase = PitchRoomDatabase.getDatabase(getContext());
            shareDao = pitchRoomDatabase.shareDao();
            shareList = shareDao.getAllShareDetails();

            System.out.println("offline feedbackList list>>>" + feedbackList.size());


            return true;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            // execution of result of Long time consuming operation
            //  callAdaptor();

            callServicesShare(shareList);

        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {


        }


    }

    private static class AsyncShareResultDelete extends AsyncTask<String, String, Boolean> {

        Context context;


        public AsyncShareResultDelete(Context context) {

            this.context = context;

        }


        @Override
        protected Boolean doInBackground(String... strings) {


            pitchRoomDatabase = PitchRoomDatabase.getDatabase(getContext());
            shareDao = pitchRoomDatabase.shareDao();
            int shareid = shareDao.deleteAllShareDetails();

            System.out.println("deleteAllShareDetails" + shareid);


            return true;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            // execution of result of Long time consuming operation
            //  callAdaptor();
            tv_check_connection.setVisibility(View.GONE);
        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {


        }


    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager manager, int i) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            // Toast.makeText(MainActivity.this, "Selected page position: " + position, Toast.LENGTH_SHORT).show();

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
