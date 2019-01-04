package z_aksys.solutions.digiappequitybb.CustomWidgets;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import z_aksys.solutions.digiappequitybb.R;

public class RangeEditText extends RelativeLayout {

    private ImageView btnMinus, btnPlus;
    private EditText editText;
    private TextView tvLabel;
    private String label;
    private Context mContext;
    private RecyclerView rvOptionsList;
    private RangeEditIncrementOptionsListAdapter rangeEditIncrementOptionsListAdapter;
    private FormatValueListener mFormatValueListener;
    private ValueChangeListener mValueChangeListener;
    private FormatIncrementOptionListener mFormatIncrementOptionListener;
    private int[] mIncrementOptions;

    private int currentValue=0;
    private int step=1;

    public RangeEditText(Context context) {
        this(context, null);
    }

    public RangeEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RangeEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RangeEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        drawWidget(mContext);
    }

    /**
     * Set label of edittext
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
        tvLabel.setVisibility(VISIBLE);
        tvLabel.setText(label);
    }

    /**
     * Set increment and decrement step size
     * @param step
     */
    public void setStep(int step){
        this.step= step;
    }

    /**
     * Set provided value in edit text
     * @param value
     */
    public void setValue(int value){
        currentValue= value;
        setEditTextValue(currentValue);
    }

    /**
     * Set quick increment options
     * @param options
     */
    public void setIncrementOptions(int[] options) {
        this.mIncrementOptions = options;
    }

    /**
     * Adds value in edittext by the increment option
     * on index provided
     * @param selectedIndex
     */
    public void addValueByIncrementOptionOnIndex(int selectedIndex) {

        if (mIncrementOptions.length>selectedIndex) {
            currentValue = currentValue + mIncrementOptions[selectedIndex];
            setEditTextValue(currentValue);
        }
    }

    public void onIncrementOptionClicked(int selectedIndex) {
        setEditTextValue(selectedIndex);
    }

    public void setFormatDisplayText(FormatValueListener formatValueListener) {
        this.mFormatValueListener = formatValueListener;
    }

    private void drawWidget(Context context) {

        inflate(mContext, R.layout.template_range_edittext, this);

        btnMinus = findViewById(R.id.btn_reduce);
        btnPlus = findViewById(R.id.btn_rise);
        editText = findViewById(R.id.edit_text);
        tvLabel = findViewById(R.id.tv_label);

        btnMinus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentValue-step > 0) {
                    currentValue= currentValue-step;
                    setEditTextValue(currentValue);
                }
            }
        });

        btnPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                currentValue= currentValue+step;
                setEditTextValue(currentValue);
            }
        });

        setFormatDisplayText(new FormatValueListener() {
            @Override
            public String onFormatValue(int value) {
                return String.valueOf(value);
            }
        });
    }

    private String formatDisplayText(int value) {
        return mFormatValueListener.onFormatValue(value);
    }

    /**
     * Populate quick increment option list
     */
    public void populateIncrementOptionsList() {

        if (mIncrementOptions.length>0) {
            rvOptionsList = findViewById(R.id.rv_options_list);
            rvOptionsList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            rangeEditIncrementOptionsListAdapter = new RangeEditIncrementOptionsListAdapter();
            rvOptionsList.setAdapter(rangeEditIncrementOptionsListAdapter);
            rvOptionsList.setVisibility(VISIBLE);
        }else {
            rvOptionsList.setVisibility(GONE);
        }
    }

    /**
     * Set formatted string value into edittext
     * @param value
     */
    private void setEditTextValue(int value) {
        editText.setText(formatDisplayText(value));

        // Emit value change event
        if (mValueChangeListener!=null){
            mValueChangeListener.onValueChange(value);
        }
    }

    /**
     * Get current int value in edittext
     * @return
     */
    public int getValue() {
        return currentValue;
    }

    public void setOnValueChangeListener(ValueChangeListener valueChangeListener){
        this.mValueChangeListener= valueChangeListener;
    }

    public void setOnFormatIncrementOptionListener(FormatIncrementOptionListener formatIncrementOptionListener){
        this.mFormatIncrementOptionListener= formatIncrementOptionListener;
    }

    public interface FormatValueListener {
        String onFormatValue(int value);
    }

    public interface ValueChangeListener {
        void onValueChange(int value);
    }

    public interface FormatIncrementOptionListener {
        String onFormatOption(int value);
    }

    /****************************************************************************
     * Increment Options Adapter
     ****************************************************************************/
    private class RangeEditIncrementOptionsListAdapter extends RecyclerView.Adapter<RangeEditIncrementOptionsListAdapter.ViewHolder> {


        RangeEditIncrementOptionsListAdapter() {

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.template_investment_amount_option, parent, false);

            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            if (mFormatIncrementOptionListener!=null) {
                holder.tvInvestmentOption.setText(mFormatIncrementOptionListener.onFormatOption(mIncrementOptions[position]));
            }else {
                holder.tvInvestmentOption.setText(mIncrementOptions[position]);
            }
            holder.tvInvestmentOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentValue= currentValue+mIncrementOptions[position];
                    onIncrementOptionClicked(currentValue);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mIncrementOptions.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView tvInvestmentOption;

            public ViewHolder(View view) {
                super(view);
                this.tvInvestmentOption = (TextView) view;
            }
        }
    }
}
