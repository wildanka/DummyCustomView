package com.example.customview.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

import com.example.customview.R;

public class CustomEditText extends AppCompatEditText {
    public Drawable mClearButtonImage;

    public CustomEditText(Context context) {
        super(context);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_close_black_24dp, null);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ((getCompoundDrawablesRelative()[2] != null)) {
                    float clearButtonStart;
                    float clearButtonEnd;
                    boolean isClearButtonClicked = false;
                    if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                        clearButtonEnd = mClearButtonImage.getIntrinsicWidth() + getPaddingStart();
                        if (event.getX() < clearButtonEnd) {
                            isClearButtonClicked = true;
                        }
                    } else {
                        clearButtonStart = (getWidth() - getPaddingEnd() - mClearButtonImage.getIntrinsicWidth());
                        if (event.getX() > clearButtonStart) {
                            isClearButtonClicked = true;
                        }
                    }
                    if (isClearButtonClicked) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_close_black_24dp, null);
                            showClearButton();
                            return true;
                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
                            mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_close_black_24dp, null);
                            if (getText() != null) {
                                getText().clear();
                            }
                            hideClearButton();
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
                return false;
            }
        });

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    showClearButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
    }

    private void showClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mClearButtonImage, null);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setHint("Masukkan nama Anda");
        setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
    }
}
