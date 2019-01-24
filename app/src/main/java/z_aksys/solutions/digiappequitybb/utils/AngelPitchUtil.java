package z_aksys.solutions.digiappequitybb.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
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
    private static AngelSharedPrefance sharedPrefance;


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
        sharedPrefance = new AngelSharedPrefance(App.getContext());
        PitchRoomDatabase pitchRoomDatabase;
        ShareDao shareDao;
        final Dialog alert = new Dialog(context);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertDlgView = inflater.inflate(R.layout.showdialogs, null);
        AppCompatTextView txt_heading = (alertDlgView).findViewById(R.id.txt_heading);
        final EditText et_emailid = (alertDlgView).findViewById(R.id.et_emailid);
        final EditText et_whatsApp = (alertDlgView).findViewById(R.id.et_whatsApp);
        final EditText et_sms = (alertDlgView).findViewById(R.id.et_sms);
        final Button btn_email = (alertDlgView).findViewById(R.id.btn_email_send);
        final Button btn_sms = (alertDlgView).findViewById(R.id.btn_sms_send);
        final Button btn_whatsapp = (alertDlgView).findViewById(R.id.btn_whatsAppsned);
        final TextView tvEmailSendError = alertDlgView.findViewById(R.id.tv_email_send_error);
        final TextView tvSmsSendError = alertDlgView.findViewById(R.id.tv_sms_send_error);
        final ImageView ivLoader = alertDlgView.findViewById(R.id.iv_loader);
        final ImageView ivSmsLoader = alertDlgView.findViewById(R.id.iv_sms_loader);
        final AppCompatImageView close = (alertDlgView).findViewById(R.id.ic_remove_icon);
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.setContentView(alertDlgView);
        alert.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        alert.setCanceledOnTouchOutside(true);
        alert.show();
        String email, mobile;
        email = sharedPrefance.getHealthAPI("emailid");
        mobile = sharedPrefance.getHealthAPI("mobilenumber");

        if (!TextUtils.isEmpty(email)) {
            et_emailid.setText(email);
        } else {
            et_emailid.setText("");
        }

        if (!TextUtils.isEmpty(mobile)) {
            et_sms.setText(mobile);
        } else {
            et_sms.setText("");
        }

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
                        //Toast.makeText(App.getContext(), "Please Enter Valid emailId", Toast.LENGTH_LONG).show();
                        tvEmailSendError.setText("Invalid email id");
                    } else {

                        tvEmailSendError.setText("");
                        et_emailid.setEnabled(false);
                        btn_email.setEnabled(false);
                        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(ivLoader);
                        Glide.with(context).load(R.raw.loading).into(imageViewTarget);
                        ivLoader.setVisibility(View.VISIBLE);
                        callWebServices(news_id, type, source_link,
                                et_emailid.getText().toString(), "",
                                "", new Callback<ShareResponse>() {
                                    @Override
                                    public void onResponse(Call<ShareResponse> call, Response<ShareResponse> response) {
                                        if (response.isSuccessful()) {
                                            ivLoader.setImageResource(R.drawable.ic_done_green_24dp);
                                        } else {
                                            tvEmailSendError.setText("Sending failed!  Please try again...");
                                            ivLoader.setVisibility(View.GONE);
                                            et_emailid.setEnabled(true);
                                            btn_email.setEnabled(true);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ShareResponse> call, Throwable t) {
                                        ivLoader.setVisibility(View.GONE);
                                        et_emailid.setEnabled(true);
                                        btn_email.setEnabled(true);
                                        tvEmailSendError.setText("Sending failed!  Please try again...");
                                    }
                                });

                        sharedPrefance.setHelathAPI("emailid", et_emailid.getText().toString());

                        // alert.dismiss();
                    }

                } else {

                    new InsertShareTask(App.getContext(), news_id, type, source_link, et_emailid.getText().toString(), "", "").execute();

                    sharedPrefance.setHelathAPI("emailid", et_emailid.getText().toString());
                    // alert.dismiss();

                }


            }
        });


        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected) {

                    if (!AngelPitchUtil.ismobilenumberValid(et_sms.getText().toString())) {
                        tvSmsSendError.setText("Invalid mobile number");

                    } else {

                        tvSmsSendError.setText("");
                        et_sms.setEnabled(false);
                        btn_sms.setEnabled(false);
                        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(ivSmsLoader);
                        Glide.with(context).load(R.raw.loading).into(imageViewTarget);
                        ivSmsLoader.setVisibility(View.VISIBLE);
                        callWebServices(news_id, type, source_link, "",
                                et_sms.getText().toString(), "", new Callback<ShareResponse>() {
                                    @Override
                                    public void onResponse(Call<ShareResponse> call, Response<ShareResponse> response) {
                                        if (response.isSuccessful()) {
                                            ivSmsLoader.setImageResource(R.drawable.ic_done_green_24dp);
                                        } else {
                                            tvSmsSendError.setText("Failed sending! Please try again...");
                                            ivSmsLoader.setVisibility(View.GONE);
                                            et_sms.setEnabled(true);
                                            btn_sms.setEnabled(true);
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<ShareResponse> call, Throwable t) {
                                        tvSmsSendError.setText("Failed sending! Please try again...");
                                        ivSmsLoader.setVisibility(View.GONE);
                                        et_sms.setEnabled(true);
                                        btn_sms.setEnabled(true);
                                    }
                                });
                        //alert.dismiss();
                        sharedPrefance.setHelathAPI("mobilenumber", et_sms.getText().toString());

                    }

                } else {
                    new InsertShareTask(App.getContext(), news_id, type, source_link, " ", et_sms.getText().toString(), "").execute();
                    //alert.dismiss();
                    sharedPrefance.setHelathAPI("mobilenumber", et_sms.getText().toString());


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
                        callWebServices(news_id, type, source_link,
                                "", "",
                                et_whatsApp.getText().toString(), new Callback<ShareResponse>() {
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
                        alert.dismiss();

                    }


                } else {
                    new InsertShareTask(App.getContext(), news_id, type, source_link, "", "", et_whatsApp.getText().toString()).execute();
                    alert.dismiss();

                }


            }
        });

    }

    private static void callWebServices(String id, String type, String link, String email, String sms, String whatsApp, Callback<ShareResponse> callback) {

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
        call.enqueue(callback);

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
