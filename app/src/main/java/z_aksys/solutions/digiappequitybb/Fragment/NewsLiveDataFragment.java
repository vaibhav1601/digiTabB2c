package z_aksys.solutions.digiappequitybb.Fragment;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import z_aksys.solutions.digiappequitybb.Activity.NewsViewer;
import z_aksys.solutions.digiappequitybb.Activity.YoutubePlayerActivity;
import z_aksys.solutions.digiappequitybb.Dao.NewsDao;
import z_aksys.solutions.digiappequitybb.Database.PitchRoomDatabase;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.NewsResponse;
import z_aksys.solutions.digiappequitybb.ViewModel.ItemAdapter;
import z_aksys.solutions.digiappequitybb.ViewModel.ItemViewModel;
import z_aksys.solutions.digiappequitybb.ViewModel.NewsListViewModel;
import z_aksys.solutions.digiappequitybb.listener.OnClickVideo;
import z_aksys.solutions.digiappequitybb.model.News;

public class NewsLiveDataFragment extends LifecycleFragment implements OnClickVideo {

    public List<NewsResponse.News> newsResponseList;
    NewsListViewModel newsListViewModel;
    private RecyclerView recyclerView;
    private TextView txt_date;
    private ItemAdapter adapter;
    private List<News> albumList;
    private String VideoId, newsUrl;
    private ProgressDialog progressDialog;
    private PitchRoomDatabase db;
    private NewsDao newsDao;

    public NewsLiveDataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.newxfragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        txt_date = (TextView) view.findViewById(R.id.txtDate);
        Calendar c = new GregorianCalendar();
        Date dt = new Date(c.getTimeInMillis());
        DateFormat dtformat;
        dtformat = DateFormat.getDateInstance(DateFormat.FULL);

        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);


        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<NewsResponse.News>>() {
            @Override
            public void onChanged(PagedList<NewsResponse.News> items) {

                Log.d("list", "list" + items.size());
                // adapter.submitList(items);
                //adapter= new ItemAdapter(getActivity(),items,this);
            }
        });


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(60), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return view;
    }


    @Override
    public void VideoId(String url, String Id) {
        VideoId = Id;
        startActivity(new Intent(getActivity(), YoutubePlayerActivity.class)
                .putExtra("video_id", VideoId));
    }

    @Override
    public void newsUrl(String url, String Id) {
        newsUrl = url;
        startActivity(new Intent(getActivity(), NewsViewer.class)
                .putExtra("newsUrl", newsUrl));

    }

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

}


