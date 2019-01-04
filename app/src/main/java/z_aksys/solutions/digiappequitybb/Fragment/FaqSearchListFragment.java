package z_aksys.solutions.digiappequitybb.Fragment;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import z_aksys.solutions.digiappequitybb.API.PitchServices;
import z_aksys.solutions.digiappequitybb.API.RetrofitClient;
import z_aksys.solutions.digiappequitybb.Adapter.FaqSearchAdapter;
import z_aksys.solutions.digiappequitybb.App;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.FaqSearchResponse;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;
import z_aksys.solutions.digiappequitybb.utils.AngelSharedPrefance;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

import static z_aksys.solutions.digiappequitybb.utils.Constants.appKey;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appSec;
import static z_aksys.solutions.digiappequitybb.utils.Constants.user;

;

public class FaqSearchListFragment extends Fragment {

    RecyclerView.LayoutManager mLayoutManager;
    PitchServices pitchServices;
    private RecyclerView recyclerView;
    private FaqSearchAdapter faqSearchAdapter;

    private List<FaqSearchResponse.faq_search> faq_searchList;
    private ProgressDialog progressDialog;
    private SearchView simpleSearchView;
    private int firstPage = 0;
    private int pageLimit = 10;
    private boolean isLoading = true;
    private int pastVisibleitem, visibleItemCount, totalItemCount, previousTotal = 0;
    private int viewtueshole = 100;
    private int hasNext;
    private FaqFragment faqFragment;
    private AppCompatTextView txtName;
    String query;
    private AngelSharedPrefance sharedPrefance;

    public FaqSearchListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.faq_search_frgament, container, false);
        txtName = (AppCompatTextView) view.findViewById(R.id.txtfaqdetailssearch);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_faq_search);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        txtName.setText("FAQ");
        pitchServices = RetrofitClient.getInstance().getApi();
        sharedPrefance = new AngelSharedPrefance(App.getContext());


        setData();

        txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }

    private void setData() {
        query = sharedPrefance.getHealthAPI("FAQSEARCH");

        if (!TextUtils.isEmpty(query)) {
            faq_searchList = new ArrayList<>();
            faq_searchList = AngelPitchUtil.deSerializeResponseList(query, FaqSearchResponse.faq_search.class);

        }


        if (!ObjectUtils.isEmpty(faq_searchList)) {
            callSearchAdaptor(faq_searchList);
        } else {
            Toast.makeText(getActivity(), "Data not fond", Toast.LENGTH_LONG).show();
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


    private void callSearchServices(String query) {
        showProgress();

        Call<FaqSearchResponse> call = pitchServices.getFaqSearch("application/json", appKey, appSec, user, query);
        call.enqueue(new Callback<FaqSearchResponse>() {
            @Override
            public void onResponse(Call<FaqSearchResponse> call, Response<FaqSearchResponse> response) {
                if (response.isSuccessful()) {
                    hideProgress();
                    faq_searchList = new ArrayList<>();
                    faq_searchList = response.body().getFaq_searches();
                    //callSearchAdaptor(faq_searchList);
                }

            }

            @Override
            public void onFailure(Call<FaqSearchResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
                hideProgress();

            }
        });
    }


    private void callSearchAdaptor(List<FaqSearchResponse.faq_search> faq_searchList) {

        faqSearchAdapter = new FaqSearchAdapter(getContext(), faq_searchList, this);
        faqSearchAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(faqSearchAdapter);

    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void showProgress() {
        if (progressDialog == null || (progressDialog != null && progressDialog.isShowing() == false)) {
            progressDialog = ProgressDialog.show(getActivity(), "", "Please wait.", true);
        }
    }

    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
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
}
