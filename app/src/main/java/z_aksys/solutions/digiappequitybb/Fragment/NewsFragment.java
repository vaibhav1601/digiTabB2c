package z_aksys.solutions.digiappequitybb.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import z_aksys.solutions.digiappequitybb.API.PitchServices;
import z_aksys.solutions.digiappequitybb.API.RetrofitClient;
import z_aksys.solutions.digiappequitybb.Activity.YoutubePlayerActivity;
import z_aksys.solutions.digiappequitybb.Adapter.MultiViewTypeAdapter;
import z_aksys.solutions.digiappequitybb.App;
import z_aksys.solutions.digiappequitybb.Dao.NewsDao;
import z_aksys.solutions.digiappequitybb.Database.PitchRoomDatabase;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.NewsResponse;
import z_aksys.solutions.digiappequitybb.listener.OnClickVideo;
import z_aksys.solutions.digiappequitybb.model.News;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

import static z_aksys.solutions.digiappequitybb.utils.Constants.appKey;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appSec;
import static z_aksys.solutions.digiappequitybb.utils.Constants.user;

public class NewsFragment extends Fragment implements OnClickVideo {

    public List<NewsResponse.News> newsResponseList;
    LayoutInflater layoutInflater;
    int orientation;
    boolean potrate;
    PitchServices pitchServices;
    private RecyclerView recyclerView;
    private TextView txt_date;
    private MultiViewTypeAdapter adapter;
    private List<News> albumList;
    private AppCompatImageView btn_goal;
    private String VideoId, newsUrl;
    private ProgressDialog progressDialog;
    private PitchRoomDatabase db;
    private NewsDao newsDao;
    private int firstPage = 0;
    private int pageLimit = 10;
    private boolean isLoading = true;
    private int pastVisibleitem, visibleItemCount, totalItemCount, previousTotal = 0;
    private int viewtueshole = 100;
    private int hasNext;
    private RecyclerView.LayoutManager mLayoutManager;

