package com.example.alireza.parking;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Method;

/**
 * Created by AliReza on 5/2/2017.
 */

public class SetAppFont {
    static Typeface mFont;
    ViewGroup mContainer;
    public SetAppFont(Context context, ViewGroup mContainer){
        mFont = Typeface.createFromAsset(context.getAssets(),
                "fonts/byekan+.ttf");
        setAppFont(mContainer);

    }
    void setAppFont(ViewGroup mContainer) {

        if (mContainer == null || mFont == null) return;

        final int mCount = mContainer.getChildCount();

        // Loop through all of the children.
        for (int i = 0; i < mCount; ++i) {
            final View mChild = mContainer.getChildAt(i);
            if (mChild instanceof TextView) {
                // Set the font if it is a TextView.
                ((TextView) mChild).setTypeface(mFont);
            } else if (mChild instanceof ViewGroup) {
                // Recursively attempt another ViewGroup.
                setAppFont((ViewGroup) mChild);
            } else {
                try {
                    Method mSetTypeface = mChild.getClass().getMethod("setTypeface", Typeface.class);
                    mSetTypeface.invoke(mChild, mFont);
                } catch (Exception e) { /* Do something... */ }
            }
        }

    }
}
