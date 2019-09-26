package com.by5388.demo.preference;


import android.os.Bundle;
import android.widget.Toast;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;


/**
 * @author Administrator  on 2019/9/26.
 */
public class MyPreferenceFragment extends PreferenceFragmentCompat
        implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
    private static final String KEY_CHECKBOX_1 = "my_checkbox_1";
    private static final String KEY_CHECKBOX_2 = "my_checkbox_2";
    private static final String KEY_CHECKBOX_3 = "my_checkbox_3";

    private CheckBoxPreference mCheckBoxPreference1;
    private CheckBoxPreference mCheckBoxPreference2;
    private CheckBoxPreference mCheckBoxPreference3;


    public static Fragment newInstance() {
        return new MyPreferenceFragment();
    }

    // TODO: 2019/9/26 如何动态的显示或者隐藏某一个Preference_Item ： 根据某一些配置文件


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // TODO: 2019/9/26 这里需要实现的是什么
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final String key = preference.getKey();
        if (KEY_CHECKBOX_1.equals(key)) {
            // TODO: 2019/9/26
            //ignore
        } else if (KEY_CHECKBOX_2.equals(key)) {
            // TODO: 2019/9/26
            boolean checked = (Boolean) newValue;
            if (checked) {
                getPreferenceScreen().addPreference(mCheckBoxPreference3);
            } else {
                getPreferenceScreen().removePreference(mCheckBoxPreference3);
            }
        } else if (KEY_CHECKBOX_3.equals(key)) {
            boolean checked = (Boolean) newValue;
            Toast.makeText(Objects.requireNonNull(getContext()), String.valueOf(checked), Toast.LENGTH_SHORT).show();
        }


        return true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_my_fragment);
        mCheckBoxPreference1 = findPreference(KEY_CHECKBOX_1);
        mCheckBoxPreference2 = findPreference(KEY_CHECKBOX_2);
        mCheckBoxPreference3 = findPreference(KEY_CHECKBOX_3);


        mCheckBoxPreference3.setOnPreferenceClickListener(this);
        mCheckBoxPreference2.setOnPreferenceClickListener(this);

        final boolean showCheckBox3 = Objects.requireNonNull(getContext()).getResources().getBoolean(R.bool.show_checkbox_3);
        if (!showCheckBox3) {
            //移除
            // FIXME: 2019/9/26 出现没办法隐藏的现象:无法动态配置选项的显示与否
//            getPreferenceScreen().removePreference(mCheckBoxPreference3);
        }


    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        final String key = preference.getKey();
        if (KEY_CHECKBOX_2.equals(key)) {
            if (true) {
                return super.onPreferenceTreeClick(preference);
            }
            final boolean checked = mCheckBoxPreference2.isChecked();
            Toast.makeText(Objects.requireNonNull(getContext()), String.valueOf(checked), Toast.LENGTH_SHORT).show();
            if (checked) {
                if (!mCheckBoxPreference3.isShown()) {
                    getPreferenceScreen().addPreference(mCheckBoxPreference3);
                }
            } else {
//                if (mCheckBoxPreference3.isShown()) {
                getPreferenceScreen().removePreference(mCheckBoxPreference3);
//                }
            }
            return true;
        }
        return false;
    }

}
