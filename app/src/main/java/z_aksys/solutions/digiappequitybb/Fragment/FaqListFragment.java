package z_aksys.solutions.digiappequitybb.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import z_aksys.solutions.digiappequitybb.API.PitchServices;
import z_aksys.solutions.digiappequitybb.API.RetrofitClient;
import z_aksys.solutions.digiappequitybb.Adapter.FaqAdapter;
import z_aksys.solutions.digiappequitybb.Adapter.FaqSearchAdapter;
import z_aksys.solutions.digiappequitybb.App;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.FaqResponse;
import z_aksys.solutions.digiappequitybb.Response.FaqSearchResponse;
import z_aksys.solutions.digiappequitybb.listener.OnClicklistener;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;
import z_aksys.solutions.digiappequitybb.utils.AngelSharedPrefance;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

import static z_aksys.solutions.digiappequitybb.utils.Constants.appKey;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appSec;
import static z_aksys.solutions.digiappequitybb.utils.Constants.user;

;

public class FaqListFragment extends Fragment implements OnClicklistener, SearchView.OnQueryTextListener {

    RecyclerView.LayoutManager mLayoutManager;
    PitchServices pitchServices;
    private RecyclerView recyclerView;
    private FaqAdapter faqAdapter;
    private FaqSearchAdapter faqSearchAdapter;
    private List<FaqResponse.faq> faqList;
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
    private AngelSharedPrefance sharedPrefance;
    private ImageView imgvoice;
    private static final int RECOGNIZER_REQ_CODE = 1234;

    public FaqListFragment() {
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
        final View view = inflater.inflate(R.layout.faq_list_frgament, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_faq);
        mLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(60), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        simpleSearchView = (SearchView) view.findViewById(R.id.simpleSearchView); // inititate a search view
        simpleSearchView.setQueryHint("Search "); // set the hint text to display in the query text field

        imgvoice=(ImageView)view.findViewById(R.id.imgvoice) ;

        simpleSearchView.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
        pitchServices = RetrofitClient.getInstance().getApi();
        sharedPrefance = new AngelSharedPrefance(App.getContext());

        if (AngelPitchUtil.checkConnection(getActivity())) {
            getFaq();
        } else {
            Toast.makeText(getActivity(), "please connect the internet", Toast.LENGTH_LONG).show();
        }

        imgvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

                try {
                    startActivityForResult(intent, 1);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getContext(), "Oops! Your device doesn't support Speech to Text",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1: {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    String yourResult = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);

                    callSearchServices(yourResult);

                    //Toast.makeText(getContext(),"search"+yourResult,Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }


    private void getFaq() {

        showProgress();

        Call<FaqResponse> myResponsecall = pitchServices.getFaq("application/json", appKey, appSec, user, firstPage, pageLimit);

        myResponsecall.enqueue(new Callback<FaqResponse>() {
            @Override
            public void onResponse(Call<FaqResponse> call, Response<FaqResponse> response) {

                if (response.isSuccessful()) {

                    faqList = new ArrayList<>();
                    faqList = response.body().getFaqs();

                    if (!ObjectUtils.isEmpty(faqList)) {
                        callAdaptor();
                    }

                    hideProgress();
                }

            }

            @Override
            public void onFailure(Call<FaqResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
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
        Call<FaqResponse> myResponsecall = pitchServices.getFaq("application/json", appKey, appSec, user, firstPage, pageLimit);

        myResponsecall.enqueue(new Callback<FaqResponse>() {
            @Override
            public void onResponse(Call<FaqResponse> call, Response<FaqResponse> response) {

                if (response.isSuccessful()) {

                    faqList = new ArrayList<>();
                    faqList = response.body().getFaqs();
                    hasNext = response.body().getHasNext();

                    if (!ObjectUtils.isEmpty(faqList)) {
                        callAdaptor();
                    }


                    hideProgress();
                }

            }

            @Override
            public void onFailure(Call<FaqResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
                hideProgress();

            }
        });

    }

    private void callAdaptor() {

        faqAdapter = new FaqAdapter(getContext(), faqList, this);
        faqAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(faqAdapter);

    }

    @Override
    public void lessonId(String Id) {
        Bundle bundle = new Bundle();
        //  bundle.putSerializable(Constants.lESSIONS, new ArrayList<LearnResponse.lessons>(lessonsArrayList));
        // bundle.putSerializable(Constants.QUESTIONS, new ArrayList<LearnResponse.questions>(questionsArrayList));
        bundle.putString("ID", Id);
        if (!TextUtils.isEmpty(Id)) {
            FaqDetailsFragment fragment2 = new FaqDetailsFragment();
            fragment2.setArguments(bundle);
            faqFragment.loadFragment(fragment2, true);
        }

    }

    public void setParentFragment(FaqFragment faqFragment) {
        this.faqFragment = faqFragment;
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
    public boolean onQueryTextSubmit(String s) {
        if (AngelPitchUtil.checkConnection(getActivity())) {
            callSearchServices(s);

        } else {
            Toast.makeText(getActivity(), "please connect the internet", Toast.LENGTH_LONG).show();

        }



       /* if (AngelPitchUtil.checkConnection(getActivity())) {

            Bundle bundle = new Bundle();
            bundle.putString("SearchKey", s);
            FaqSearchListFragment fragment2 = new FaqSearchListFragment();
            fragment2.setArguments(bundle);
            replaceFragment(fragment2, true);

        }else {
            Toast.makeText(getActivity(), "please connect the internet", Toast.LENGTH_LONG).show();

        }*/


        return false;
    }

    private void callSearchServices(String query) {
        showProgress();

        Call<FaqSearchResponse> call = pitchServices.getFaqSearch("application/json", appKey, appSec, user, query);
        call.enqueue(new Callback<FaqSearchResponse>() {
            @Override
            public void onResponse(Call<FaqSearchResponse> call, Response<FaqSearchResponse> response) {
                if (response.isSuccessful()) {
                    hideProgress();

                    if (response.body().getMessage().equalsIgnoreCase("Sucess query")) {
                        faq_searchList = new ArrayList<>();
                        faq_searchList = response.body().getFaq_searches();
                        sharedPrefance.setHelathAPI("FAQSEARCH", "");
                        sharedPrefance.setHelathAPI("FAQSEARCH", AngelPitchUtil.toJson(faq_searchList));
                        FaqSearchListFragment fragment2 = new FaqSearchListFragment();
                        replaceFragment(fragment2, true);
                        // callSearchAdaptor(faq_searchList);

                    } else if (response.body().getMessage().equalsIgnoreCase(" Not Record Found")) {
                        Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_LONG).show();

                    } else {

                        Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_LONG).show();

                    }

                }

            }

            @Override
            public void onFailure(Call<FaqSearchResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
                hideProgress();

            }
        });
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String text = s;
        //  Toast.makeText(getContext(),"serachText"+s,Toast.LENGTH_LONG).show();
        //adapter.filter(text);
        return false;
    }

    private void callSearchAdaptor(List<FaqSearchResponse.faq_search> faq_searchList) {

        mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(60), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
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
