/*
package z_aksys.solutions.pitchapplication.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import z_aksys.solutions.pitchapplication.API.PitchAPI;
import z_aksys.solutions.pitchapplication.API.PitchServices;
import z_aksys.solutions.pitchapplication.API.RestAPI;
import z_aksys.solutions.pitchapplication.Activity.NewsViewer;
import z_aksys.solutions.pitchapplication.Activity.YoutubePlayerActivity;
import z_aksys.solutions.pitchapplication.Adapter.MultiViewTypeAdapter;

import z_aksys.solutions.pitchapplication.Adapter.PaginationAdapter;
import z_aksys.solutions.pitchapplication.Dao.NewsDao;
import z_aksys.solutions.pitchapplication.Database.PitchRoomDatabase;
import z_aksys.solutions.pitchapplication.R;
import z_aksys.solutions.pitchapplication.Request.NewsRequest;
import z_aksys.solutions.pitchapplication.Response.NewsResponse;
import z_aksys.solutions.pitchapplication.listener.PaginationAdapterCallback;
import z_aksys.solutions.pitchapplication.listener.PaginationScrollListener;
import z_aksys.solutions.pitchapplication.mPager.Paginator;
import z_aksys.solutions.pitchapplication.model.News;
import z_aksys.solutions.pitchapplication.listener.OnClickVideo;

import static android.support.constraint.Constraints.TAG;
import static z_aksys.solutions.pitchapplication.utils.Constants.baseUrl;

public class NewsDemo extends Fragment implements OnClickVideo, PaginationAdapterCallback {

    private RecyclerView recyclerView;
    private TextView txt_date;
    private PaginationAdapter adapter;
    private List<News> albumList;
    private ArrayList<NewsResponse.News> newsResponseList;
    private String VideoId, newsUrl;
    private ProgressDialog progressDialog;
    private PitchRoomDatabase db;
    private NewsDao newsDao;
    private PitchServices pitchServices;
    private LinearLayoutManager mLayoutManager;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private static int Start = 0;
    private static int Limit = 10;


    public NewsDemo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.newxfragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        txt_date = (TextView) view.findViewById(R.id.txtDate);
        Calendar c = new GregorianCalendar();

        Date dt = new Date(c.getTimeInMillis());
        DateFormat dtformat;
        dtformat = DateFormat.getDateInstance(DateFormat.FULL);

        adapter = new PaginationAdapter(getContext(),this);
        adapter.notifyDataSetChanged();
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(60), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new PaginationScrollListener(mLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                Start += 3;
                Limit += 3;

                loadNextPage();
            }

            @Override
            public int getTotalPageCount() {
                return 100;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        //init service and load data
        pitchServices = PitchAPI.getClient().create(PitchServices.class);

        loadFirstPage();


        return view;
    }

    private void loadFirstPage() {

        Log.d(TAG, "loadFirstPage: ");

        // To ensure list is visible when retry button in error view is clicked
        showProgress();

        callNewsApi().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                // Got data. Send it to adapter

                hideProgress();

                Toast.makeText(getContext(),"Response"+response,Toast.LENGTH_LONG).show();
                List<NewsResponse.News> results = fetchResults(response);

                System.out.println("result"+results.size());

                adapter.addAll(results);

                if (Start <= 100)
                    adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                hideProgress();
                t.printStackTrace();

            }
        });
    }

    private Call<NewsResponse> callNewsApi() {

*/
/*
        return pitchServices.getNews("application/json", "80w4k8ogow8k4g4ks80sg08o4kcsc04scg48kks4"
                , "42d34bd99093094714e0257e391a810c",
                "test",
                String.valueOf(Start),
                String.valueOf(Limit)
        );
    }*//*



    private void loadNextPage() {
        Log.d(TAG, "loadNextPage: " + String.valueOf(Start));

        callNewsApi().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                hideProgress();
                adapter.removeLoadingFooter();
                isLoading = false;



                Toast.makeText(getContext(),"Response"+response,Toast.LENGTH_LONG).show();
                List<NewsResponse.News> results = fetchResults(response);

                System.out.println("resultafterload"+results.size());

                adapter.addAll(results);

                if (Start != 100) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                hideProgress();
                t.printStackTrace();
                // adapter.showRetry(true, fetchErrorMessage(t));
            }
        });
    }

    private List<NewsResponse.News> fetchResults(Response<NewsResponse> response) {
        NewsResponse newsResponse = response.body();
        return newsResponse.getNews();

    }


    private void getNews() {

        showProgress();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(logging);  // <-- this is the important line!


        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();


        RestAPI restAPI = retrofit.create(RestAPI.class);

        Call<NewsResponse> myResponseCall = restAPI.getNews("application/json", "80w4k8ogow8k4g4ks80sg08o4kcsc04scg48kks4", "42d34bd99093094714e0257e391a810c", "test", "0", "10");


        myResponseCall.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {


                if (response.isSuccessful()) {
                    System.out.println("RequestParameter" + response);

                    // Toast.makeText(getContext(), "sucess" + response.toString(), Toast.LENGTH_LONG).show();
                    newsResponseList = new ArrayList<>();
                    newsResponseList = response.body().getNews();

                    callAdapter();

                    //        callDataBase(newsResponseList);

                    //new InsertTask(getActivity(), newsResponseList).execute();
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

    private void callDataBase(ArrayList<NewsResponse.News> newsResponseList) {


        PitchRoomDatabase db = PitchRoomDatabase.getDatabase(getActivity());
        newsDao = db.newsDao();

        for (int i = 0; i < newsResponseList.size(); i++) {
            newsDao.insert(newsResponseList.get(i));

        }


    }


    private void callAdapter() {




    }


    @Override
    public void VideoId(String Id) {

        VideoId = Id;


        //start youtube player activity by passing selected video id via intent
        startActivity(new Intent(getActivity(), YoutubePlayerActivity.class)
                .putExtra("video_id", VideoId));

    }

    @Override
    public void newsUrl(String url) {
        newsUrl = url;

        startActivity(new Intent(getActivity(), NewsViewer.class)
                .putExtra("newsUrl", newsUrl));

    }

    @Override
    public void retryPageLoad() {

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
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    */
/**
 * Converting dp to pixel
 *//*

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


    private static class InsertTask extends AsyncTask<Void, Void, Boolean> {


        ArrayList<NewsResponse.News> newsResponseList = new ArrayList<>();
        NewsDao newsDao;
        FragmentActivity fragment;

        public InsertTask(FragmentActivity activity, ArrayList<NewsResponse.News> newsResponseList) {
            this.newsResponseList = newsResponseList;
            this.fragment = activity;


        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            PitchRoomDatabase db = PitchRoomDatabase.getDatabase(fragment);
            newsDao = db.newsDao();
            deleteRecord();

            for (int i = 0; i < newsResponseList.size(); i++) {

                int ids = newsDao.deleteAll();

                Log.d("deleted", "deleted>>" + ids);
                Long id = newsDao.insert(newsResponseList.get(i));
                Log.d("inserid", "inserid>>" + id);

            }

            List<NewsResponse.News> news = newsDao.getAllnews();
            Log.d("insterted news", "news inserted size" + news.size());


            return true;
        }

        private void deleteRecord() {


        }
    }
}


*/
