package z_aksys.solutions.digiappequitybb.Activity;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;

import z_aksys.solutions.digiappequitybb.Adapter.LessonsAdapter;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.LearnResponse;
import z_aksys.solutions.digiappequitybb.listener.OnClickLessons;
import z_aksys.solutions.digiappequitybb.utils.Constants;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

public class LessionsActivity extends AppCompatActivity implements OnClickLessons {

    RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recycler_view_lessons;
    private LessonsAdapter lessonsAdapter;
    private ProgressDialog progressDialog;
    private ArrayList<LearnResponse.lessons> lessonsArrayList;
    private ArrayList<LearnResponse.questions> questionsArrayList;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news_upadte);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Lato-Bold.ttf");


        recycler_view_lessons = findViewById(R.id.recycler_view_lessions);

        setData();


    }


    private void setData() {

        Bundle bundle = getIntent().getExtras();

        if (ObjectUtils.isNotNull(bundle)) {


            if (bundle.containsKey("ID")) {
                //ObjectUtils.getIntFromString(id=bundle.getString("ID"));

                id = bundle.getInt("ID");

            }


            if (bundle.containsKey(Constants.lESSIONS)) {
                lessonsArrayList = (ArrayList<LearnResponse.lessons>) bundle.getSerializable(Constants.lESSIONS);

                // lessonsAdapter = new LessonsAdapter(getApplicationContext(), lessonsArrayList, id);
                lessonsAdapter.notifyDataSetChanged();
                mLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
                recycler_view_lessons.setLayoutManager(mLayoutManager);
                recycler_view_lessons.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(60), true));
                recycler_view_lessons.setItemAnimator(new DefaultItemAnimator());
                recycler_view_lessons.setAdapter(lessonsAdapter);


            }


            if (bundle.containsKey(Constants.QUESTIONS)) {
                questionsArrayList = (ArrayList<LearnResponse.questions>) bundle.getSerializable(Constants.QUESTIONS);

            }


        }
    }


    @Override
    public void lessonId(String Id, int position) {

    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
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

   /* public void showProgress() {
        if (getApplication().isAdded() && (progressDialog == null || (progressDialog != null && progressDialog.isShowing() == false))) {
            progressDialog = ProgressDialog.show(getApplicationContext(), "", "Please wait.", true);
        }
    }

    public void hideProgress() {
        if (isAdded() && progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        } else {
            return;
        }
    }*/

}
