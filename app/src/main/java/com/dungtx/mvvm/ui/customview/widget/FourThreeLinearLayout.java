package com.dungtx.mvvm.ui.customview.widget;

import android.content.Context;
import android.util.AttributeSet;

import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.getSize;
import static android.view.View.MeasureSpec.makeMeasureSpec;

/**
 * A extension of ForegroundLinearLayout that is always 4:3 aspect ratio.
 */
public class FourThreeLinearLayout extends ForegroundLinearLayout {

    public FourThreeLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int fourThreeHeight = makeMeasureSpec(getSize(widthSpec) * 3 / 4, EXACTLY);
        super.onMeasure(widthSpec, fourThreeHeight);
    }

    @Override
    public boolean hasOverlappingRendering() {
        return false;
    }
}
