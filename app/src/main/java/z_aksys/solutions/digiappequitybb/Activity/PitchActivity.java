package z_aksys.solutions.digiappequitybb.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import z_aksys.solutions.digiappequitybb.API.PitchServices;
import z_aksys.solutions.digiappequitybb.API.RetrofitClient;
import z_aksys.solutions.digiappequitybb.App;
import z_aksys.solutions.digiappequitybb.Dao.FeedbackModelDao;
import z_aksys.solutions.digiappequitybb.Database.PitchData;
import z_aksys.solutions.digiappequitybb.Database.PitchRoomDatabase;
import z_aksys.solutions.digiappequitybb.Fragment.SlideIndexFragment;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Request.FeedBackRequest;
import z_aksys.solutions.digiappequitybb.Response.FeedBackResponse;
import z_aksys.solutions.digiappequitybb.model.Feedback;
import z_aksys.solutions.digiappequitybb.model.PitchSlide;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;
import z_aksys.solutions.digiappequitybb.utils.OnSwipeTouchListener;

import static z_aksys.solutions.digiappequitybb.App.getContext;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appKey;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appSec;

public class PitchActivity extends AppCompatActivity implements View.OnClickListener {

    public PitchRoomDatabase pitchRoomDatabase;
    public FeedbackModelDao feedbackModelDao;
    public HashMap<Integer, String> slideCategories;
    PitchServices pitchServices;
    private ImageView mnSlideNavigation;
    private ImageView mnCalNavigation;
    private ImageView mnLanguageSettings;
    private ImageView mnExit;
    private ProgressDialog progressDialog;
    private View vPopupOverlay;
    private LinearLayout dialogLanguageSettigns;
    private Button btnLanguageUpdate;
    private TextView tvLangHindi;
    private TextView tvLangEnglish;
    private TextView tvLangGujrati;
    private TextView tvLangBengali;
    private TextView tvLangTamil;
    private TextView tvLangKannada;
    private int mSelectedLanguage = PitchData.LANG_ENG;
    private int mTempSelectedLanguage = mSelectedLanguage;
    private int mCurrentSelectedCategory = 0;
    private WebView wvSlides1;
    private WebView wvSlides2;
    private FrameLayout flNativeSlide;
    //private int currentlyActiveWebview = 2;// 2 so that loading starts with webview 1
    private TextView tvSlideTitle;
    private View frgCalculator;
    private View frgSlideIndex;
    private PitchData pitchData;
    private ArrayList<PitchSlide> slides;
    private SlideIndexFragment mSlideIndexFragment;
    private int mCurrentlySelectedSlideIndex = 0;
    private ACTION mCurrentlySelectedPopup = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pitch);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        pitchData = PitchData.getInstance();
        slideCategories = pitchData.slideCategories;
        slides = pitchData.pitchSlides;

        wvSlides1 = findViewById(R.id.wv_slides1);
        wvSlides2 = findViewById(R.id.wv_slides2);
        flNativeSlide = findViewById(R.id.fl_native_slide);
        currentlyActiveView= wvSlides2;

        PitchActivity.SwipeListener swipeListener = new PitchActivity.SwipeListener(this);

        wvSlides2.getSettings().setLoadWithOverviewMode(true);
        wvSlides2.getSettings().setUseWideViewPort(true);

        wvSlides1.getSettings().setLoadWithOverviewMode(true);
        wvSlides1.getSettings().setUseWideViewPort(true);

        wvSlides1.setOnTouchListener(swipeListener);
        wvSlides2.setOnTouchListener(swipeListener);
        flNativeSlide.setOnTouchListener(swipeListener);

        tvSlideTitle = findViewById(R.id.tv_slide_title);

        mnSlideNavigation = findViewById(R.id.mn_slide_navigation);
        mnSlideNavigation.setOnClickListener(this);

        mnCalNavigation = findViewById(R.id.im_calculators);
        mnCalNavigation.setOnClickListener(this);

        mnExit = findViewById(R.id.mn_pitch_exit);
        mnExit.setOnClickListener(this);

        frgCalculator = findViewById(R.id.fragment_calculator);
        frgSlideIndex = findViewById(R.id.fragment_slide_index);
        frgCalculator.setVisibility(View.GONE);
        frgSlideIndex.setVisibility(View.GONE);

        vPopupOverlay = findViewById(R.id.v_popup_overlay);
        vPopupOverlay.setOnClickListener(this);
        mnLanguageSettings = findViewById(R.id.mn_pitch_language_settings);
        mnLanguageSettings.setOnClickListener(this);
        dialogLanguageSettigns = findViewById(R.id.ll_language_setting_dialog);
        btnLanguageUpdate = findViewById(R.id.btn_language_update);
        btnLanguageUpdate.setOnClickListener(this);

        tvLangEnglish = findViewById(R.id.tv_lang_english);
        tvLangEnglish.setOnClickListener(this);
        tvLangHindi = findViewById(R.id.tv_lang_hindi);
        tvLangHindi.setOnClickListener(this);
        tvLangGujrati = findViewById(R.id.tv_lang_gujrati);
        tvLangGujrati.setOnClickListener(this);
        tvLangTamil = findViewById(R.id.tv_lang_tamil);
        tvLangTamil.setOnClickListener(this);
        tvLangBengali = findViewById(R.id.tv_lang_bengali);
        tvLangBengali.setOnClickListener(this);
        tvLangKannada = findViewById(R.id.tv_lang_kannada);
        tvLangKannada.setOnClickListener(this);

        mSlideIndexFragment = (SlideIndexFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_slide_index);
        mSlideIndexFragment.updateSlides(slides);
        mSlideIndexFragment.updateCategories(slideCategories);
        pitchServices = RetrofitClient.getInstance().getApi();

        if (getIntent().getBooleanExtra("isExperiencedInvestor", false)){
            mCurrentlySelectedSlideIndex= 11;
        }

        loadCurrentSlide(SLIDETRANSITION.LEFTSLIDE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.v_popup_overlay:
                removeSelection(ACTION.LANGUAGE);
                break;
            case R.id.mn_pitch_language_settings:
                showMenu(ACTION.LANGUAGE);
                break;
            case R.id.mn_pitch_exit:
                showMenu(ACTION.EXIT);
                break;
            case R.id.mn_slide_navigation:
                showMenu(ACTION.SLIDEINDEX);
                break;
            case R.id.im_calculators:
                showMenu(ACTION.CALCULATOR);
                break;
            case R.id.btn_language_update:
                mSelectedLanguage = mTempSelectedLanguage;
                removeSelection(ACTION.LANGUAGE);
                updateSlideTitle(mSelectedLanguage);
                mSlideIndexFragment.updateCurrentlySelectedLanguage(mSelectedLanguage);
                break;
            case R.id.tv_lang_english:
                mTempSelectedLanguage = PitchData.LANG_ENG;
                showSelectedLanguage(mTempSelectedLanguage);
                break;
            case R.id.tv_lang_hindi:
                mTempSelectedLanguage = PitchData.LANG_HINDI;
                showSelectedLanguage(mTempSelectedLanguage);
                break;
            case R.id.tv_lang_kannada:
                mTempSelectedLanguage = PitchData.LANG_KANNADA;
                showSelectedLanguage(mTempSelectedLanguage);
                break;
            case R.id.tv_lang_tamil:
                mTempSelectedLanguage = PitchData.LANG_TAMIL;
                showSelectedLanguage(mTempSelectedLanguage);
                break;
            case R.id.tv_lang_bengali:
                mTempSelectedLanguage = PitchData.LANG_BENGALI;
                showSelectedLanguage(mTempSelectedLanguage);
                break;
            case R.id.tv_lang_gujrati:
                mTempSelectedLanguage = PitchData.LANG_GUJRATI;
                showSelectedLanguage(mTempSelectedLanguage);
                break;
            default:
                break;
        }
    }

    private void showMenu(ACTION action) {

        if (mCurrentlySelectedPopup != null) {
            if (mCurrentlySelectedPopup == action){
                removeSelection(mCurrentlySelectedPopup);
                return;
            }
            removeSelection(mCurrentlySelectedPopup);
        }



        switch (action) {
            case SLIDEINDEX:
                mnSlideNavigation.setImageResource(R.drawable.ic_thumbnail_f);
                frgSlideIndex.setVisibility(View.VISIBLE);
                break;
            case LANGUAGE:
                dialogLanguageSettigns.setVisibility(View.VISIBLE);
                vPopupOverlay.setVisibility(View.VISIBLE);
                break;
            case CALCULATOR:
                mnCalNavigation.setImageResource(R.drawable.ic_calc_f);
                frgCalculator.setVisibility(View.VISIBLE);
                break;
            case EXIT:
                showExitDialog();
                break;
            default:
                break;
        }

        mCurrentlySelectedPopup = action;
    }

    private void showExitDialog() {

        final Dialog alert = new Dialog(this);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertDlgView = inflater.inflate(R.layout.dialog_pitch_exit, null);

        EditText et_customer_name = (EditText) (alertDlgView).findViewById(R.id.et_customer_name);
        EditText et_mobile = (EditText) (alertDlgView).findViewById(R.id.et_mobile);
        EditText et_emailId = (EditText) (alertDlgView).findViewById(R.id.et_emailId);

        ImageView img_close = alertDlgView.findViewById(R.id.img_close);
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });

        Button btnSubmit = alertDlgView.findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(et_customer_name.getText())) {
                    Toast.makeText(getApplicationContext(), "please enter valid name", Toast.LENGTH_LONG).show();
                } else if (!AngelPitchUtil.isValidMobile(et_mobile.getText())) {

                    Toast.makeText(App.getContext(), "Please Enter Valid mobile ", Toast.LENGTH_LONG).show();

                } else if (!AngelPitchUtil.isValidEmail(et_emailId.getText())) {
                    Toast.makeText(App.getContext(), "Please Enter Valid emailId", Toast.LENGTH_LONG).show();
                } else {

                    List<FeedBackRequest> feedBackRequestList = new ArrayList<>();
                    FeedBackRequest feedBackRequest = new FeedBackRequest();
                    feedBackRequest.setEmp_no("test");
                    feedBackRequest.setCustomer_name(et_customer_name.getText().toString());
                    feedBackRequest.setContact(ObjectUtils.getDoubleFromString(et_mobile.getText().toString()));
                    feedBackRequest.setEmail(et_emailId.getText().toString());
                    feedBackRequest.setWhatsapp_no(et_mobile.getText().toString());
                    feedBackRequestList.add(feedBackRequest);

                    if (AngelPitchUtil.checkConnection(getApplicationContext())) {
                        callServices(feedBackRequestList);
                    } else {
                        new AsyncTaskRunnerResult(getContext(), feedBackRequestList).execute();
                        Toast.makeText(getApplicationContext(), "please connect the internet", Toast.LENGTH_LONG).show();
                    }

                    alert.dismiss();
                    finish();

                }


            }
        });

        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.setContentView(alertDlgView);
        alert.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        alert.setCanceledOnTouchOutside(false);
        alert.setCancelable(false);

        alert.show();
    }

    private void callServices(List<FeedBackRequest> feedBackRequestList) {

        Call<FeedBackResponse> call = pitchServices.postAllFeedBack("application/json", appKey, appSec, feedBackRequestList);
        call.enqueue(new Callback<FeedBackResponse>() {
            @Override
            public void onResponse(Call<FeedBackResponse> call, Response<FeedBackResponse> response) {
                // hideProgress();
                Toast.makeText(getContext(), "sucess>>>>>>>>>>" + response.message(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<FeedBackResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

    private void removeSelection(ACTION action) {
        switch (action) {
            case SLIDEINDEX:
                mnSlideNavigation.setImageResource(R.drawable.ic_thumbnail_o);
                frgSlideIndex.setVisibility(View.GONE);
                break;
            case LANGUAGE:
                dialogLanguageSettigns.setVisibility(View.GONE);
                vPopupOverlay.setVisibility(View.GONE);
                break;
            case CALCULATOR:
                mnCalNavigation.setImageResource(R.drawable.ic_calc_o);
                frgCalculator.setVisibility(View.GONE);
                break;
            default:
                break;
        }

        mCurrentlySelectedPopup = null;
    }

    private void clearAllSelectionOfLanguage() {
        tvLangEnglish.setBackground(getResources().getDrawable(R.drawable.language_rouded_borders));
        tvLangHindi.setBackground(getResources().getDrawable(R.drawable.language_rouded_borders));
        tvLangGujrati.setBackground(getResources().getDrawable(R.drawable.language_rouded_borders));
        tvLangTamil.setBackground(getResources().getDrawable(R.drawable.language_rouded_borders));
        tvLangBengali.setBackground(getResources().getDrawable(R.drawable.language_rouded_borders));
        tvLangKannada.setBackground(getResources().getDrawable(R.drawable.language_rouded_borders));
    }

    private void showSelectedLanguage(int selectedLanguage) {

        // Clear all selections
        clearAllSelectionOfLanguage();

        switch (selectedLanguage) {
            case PitchData.LANG_ENG:
                tvLangEnglish.setBackground(getResources().getDrawable(R.drawable.language_option_selected));
                break;
            case PitchData.LANG_HINDI:
                tvLangHindi.setBackground(getResources().getDrawable(R.drawable.language_option_selected));
                break;
            case PitchData.LANG_GUJRATI:
                tvLangGujrati.setBackground(getResources().getDrawable(R.drawable.language_option_selected));
                break;
            case PitchData.LANG_TAMIL:
                tvLangTamil.setBackground(getResources().getDrawable(R.drawable.language_option_selected));
                break;
            case PitchData.LANG_BENGALI:
                tvLangBengali.setBackground(getResources().getDrawable(R.drawable.language_option_selected));
                break;
            case PitchData.LANG_KANNADA:
                tvLangKannada.setBackground(getResources().getDrawable(R.drawable.language_option_selected));
                break;
            default:
                break;
        }
    }

    public void onSlideSelectecOnNavigationMenu(int index) {
        removeSelection(ACTION.SLIDEINDEX);
        loadSlideOnIndex(index);
    }

    private View currentlyActiveView=null;
    private void loadCurrentSlide(SLIDETRANSITION slidetransition) {

        PitchSlide pitchSlide = slides.get(mCurrentlySelectedSlideIndex);

        View viewToLoadSlide;

        if (pitchSlide.isNative){
            viewToLoadSlide= flNativeSlide;
            loadFragment(pitchSlide.nativeSlide);
        } else {
            if (currentlyActiveView.getId()== wvSlides1.getId()){
                viewToLoadSlide= wvSlides2;
                //wvSlides1.loadUrl(pitchSlide.path);
            }else {
                viewToLoadSlide= wvSlides1;
                //wvSlides2.loadUrl(pitchSlide.path);
            }
            ((WebView) viewToLoadSlide).loadUrl(pitchSlide.path);

        }

        updateSlideTitle(mSelectedLanguage);
        transitionSlide(currentlyActiveView, viewToLoadSlide, slidetransition);
        currentlyActiveView= viewToLoadSlide;


        mSlideIndexFragment.updateCurrentlySelectedCategory(pitchSlide.category);
        mSlideIndexFragment.updateCurrentlySelectedSlide(mCurrentlySelectedSlideIndex);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_native_slide,
                fragment,
                fragment.getClass().getSimpleName());

        fragmentTransaction.commitAllowingStateLoss();
    }

    private void transitionSlide(View viewToHide, View viewToShow, SLIDETRANSITION slidetransition) {

        if (slidetransition != null) {

            AnimationListener animationListenerViewToHide= new AnimationListener(viewToHide, true);
            AnimationListener animationListenerViewToShow= new AnimationListener(viewToShow, false);

            switch (slidetransition) {
                case LEFTSLIDE:
                    Animation slideLeftOutAnimation;
                    Animation slideRightInAnimation;
                    slideRightInAnimation = AnimationUtils.loadAnimation(this, R.anim.right_slide_in);
                    slideLeftOutAnimation = AnimationUtils.loadAnimation(this, R.anim.left_slide_out);

                    slideLeftOutAnimation.setAnimationListener(animationListenerViewToHide);
                    slideRightInAnimation.setAnimationListener(animationListenerViewToShow);

                    viewToHide.startAnimation(slideLeftOutAnimation);
                    viewToShow.startAnimation(slideRightInAnimation);
                    break;
                case RIGHTSLIDE:
                    Animation slideRightOuttAnimation;
                    Animation slideLeftInAnimation;
                    slideLeftInAnimation = AnimationUtils.loadAnimation(this, R.anim.left_slide_in);
                    slideRightOuttAnimation = AnimationUtils.loadAnimation(this, R.anim.right_slide_out);

                    slideRightOuttAnimation.setAnimationListener(animationListenerViewToHide);
                    slideLeftInAnimation.setAnimationListener(animationListenerViewToShow);

                    viewToHide.startAnimation(slideRightOuttAnimation);
                    viewToShow.startAnimation(slideLeftInAnimation);

                    break;
                default:
                    viewToHide.setVisibility(View.GONE);
                    viewToShow.setVisibility(View.VISIBLE);
                    break;
            }
        } else {
            viewToHide.setVisibility(View.GONE);
            viewToShow.setVisibility(View.VISIBLE);
        }
    }

    private void updateSlideTitle(int selectedLanguage) {

        if (slides.get(mCurrentlySelectedSlideIndex).isSectionalHeader){
            tvSlideTitle.setText("");
        } else {
            tvSlideTitle.setText(slides.get(mCurrentlySelectedSlideIndex).title.get(selectedLanguage));
        }

    }

    private void loadSlideOnIndex(int index) {
        mCurrentlySelectedSlideIndex = index;
        loadCurrentSlide(SLIDETRANSITION.LEFTSLIDE);
    }

    private void slideLeft() {
        if (mCurrentlySelectedSlideIndex < slides.size() - 1) {
            mCurrentlySelectedSlideIndex++;
            loadCurrentSlide(SLIDETRANSITION.LEFTSLIDE);
        }
    }

    private void slideRight() {
        if (mCurrentlySelectedSlideIndex > 0) {
            mCurrentlySelectedSlideIndex--;
            loadCurrentSlide(SLIDETRANSITION.RIGHTSLIDE);
        }
    }

    public void showProgress() {
        if ((progressDialog == null || (progressDialog != null && progressDialog.isShowing() == false))) {
            progressDialog = ProgressDialog.show(getContext(), "", "Please wait.", true);
        }
    }

    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        } else {
            return;
        }
    }

    private enum ACTION {SLIDEINDEX, LANGUAGE, CALCULATOR, EXIT}


    private enum SLIDETRANSITION {LEFTSLIDE, RIGHTSLIDE}

    private class SwipeListener extends OnSwipeTouchListener {

        public SwipeListener(Context ctx) {
            super(ctx);
        }

        @Override
        public void onSwipeLeft() {
            super.onSwipeLeft();
            slideLeft();
        }

        @Override
        public void onSwipeRight() {
            super.onSwipeRight();
            slideRight();
        }
    }

    private class AsyncTaskRunnerResult extends AsyncTask<String, String, Boolean> {

        Context context;
        List<FeedBackRequest> feedBackRequests;
        List<Feedback> feedbacks;
        String outOfMark;


        public AsyncTaskRunnerResult(Context context, List<FeedBackRequest> feedBackRequestList) {
            this.context = context;
            this.feedBackRequests = feedBackRequestList;
            feedbacks = new ArrayList<>();

        }


        @Override
        protected Boolean doInBackground(String... strings) {

            if (!ObjectUtils.isEmpty(feedBackRequests)) {
                for (int i = 0; i < feedBackRequests.size(); i++) {
                    Feedback feedback = new Feedback();
                    feedback.setCustomer_name(feedBackRequests.get(i).getCustomer_name());
                    feedback.setEmp_number(feedBackRequests.get(i).getEmp_no());
                    feedback.setEmail(feedBackRequests.get(i).getEmail());
                    feedback.setMobile_number(ObjectUtils.getStringFromDouble(feedBackRequests.get(i).getContact()));
                    feedback.setComments(feedBackRequests.get(i).getWhatsapp_no());
                    feedbacks.add(feedback);

                }

            }

            pitchRoomDatabase = PitchRoomDatabase.getDatabase(getContext());
            feedbackModelDao = pitchRoomDatabase.feedbackModelDao();

            for (int i = 0; i < feedbacks.size(); i++) {
                Long feedbackId = feedbackModelDao.insertFeedback(feedbacks.get(i));
                System.out.println("offline feedback>>id" + feedbackId);
            }


            return true;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            // execution of result of Long time consuming operation
            //  callAdaptor();
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(String... text) {

        }
    }

    private class AnimationListener implements Animation.AnimationListener{

        private View animatedView;
        private boolean isHideViewAfterAnimation= false;

        AnimationListener(View animatedView, boolean isHideViewAfterAnimation){
            this.animatedView= animatedView;
            this.isHideViewAfterAnimation= isHideViewAfterAnimation;
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (animatedView!=null){
                if (isHideViewAfterAnimation){
                    animatedView.setVisibility(View.GONE);
                } else {
                    animatedView.setVisibility(View.VISIBLE);
                }
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
