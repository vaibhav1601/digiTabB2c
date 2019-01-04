package z_aksys.solutions.digiappequitybb.custom;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class CustomViewPager extends ViewPager {

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    int getMeasureExactly(View child, int widthMeasureSpec) {
        child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        int height = child.getMeasuredHeight();
        return MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        boolean wrapHeight = MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST;

        final View tab = getChildAt(0);
        if (tab == null) {
            return;
        }

        int width = getMeasuredWidth();
        if (wrapHeight) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        }
        Fragment fragment = ((Fragment) getAdapter().instantiateItem(this, getCurrentItem()));
        heightMeasureSpec = getMeasureExactly(fragment.getView(), widthMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}