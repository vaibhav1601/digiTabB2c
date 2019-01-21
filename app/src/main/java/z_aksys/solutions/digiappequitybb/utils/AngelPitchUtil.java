package z_aksys.solutions.digiappequitybb.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import z_aksys.solutions.digiappequitybb.API.PitchServices;
import z_aksys.solutions.digiappequitybb.API.RetrofitClient;
import z_aksys.solutions.digiappequitybb.App;
import z_aksys.solutions.digiappequitybb.Dao.ShareDao;
import z_aksys.solutions.digiappequitybb.Database.PitchRoomDatabase;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Request.ShareRequest;
import z_aksys.solutions.digiappequitybb.Response.LearnResponse;
import z_aksys.solutions.digiappequitybb.Response.ShareResponse;
import z_aksys.solutions.digiappequitybb.model.Share;

import static z_aksys.solutions.digiappequitybb.App.getContext;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appKey;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appSec;

public class AngelPitchUtil {

    public static String answer = "";
    public static String organswer = "";
    public static PitchServices pitchServices;
    public static Boolean isConnected;


    public static String getVideoId(String videoUrl) {
        String reg = "(?:youtube(?:-nocookie)?\\.com\\/(?:[^\\/\\n\\s]+\\/\\S+\\/|(?:v|e(?:mbed)?)\\/|\\S*?[?&]v=)|youtu\\.be\\/)([a-zA-Z0-9_-]{11})";
        Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(videoUrl);

        if (matcher.find())
            return matcher.group(1);
        return null;
    }


    public static int getScreenOrientation(Context context) {
        Display getOrient = ((Activity) context).getWindowManager().getDefaultDisplay();
        int orientation = Configuration.ORIENTATION_UNDEFINED;
        if (getOrient.getWidth() == getOrient.getHeight()) {
            orientation = Configuration.ORIENTATION_SQUARE;
        } else {

            if (getOrient.getWidth() < getOrient.getHeight()) {
                orientation = Configuration.ORIENTATION_PORTRAIT;
            } else {
                orientation = Configuration.ORIENTATION_LANDSCAPE;
            }

        }
        return orientation;
    }



/*
    public   static  boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) App.getContext().getSystemService(CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
*/


