package com.by5388.demo.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

/**
 * <p>自定义一个偏好选项</p>
 * 参考（copy）自 API_demo 中 Preference.MyPreference
 *
 * @author Administrator  on 2019/9/26.
 */
public class CustomPreference extends Preference {
    private static final String TAG = "CustomPreference";
    private int mCount;

    public CustomPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWidgetLayoutResource(R.layout.widget_custom_prefrence);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        final TextView textView = (TextView) holder.findViewById(R.id.textView_custom_preference);
        if (textView != null) {
            textView.setText(String.valueOf(mCount));
        }
    }

    @Override
    protected void onClick() {
        int newValue = mCount + 1;
        if (!callChangeListener(newValue)) {
            // 2019/9/26 检测到没有发生变化？正常情况下应该不会出现
            return;
        }
        mCount = newValue;

        //持久化保持新的值
        persistInt(mCount);
        notifyChanged();

    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        // TODO: 2019/9/26 TypedArray 能够从layout.xml中读取组件的属性信息，从而作用到UI上，
        //  类似于自定义view中的自定义属性
        //这里仅有的一个值是 整型
        return a.getInteger(index, 0);
    }

    @Override
    protected void onSetInitialValue(Object defaultValue) {

        mCount = getPersistedInt(mCount);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Parcelable parcelable = super.onSaveInstanceState();
        // TODO: 2019/9/26 已经持久化？
        if (isPersistent()) {
            return parcelable;
        }
        // TODO: 2019/9/26 保存对象，数据
        final SavedState savedState = new SavedState(parcelable);
        // TODO: 2019/9/26 赋值写反了 mCount =  savedState.mSaveCount;
        savedState.mSaveCount = mCount;
        return savedState;
    }


    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        // TODO: 2019/9/26 这种比较类名的形式与 instanceof 的差别：instanceof 可以判断接口类型
        //  ，类名比较只能是具体的某一个类，子类不行，父类也不行
        if (!state.getClass().equals(SavedState.class)) {
            // TODO: 2019/9/26 非预期数据
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState savedState = (SavedState) state;
        // TODO: 2019/9/26 ? 为何需要添加这句
        super.onRestoreInstanceState(savedState.getSuperState());
        mCount = savedState.mSaveCount;
        notifyChanged();
    }

    private static class SavedState extends BaseSavedState {
        int mSaveCount;

        public static final Parcelable.Creator<SavedState> CREATOR = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel source) {
                return new SavedState(source);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };

        SavedState(Parcel source) {
            super(source);
            mSaveCount = source.readInt();
        }

        SavedState(Parcelable superState) {
            super(superState);
        }


        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(mSaveCount);
        }
    }
}