    private int span;
    private LinearLayout linearLayout;


    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layoutInflater = inflater;
        final View view = inflater.inflate(R.layout.newxfragment, container, false);
        pitchServices = RetrofitClient.getInstance().getApi();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);


        if (AngelPitchUtil.getScreenOrientation(getActivity()) == 1) {
            orientation = Configuration.ORIENTATION_PORTRAIT;
            potrate = true;
            span = 2;
            setValue();

        } else {
            orientation = Configuration.ORIENTATION_LANDSCAPE;
            span = 3;
            setValue();
            potrate = false;
        }


    }


    private void setValue() {
        mLayoutManager = new GridLayoutManager(getContext(), span);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(span, dpToPx(60), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        txt_date = (TextView) getView().findViewById(R.id.txtDate);

       // linearLayout.setVisibility(View.VISIBLE);
        Calendar c = new GregorianCalendar();
        Date dt = new Date(c.getTimeInMillis());
        DateFormat dtformat;
        dtformat = DateFormat.getDateInstance(DateFormat.FULL);
        txt_date.setText(dtformat.format(dt));

        if (AngelPitchUtil.checkConnection(getActivity())) {
            showNewsDetails();

        } else {
            new GetNewsTask(getActivity()).execute();
            Toast.makeText(getActivity(), "please connect the internet", Toast.LENGTH_LONG).show();
        }
    }


    protected void replaceFragment(Fragment fragmentName, boolean isAddToBackStack) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_left_in,
                R.anim.slide_left_out,
                R.anim.trans_right_in,
                R.anim.trans_right_out);

        if (isAddToBackStack) {
            fragmentTransaction.replace(android.R.id.content,
                    fragmentName,
                    fragmentName.getClass().getSimpleName()).addToBackStack(null);
        } else {
            fragmentTransaction.replace(android.R.id.content,
                    fragmentName,
                    fragmentName.getClass().getSimpleName());
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // setlogo();// Your Method

            //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            span = 3;
            // setValue();
            //GridLayoutManager(getActivity(),3);
            Log.d("Daiya", "ORIENTATION_LANDSCAPE");

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            span = 2;
            //setValue();

            //  setlogoForLandScape();// Your Method
            Log.d("Daiya", "ORIENTATION_PORTRAIT");
        }
    }


    private void callAdapter() {

        if (!ObjectUtils.isEmpty(newsResponseList)) {
            adapter = new MultiViewTypeAdapter(newsResponseList, getContext(), this, potrate);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
        }


    }


    private void showNewsDetails() {
        showProgress();
        Call<NewsResponse> call = pitchServices.getNews("application/json", appKey, appSec, user, firstPage, pageLimit);
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                showProgress();
                if (response.isSuccessful()) {
                    System.out.println("RequestParameter" + response);
                    newsResponseList = new ArrayList<>();
                    newsResponseList = response.body().getNews();
                    hasNext = response.body().getHasNext();
                    new InsertTask(getActivity(), newsResponseList).execute();
                    hideProgress();
                }

            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("RequestParameter" + t.getMessage());
                hideProgress();

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                pastVisibleitem = ((GridLayoutManager) mLayoutManager).findFirstVisibleItemPosition();

                if (dy > 0) {
                    if (isLoading) {
                        if (totalItemCount > previousTotal) {
                            isLoading = false;
                            previousTotal = totalItemCount;
                        }

                    }

                    if (!isLoading && (totalItemCount - visibleItemCount) <= (pastVisibleitem + viewtueshole) && hasNext > 0) {
                        pageLimit++;
                        callPagination();
                        isLoading = true;
                    }

                }
            }
        });

    }


    private void callPagination() {
        showProgress();
        Call<NewsResponse> myResponseCall = pitchServices.getNews("application/json", appKey, appSec, user, firstPage, pageLimit);
        myResponseCall.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {


                if (response.isSuccessful()) {
                    System.out.println("RequestParameter" + response);
                    newsResponseList = new ArrayList<>();
                    newsResponseList = response.body().getNews();
                    new InsertTask(getActivity(), newsResponseList).execute();
                    hideProgress();
                }


            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();

                System.out.println("RequestParameter" + t.getMessage());
                hideProgress();
            }
        });


    }


    @Override
    public void VideoId(String videoId, String newsId) {

        VideoId = videoId;
        //start youtube player activity by passing selected video id via intent
        startActivity(new Intent(getActivity(), YoutubePlayerActivity.class)
                .putExtra("video_id", VideoId)
        .putExtra("newsId", newsId));

    }

    @Override
    public void newsUrl(String url, String newsId) {
        newsUrl = url;
        Bundle bundle = new Bundle();
        bundle.putString("newsUrl", newsUrl);
        bundle.putString("newsId", newsId);
        NewsViewerFragment fragment2 = new NewsViewerFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragment2.setArguments(bundle);
        replaceFragment(fragment2, true);


    }


    public void newsView(Context context) {

        final Dialog alert = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertDlgView = inflater.inflate(R.layout.newsviewdialouges, null);

        final AppCompatTextView btnClose = (AppCompatTextView) (alertDlgView).findViewById(R.id.txtclose);

        final WebView webView = (WebView) (alertDlgView).findViewById(R.id.webview);

        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alert.setContentView(alertDlgView);
        alert.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        alert.setCanceledOnTouchOutside(true);
        alert.show();
        initWebView(webView);
        webView.loadUrl(newsUrl);


        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });


    }

    private void initWebView(WebView webView) {
        webView.setWebChromeClient(new MyWebChromeClient(App.getContext()));
        webView.clearCache(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                //invalidateOptionsMenu();
            }
        });
        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void showProgress() {
        if (isAdded() && (progressDialog == null || (progressDialog != null && progressDialog.isShowing() == false))) {
            progressDialog = ProgressDialog.show(getActivity(), "", "Please wait.", true);
        }
    }

    public void hideProgress() {
        if (isAdded() && progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        } else {
            return;
        }
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount - 20; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount - 20; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing - 10;
                }
                outRect.bottom = spacing - 10; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    public class InsertTask extends AsyncTask<String, String, Boolean> {

        List<NewsResponse.News> newsResponseList = new ArrayList<>();

        FragmentActivity fragment;


        public InsertTask(FragmentActivity activity, List<NewsResponse.News> newsResponseList) {

            this.newsResponseList = newsResponseList;
            this.fragment = activity;


        }

        @Override
        protected Boolean doInBackground(String... params) {

            PitchRoomDatabase db = PitchRoomDatabase.getDatabase(fragment);
            newsDao = db.newsDao();
            for (int i = 0; i < newsResponseList.size(); i++) {
                Long id = newsDao.insert(newsResponseList.get(i));
                Log.d("inserid", "inserid>>" + id);

            }
            return true;

        }


        @Override
        protected void onPostExecute(Boolean result) {

            new GetNewsTask(getActivity()).execute();

        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {

        }
    }


    private class GetNewsTask extends AsyncTask<String, String, Boolean> {


        FragmentActivity fragment;


        public GetNewsTask(FragmentActivity activity) {
            this.fragment = activity;


        }

        @Override
        protected Boolean doInBackground(String... params) {


            PitchRoomDatabase db = PitchRoomDatabase.getDatabase(fragment);
            newsDao = db.newsDao();

            newsResponseList = newsDao.getAllnews();

            System.out.println("list>>>" + newsResponseList.size());

            // Toast.makeText(getContext(), "doInBackground"+newsResponseList.size(), Toast.LENGTH_LONG).show();


            return true;

        }


        @Override
        protected void onPostExecute(Boolean result) {

            super.onPostExecute(result);

            // Toast.makeText(getContext(), "on post news", Toast.LENGTH_LONG).show();

            // execution of result of Long time consuming operation
            callAdapter();
            //hideProgress();


        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {

        }
    }


}