    public static void pitchContune(Context context) {

        final Dialog alert = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertDlgView = inflater.inflate(R.layout.pitchalert, null);
        AppCompatTextView txt_heading = (alertDlgView).findViewById(R.id.txt_heading);
        final TextView btn_later = (alertDlgView).findViewById(R.id.btn_yes);
        final TextView btn_retry = (alertDlgView).findViewById(R.id.btn_no);
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.setContentView(alertDlgView);
        alert.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        alert.setCanceledOnTouchOutside(true);
        alert.show();


        btn_later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });


        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });


    }

    public static void showQuestion(Context context) {


        final Dialog alert = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertDlgView = inflater.inflate(R.layout.question_score, null);
        AppCompatTextView txt_heading = (alertDlgView).findViewById(R.id.txt_heading);
        final TextView txtScore = (alertDlgView).findViewById(R.id.txtScore);
        final TextView btn_later = (alertDlgView).findViewById(R.id.btn_later);
        final TextView btn_retry = (alertDlgView).findViewById(R.id.btn_retry);
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.setContentView(alertDlgView);
        alert.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        alert.setCanceledOnTouchOutside(true);
        alert.show();


        btn_later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });


        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });


    }


    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();


    }


    public static boolean isHaveSpecialCharacter(String name) {
        String specialCharacters = "!#$%&()*+,-./:;<=>?@[]^_`{|}~0123456789";
        String[] strlCharactersArray = new String[name.length()];
        for (int i = 0; i < name.length(); i++) {
            strlCharactersArray[i] = Character
                    .toString(name.charAt(i));
        }
        //now  strlCharactersArray[i]=[A, d, i, t, y, a]
        int count = 0;
        for (int i = 0; i < strlCharactersArray.length; i++) {
            if (specialCharacters.contains(strlCharactersArray[i])) {
                count++;
            }

        }

        return name != null && count == 0;
    }

    public final static boolean isValidMobile(CharSequence target) {
        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]+", target)) {
            check = target.length() >= 6 && target.length() <= 13;
        } else {
            check = false;
        }
        return check;
    }


    public final static boolean ismobilenumberValid(String number) {
        return !TextUtils.isEmpty(number) && number.matches("\\d{10}");

    }


    public static void ShowDialog(Context context, String news_id, String type, String source_link) {

        pitchServices = RetrofitClient.getInstance().getApi();
        PitchRoomDatabase pitchRoomDatabase;
        ShareDao shareDao;
        final Dialog alert = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertDlgView = inflater.inflate(R.layout.showdialogs, null);
        AppCompatTextView txt_heading = (alertDlgView).findViewById(R.id.txt_heading);
        final EditText et_emailid = (alertDlgView).findViewById(R.id.et_emailid);
        final EditText et_whatsApp = (alertDlgView).findViewById(R.id.et_whatsApp);
        final EditText et_sms = (alertDlgView).findViewById(R.id.et_sms);
        final Button btn_email = (alertDlgView).findViewById(R.id.btn_email_send);
        final Button btn_sms = (alertDlgView).findViewById(R.id.btn_sms_send);
        final Button btn_whatsapp = (alertDlgView).findViewById(R.id.btn_whatsAppsned);
        final AppCompatImageView close = (alertDlgView).findViewById(R.id.ic_remove_icon);
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.setContentView(alertDlgView);
        alert.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        alert.setCanceledOnTouchOutside(true);
        alert.show();

        isConnected = AngelPitchUtil.checkConnection(App.getContext());

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });


        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected) {

                    if (!AngelPitchUtil.isValidEmail(et_emailid.getText())) {
                        Toast.makeText(App.getContext(), "Please Enter Valid emailId", Toast.LENGTH_LONG).show();
                    } else {
                        callWebServices(news_id, type, source_link, et_emailid.getText().toString(), "", "");

                        et_emailid.setText("");
                        // alert.dismiss();
                    }

                } else {

                    new InsertShareTask(App.getContext(), news_id, type, source_link, et_emailid.getText().toString(), "", "").execute();
                    // alert.dismiss();
                    et_emailid.setText("");
                }


            }
        });


        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected) {

                    if (!AngelPitchUtil.ismobilenumberValid(et_sms.getText().toString())) {

                        Toast.makeText(App.getContext(), "Please Enter Valid mobile ", Toast.LENGTH_LONG).show();

                        et_sms.setText("");

                    } else {
                        callWebServices(news_id, type, source_link, "", et_sms.getText().toString(), "");
                        //alert.dismiss();

                    }

                } else {
                    new InsertShareTask(App.getContext(), news_id, type, source_link, " ", et_sms.getText().toString(), "").execute();
                    //alert.dismiss();
                    et_sms.setText("");

                }


            }
        });


        btn_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected) {

                    if (!AngelPitchUtil.isValidMobile(et_whatsApp.getText())) {

                        Toast.makeText(App.getContext(), "Please Enter Valid mobile ", Toast.LENGTH_LONG).show();

                    } else {
                        callWebServices(news_id, type, source_link, "", "", et_whatsApp.getText().toString());
                        alert.dismiss();

                    }


                } else {
                    new InsertShareTask(App.getContext(), news_id, type, source_link, "", "", et_whatsApp.getText().toString()).execute();
                    alert.dismiss();

                }


            }
        });

    }

    private static void callWebServices(String id, String type, String link, String email, String sms, String whatsApp) {

        ShareRequest shareRequest = new ShareRequest();
        List<ShareRequest> shareRequests = new ArrayList<>();
        shareRequest.setEmp_no("test");
        shareRequest.setEmail(email);
        shareRequest.setContact(sms);
        shareRequest.setWhatsapp_no(whatsApp);
        shareRequest.setId(id);
        shareRequest.setType(type);
        shareRequest.setLink(link);
        shareRequests.add(shareRequest);

        retrofit2.Call<ShareResponse> call = pitchServices.postShare("application/json", appKey, appSec, shareRequests);
        call.enqueue(new Callback<ShareResponse>() {
            @Override
            public void onResponse(Call<ShareResponse> call, Response<ShareResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), " " + response.message(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ShareResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();


            }
        });

    }

    /**
     * CHECK WHETHER INTERNET CONNECTION IS AVAILABLE OR NOT
     */
    public static boolean checkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

        if (activeNetworkInfo != null) { // connected to the internet
            // Toast.makeText(context, activeNetworkInfo.getTypeName(), Toast.LENGTH_SHORT).show();

            // connected to the mobile provider's data plan
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else return activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return false;
    }

    public static String toJson(Object object) {
        if (object != null) {
            Gson gson = new Gson();
            String json = gson.toJson(object);
            return json;
        } else {
            return "";
        }
    }

    public static <T> List<T> deSerializeResponseList(String json, Class<T> clazz) {
        List<T> list = null;
        try {
            list = getGsonObject().fromJson(json, new ListWithElements<T>(clazz));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Gson getGsonObject() {
        return new GsonBuilder()
//                .registerTypeAdapter(long.class, new LongTypeAdapter())
//                .registerTypeAdapter(double.class, new DoubleTypeAdapter())
//                .registerTypeAdapter(float.class, new FloatTypeAdapter())
                .registerTypeAdapter(int.class, new IntegerTypeAdapter())
                .create();
    }

    public static void showQuestionsDialog(final Context context, final LearnResponse.questions questions) {

        if (ObjectUtils.isNotNull(questions)) {

            final Dialog alert = new Dialog(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View alertDlgView = inflater.inflate(R.layout.dialog_questions, null);
            AppCompatTextView txt_heading = (alertDlgView).findViewById(R.id.txt_heading);
            final AppCompatTextView option_1 = (alertDlgView).findViewById(R.id.option_1);
            final AppCompatTextView option_2 = (alertDlgView).findViewById(R.id.option_2);
            final AppCompatTextView option_3 = (alertDlgView).findViewById(R.id.option_3);
            final AppCompatTextView option_4 = (alertDlgView).findViewById(R.id.option_4);
            final LinearLayout ll_option_1 = (alertDlgView).findViewById(R.id.ll_option_1);
            final LinearLayout lloption_2 = (alertDlgView).findViewById(R.id.lloption_2);
            final LinearLayout lloption_3 = (alertDlgView).findViewById(R.id.lloption_3);
            final LinearLayout lloption_4 = (alertDlgView).findViewById(R.id.lloption_4);
            final ImageView img_close = (alertDlgView).findViewById(R.id.img_close);

            alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alert.setContentView(alertDlgView);
            alert.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            alert.setCanceledOnTouchOutside(true);

            txt_heading.setText(questions.title);
            option_1.setText(questions.option_a);
            option_2.setText(questions.option_b);
            option_3.setText(questions.option_c);
            option_4.setText(questions.option_d);


            Button btn_GotIt = alert.findViewById(R.id.btn_submit);
            alert.setCancelable(false);
            alert.setCanceledOnTouchOutside(false);
            alert.show();


            ll_option_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    option_1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_green_24dp, 0);
                    option_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    option_3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    option_4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    // answer=option_1.getText().toString();
                    answer = "option_a";

                }
            });


            lloption_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    option_1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    option_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_green_24dp, 0);
                    option_3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    option_4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    // answer=option_2.getText().toString();

                    answer = "option_b";
                }
            });


            lloption_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    option_1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    option_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    option_3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_green_24dp, 0);
                    option_4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    answer = "option_c";

                }
            });


            lloption_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    option_1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    option_2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    option_3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close_close_24dp, 0);
                    option_4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_done_green_24dp, 0);
                    //answer=option_4.getText().toString();

                    answer = "option_d";


                }
            });


            btn_GotIt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (answer.equalsIgnoreCase(questions.getAnswer())) {
                        alert.dismiss();
                    }

                }
            });

            img_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    alert.cancel();
                }
            });
        }
    }

    private static class ListWithElements<T> implements ParameterizedType {

        private Class<T> elementsClass;

        public ListWithElements(Class<T> elementsClass) {
            this.elementsClass = elementsClass;
        }

        public Type[] getActualTypeArguments() {
            return new Type[]{elementsClass};
        }

        public Type getRawType() {
            return List.class;
        }

        public Type getOwnerType() {
            return null;
        }
    }

    public static class InsertShareTask extends AsyncTask<String, String, Boolean> {

        Context context;
        String newsId;
        String type;
        String sourceLink;
        String email;
        String sms;
        String whatsapp;
        ShareDao shareDao;

        public InsertShareTask(Context context, String news_id, String type, String source_link, String email, String sms, String whatsapp) {

            this.context = context;
            this.email = email;
            this.newsId = news_id;
            this.sourceLink = source_link;
            this.sms = sms;
            this.whatsapp = whatsapp;
            this.type = type;
        }

        @Override
        protected Boolean doInBackground(String... params) {

            PitchRoomDatabase db = PitchRoomDatabase.getDatabase(context);
            shareDao = db.shareDao();
            Share share = new Share();

            share.setEmp_no("test");
            share.setEmail(email);
            share.setContact(sms);
            share.setWhatsapp_no(whatsapp);
            share.setId(newsId);
            share.setType(type);
            share.setLink(sourceLink);


            Long id = shareDao.insertShareData(share);
            Log.d("inserid", "shareinserid>>" + id);


            return true;

        }


        @Override
        protected void onPostExecute(Boolean result) {


        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {

        }
    }

}
