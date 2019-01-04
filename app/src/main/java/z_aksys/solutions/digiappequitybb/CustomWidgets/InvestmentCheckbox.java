package z_aksys.solutions.digiappequitybb.CustomWidgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.utils.CurrencyFormatter;

public class InvestmentCheckbox extends RelativeLayout {

    private Context mContext;
    private CheckBox checkbox;
    private TextView tvCheckboxLabel;
    private TextView tvCheckboxValue;
    private CheckChangeListner checkChangeListner;

    private String label="";
    private long value=0;

    public InvestmentCheckbox(Context context) {
        super(context);
        init(context);
    }

    public InvestmentCheckbox(Context context, AttributeSet attrs) {

        super(context, attrs);
        readAttributes(context,attrs);
        init(context);
    }

    public InvestmentCheckbox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttributes(context,attrs);
        init(context);
    }

    private void readAttributes(Context context, AttributeSet attrs){
        TypedArray typedArray;
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.InvestmentCheckbox);
        label = typedArray
                .getString(R.styleable.InvestmentCheckbox_label);
        value= typedArray.getInt(R.styleable.InvestmentCheckbox_value, 0);

        typedArray.recycle();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InvestmentCheckbox(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        readAttributes(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        drawWidget(mContext);
    }


    private void drawWidget(Context context) {
        inflate(context, R.layout.template_investment_checkbox, this);

        checkbox=findViewById(R.id.checkbox);
        tvCheckboxLabel= findViewById(R.id.tv_label);
        tvCheckboxValue= findViewById(R.id.tv_value);

        tvCheckboxValue.setText(CurrencyFormatter.format(value));
        tvCheckboxLabel.setText(label);

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkChangeListner!=null){
                    checkChangeListner.onCheckChanged();
                }
            }
        });
    }

    public boolean getChecked(){
        return checkbox.isChecked();
    }

    public void setCheckChangeListner(CheckChangeListner checkChangeListner){
        this.checkChangeListner= checkChangeListner;
    }

    public interface CheckChangeListner{
        void onCheckChanged();
    }
}
