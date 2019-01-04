package z_aksys.solutions.digiappequitybb.Fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import z_aksys.solutions.digiappequitybb.API.PitchServices;
import z_aksys.solutions.digiappequitybb.API.RetrofitClient;
import z_aksys.solutions.digiappequitybb.Adapter.GoalAdapter;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.Response.EmployeeProfileResponse;
import z_aksys.solutions.digiappequitybb.utils.AngelPitchUtil;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

import static z_aksys.solutions.digiappequitybb.utils.Constants.appKey;
import static z_aksys.solutions.digiappequitybb.utils.Constants.appSec;
import static z_aksys.solutions.digiappequitybb.utils.Constants.user;

public class GoalFragment extends DialogFragment implements View.OnClickListener {

    public static final String TAG = "goal";
    public static PitchServices pitchServices;
    RecyclerView recycler_view_goal;
    List<String> goalList = new ArrayList<>();
    private GoalAdapter goalAdapter;
    private GridLayoutManager mGridLayoutManager;
    private TextView txt_emp_name, txt_emp_number, txt_logout, txt_emp_hide;
    private ProgressDialog progressDialog;
    private EmployeeProfileResponse.employee employee;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle mBundle = getArguments();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goal, container, false);

        recycler_view_goal = (RecyclerView) view.findViewById(R.id.recycler_view_goal);
        txt_emp_hide = (TextView) view.findViewById(R.id.txt_hide);

        mGridLayoutManager = new GridLayoutManager(getContext(), 3);
        recycler_view_goal.setLayoutManager(mGridLayoutManager);
        // recycler_view_goal.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(10), true));
        recycler_view_goal.setItemAnimator(new DefaultItemAnimator());
        pitchServices = RetrofitClient.getInstance().getApi();


        if (AngelPitchUtil.checkConnection(getActivity())) {
            getGoalDeatils();

        } else {
            Toast.makeText(getActivity(), "please connect the internet", Toast.LENGTH_LONG).show();

        }
        return view;
    }

    private void getGoalDeatils() {
        showProgress();

        Call<EmployeeProfileResponse> call = pitchServices.getEmployee("application/json", appKey, appSec, user);
        call.enqueue(new Callback<EmployeeProfileResponse>() {
            @Override
            public void onResponse(Call<EmployeeProfileResponse> call, Response<EmployeeProfileResponse> response) {
                //  Toast.makeText(getContext(), "sucessfeedback>>>>>>>>>>" + response.message(), Toast.LENGTH_LONG).show();

                if (response.body().getStatus().contentEquals("true")) {
                    employee = response.body().getEmployee();
                    initView(employee);

                    hideProgress();
                } else {


                    hideProgress();
                }

            }

            @Override
            public void onFailure(Call<EmployeeProfileResponse> call, Throwable t) {
                hideProgress();
                Toast.makeText(getContext(), "Fail" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // set click listener

        txt_emp_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {

        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setWindowAnimations(
                    R.style.DialogTheme);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().getAttributes().gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    private void initView(EmployeeProfileResponse.employee employeeList) {

        if (ObjectUtils.isNotNull(employeeList)) {
            goalList.add("z-aksys");
            goalList.add("z-aksys");
            goalList.add("z-aksys");
            goalList.add("z-aksys");
            goalList.add("z-aksys");
            goalList.add("z-aksys");

            goalAdapter = new GoalAdapter(getContext(), goalList, employeeList, this);
            goalAdapter.notifyDataSetChanged();
            recycler_view_goal.setAdapter(goalAdapter);

        } else {
            goalAdapter = new GoalAdapter(getContext(), goalList, employeeList, this);
        }


    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

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


    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}