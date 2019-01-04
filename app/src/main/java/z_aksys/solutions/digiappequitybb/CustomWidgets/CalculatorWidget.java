package z_aksys.solutions.digiappequitybb.CustomWidgets;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.NumberFormat;

import z_aksys.solutions.digiappequitybb.R;

public class CalculatorWidget extends LinearLayout {

    private Context mContext;
    private LinearLayout llContainer;
    private String mWidgetTitle = "", mNumbersLabel = "", mAmountLabel = "", mDurationLabel = "";
    private TextView tvWidgetTitle, tvWidgetAmount, tvWidgetInvestmentDetails;
    private ImageView ivWidgetIcon;
    private Drawable mWidgetIconResourceId;
    private TextView mTvDialogResult;
    private Calculator calculator;
    private int mStepAmount, mStepDuration, mStepNumbers, mValueAmount, mValueDuration, mValueNumbers;
    private boolean mIsShowAmount, mIsShowDuration, mIsShowNumbers;

    private int numbers = 0, amount = 0, duration = 0;
    private long totalEarning = 0;

    public CalculatorWidget(Context context) {
        super(context);
        init(context);
    }

    public CalculatorWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttributes(context, attrs);
        init(context);
    }

    public CalculatorWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttributes(context, attrs);
        init(context);
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
        totalEarning = calculator.calculateTotalEarnings(numbers, amount, duration);
        setResultInDailog(totalEarning);
        setInvestmentSummary();
    }

    private void readAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray;
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.CalculatorWidget);
        mWidgetTitle = typedArray
                .getString(R.styleable.CalculatorWidget_title);
        mWidgetIconResourceId = typedArray.getDrawable(R.styleable.CalculatorWidget_icon);

        mNumbersLabel = typedArray.getString(R.styleable.CalculatorWidget_label_numbers);
        mAmountLabel = typedArray.getString(R.styleable.CalculatorWidget_label_amount);
        mDurationLabel = typedArray.getString(R.styleable.CalculatorWidget_label_duration);

        mStepAmount = typedArray.getInt(R.styleable.CalculatorWidget_step_amount, 10000);
        mStepDuration = typedArray.getInt(R.styleable.CalculatorWidget_step_duration, 12);
        mStepNumbers = typedArray.getInt(R.styleable.CalculatorWidget_step_numbers, 1);

        mValueAmount = typedArray.getInt(R.styleable.CalculatorWidget_value_amount, 10000);
        mValueDuration = typedArray.getInt(R.styleable.CalculatorWidget_value_duration, 12);
        mValueNumbers = typedArray.getInt(R.styleable.CalculatorWidget_value_numbers, 1);

        mIsShowAmount = typedArray.getBoolean(R.styleable.CalculatorWidget_is_show_amount, false);
        mIsShowDuration = typedArray.getBoolean(R.styleable.CalculatorWidget_is_show_duration, false);
        mIsShowNumbers = typedArray.getBoolean(R.styleable.CalculatorWidget_is_show_numbers, false);

        typedArray.recycle();
    }

    private void init(Context context) {
        this.mContext = context;
        inflate(mContext, R.layout.template_calculator_widget, this);
        llContainer = findViewById(R.id.container);
        tvWidgetTitle = findViewById(R.id.tv_widget_title);
        tvWidgetTitle.setText(mWidgetTitle);

        ivWidgetIcon = findViewById(R.id.ic_widget_icon);
        ivWidgetIcon.setImageDrawable(mWidgetIconResourceId);

        tvWidgetAmount = findViewById(R.id.tv_widget_amount);
        tvWidgetInvestmentDetails = findViewById(R.id.tv_widget_investment_details);

        Dialog dialog = new Dialog(mContext);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_calculator_widget);
        TextView tvDailogTitle = dialog.findViewById(R.id.tv_dialog_title);
        tvDailogTitle.setText(mWidgetTitle);

        ImageView icClose = dialog.findViewById(R.id.ic_remove_icon);
        icClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        RangeEditText retNumbers = dialog.findViewById(R.id.ret_numbers);

        if (mIsShowNumbers) {
            retNumbers.setStep(mStepNumbers);
            retNumbers.setOnValueChangeListener(value -> {

                numbers = value;
                if (calculator != null) {
                    totalEarning = calculator.calculateTotalEarnings(numbers, amount, duration);
                    setResultInDailog(totalEarning);
                    setInvestmentSummary();
                }
            });
            retNumbers.setValue(mValueNumbers);
            retNumbers.setLabel(mNumbersLabel);
            retNumbers.setVisibility(VISIBLE);
        }else {
            retNumbers.setVisibility(GONE);
        }

        RangeEditText retAmount = dialog.findViewById(R.id.ret_investment);

        if (mIsShowAmount) {
            retAmount.setStep(mStepAmount);
            retAmount.setOnValueChangeListener((value) -> {

                amount = value;
                if (calculator != null) {
                    totalEarning = calculator.calculateTotalEarnings(numbers, amount, duration);
                    setResultInDailog(totalEarning);
                    setInvestmentSummary();
                }
            });
            retAmount.setFormatDisplayText(new RangeEditText.FormatValueListener() {
                @Override
                public String onFormatValue(int value) {
                    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
                    return currencyFormatter.format(value);
                }
            });
            retAmount.setValue(mValueAmount);
            retAmount.setLabel(mAmountLabel);
            retAmount.setVisibility(VISIBLE);
        }else {
            retAmount.setVisibility(GONE);
        }

        RangeEditText retTenure = dialog.findViewById(R.id.ret_tenure);

        if (mIsShowDuration) {
            retTenure.setStep(mStepDuration);
            retTenure.setOnValueChangeListener((value) -> {

                duration = value;
                if (calculator != null) {
                    totalEarning = calculator.calculateTotalEarnings(numbers, amount, duration);
                    setResultInDailog(totalEarning);
                    setInvestmentSummary();
                }
            });
            retTenure.setFormatDisplayText(new RangeEditText.FormatValueListener() {
                @Override
                public String onFormatValue(int value) {
                    return value + " month";
                }
            });
            retTenure.setValue(mValueDuration);
            retTenure.setLabel(mDurationLabel);
            retTenure.setVisibility(VISIBLE);
        }else {
            retTenure.setVisibility(GONE);
        }

        mTvDialogResult = dialog.findViewById(R.id.tv_total_earning);

        setInvestmentSummary();

        llContainer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }

    private void setResultInDailog(long result) {
        NumberFormat currenyFormatter = NumberFormat.getCurrencyInstance();

        if (mTvDialogResult != null) {
            mTvDialogResult.setText(Html.fromHtml("Total Earning: <b>" + currenyFormatter.format(result) + "</b>"));
        }
    }

    private void setInvestmentSummary() {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        tvWidgetAmount.setText(currencyFormatter.format(totalEarning));

        if (calculator != null) {
            tvWidgetInvestmentDetails.setText(calculator.getSummaryString(numbers, amount, duration));
        }
    }

    public interface Calculator {
        long calculateTotalEarnings(int numbers, int amount, int duration);

        String getSummaryString(int numbers, int amount, int duration);
    }
}
