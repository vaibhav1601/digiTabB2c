package z_aksys.solutions.digiappequitybb.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import z_aksys.solutions.digiappequitybb.Adapter.FaqDetailsAdapter;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.FaqDetailsResponse;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

;import static z_aksys.solutions.digiappequitybb.utils.Constants.appKey;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appSec;
import static z_aksys.solutions.digiappequitybb.utils.Constants.user;

public class FaqDetailsFragment extends Fragment {

    RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recyclerView;
    private FaqDetailsAdapter faqDetailsAdapter;
    private List<FaqDetailsResponse.faq_detail> faqDetailList;
    private ProgressDialog progressDialog;
    private SearchView simpleSearchView;
    private int firstPage = 0;
    private int pageLimit = 10;
    private boolean isLoading = true;
    private int pastVisibleitem, visibleItemCount, totalItemCount, previousTotal = 0;
    private int viewtueshole = 100;
    private int hasNext;
    private String topicId;
    private AppCompatTextView txtName;
    private PitchServices pitchServices;


    public FaqDetailsFragment() {
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
        final View view = inflater.inflate(R.layout.faq_details_frgament, container, false);

        //Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        txtName = (AppCompatTextView) view.findViewById(R.id.txtfaqdetails);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_faq_details);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        pitchServices = RetrofitClient.getInstance().getApi();
        txtName.setText("FAQ");

        setData();

        if (AngelPitchUtil.checkConnection(getActivity())) {
            getFaqDeatils();

        } else {
            Toast.makeText(getActivity(), "please connect the internet", Toast.LENGTH_LONG).show();

        }

        txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }


    private void setData() {

        Bundle bundle = getArguments();

        if (ObjectUtils.isNotNull(bundle)) {


            if (bundle.containsKey("ID")) {
                //ObjectUtils.getIntFromString(id=bundle.getString("ID"));

                topicId = bundle.getString("ID");

            }
        }

    }

    private void getFaqDeatils() {
        showProgress();

        Call<FaqDetailsResponse> myResponsecall = pitchServices.getFaqDetails("application/json", appKey, appSec, user, firstPage, pageLimit, topicId);
        myResponsecall.enqueue(new Callback<FaqDetailsResponse>() {
            @Override
            public void onResponse(Call<FaqDetailsResponse> call, Response<FaqDetailsResponse> response) {

                if (response.isSuccessful()) {

                    faqDetailList = new ArrayList<>();
                    faqDetailList = response.body().getFaq_details();

                    if (!ObjectUtils.isEmpty(faqDetailList)) {
                        callAdaptor();
                    }
                    hideProgress();
                }

            }

            @Override
            public void onFailure(Call<FaqDetailsResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
                hideProgress();

            }
        });

      /*  recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                pastVisibleitem = ((RecyclerView.LayoutManager) mLayoutManager).findFirstVisibleItemPosition();

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
*/

    }

    private void callPagination() {
        showProgress();

        Call<FaqDetailsResponse> myResponsecall = pitchServices.getFaqDetails("application/json", appKey, appSec, user, firstPage, pageLimit, topicId);
        myResponsecall.enqueue(new Callback<FaqDetailsResponse>() {
            @Override
            public void onResponse(Call<FaqDetailsResponse> call, Response<FaqDetailsResponse> response) {

                if (response.isSuccessful()) {

                    faqDetailList = new ArrayList<>();
                    faqDetailList = response.body().getFaq_details();

                    if (!ObjectUtils.isEmpty(faqDetailList)) {
                        callAdaptor();
                    }
                    hideProgress();
                }

            }

            @Override
            public void onFailure(Call<FaqDetailsResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();
                hideProgress();

            }
        });

    }


    private void callAdaptor() {
        faqDetailsAdapter = new FaqDetailsAdapter(getContext(), faqDetailList, this);
        faqDetailsAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(faqDetailsAdapter);

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


}
