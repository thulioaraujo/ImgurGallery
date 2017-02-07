package com.mobilab.imgurgallery.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.mobilab.imgurgallery.R;
import com.mobilab.imgurgallery.model.PreferencesApplication;
import com.mobilab.imgurgallery.util.Constants;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * This configures the display parameter chosen by the user
 *
 * @author Thúlio Araújo (thuliolins@gmail.com)
 * @since 2/7/2017
 */
public class FilterActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @InjectView(R.id.sortRadioGroup)
    RadioGroup sortRadioGroup;
    @InjectView(R.id.windowRadioGroup)
    RadioGroup windowRadioGroup;
    @InjectView(R.id.viralCheckBox)
    CheckBox viralCheckBox;
    @InjectView(R.id.btn_apply)
    Button btnApply;
    @InjectView(R.id.btn_cancel)
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_filter_dialog);
        ButterKnife.inject(this);

        sortRadioGroup.setOnCheckedChangeListener(this);
        windowRadioGroup.setOnCheckedChangeListener(this);

        this.viralCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                PreferencesApplication.savePreferences(getApplicationContext(), PreferencesApplication.VIRAL, String.valueOf(checked));
            }
        });

        this.btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK, null);
                finish();
            }
        });

        this.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED, null);
                finish();
            }
        });

        setDefaults();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        // Find which radio button is selected
        if (checkedId == R.id.sortViral) {
            PreferencesApplication.savePreferences(this, PreferencesApplication.SORT,
                    PreferencesApplication.Sort.VIRAL.getValue());
        } else if (checkedId == R.id.sortTop) {
            PreferencesApplication.savePreferences(this, PreferencesApplication.SORT,
                    PreferencesApplication.Sort.TOP.getValue());
        } else if (checkedId == R.id.sortTime) {
            PreferencesApplication.savePreferences(this, PreferencesApplication.SORT,
                    PreferencesApplication.Sort.TIME.getValue());
        } else if (checkedId == R.id.sortDay) {
            PreferencesApplication.savePreferences(this, PreferencesApplication.WINDOW,
                    PreferencesApplication.Window.DAY.getValue());
        } else if (checkedId == R.id.sortWeek) {
            PreferencesApplication.savePreferences(this, PreferencesApplication.WINDOW,
                    PreferencesApplication.Window.WEEK.getValue());
        } else if (checkedId == R.id.sortMonth) {
            PreferencesApplication.savePreferences(this, PreferencesApplication.WINDOW,
                    PreferencesApplication.Window.MONTH.getValue());
        } else if (checkedId == R.id.sortYear) {
            PreferencesApplication.savePreferences(this, PreferencesApplication.WINDOW,
                    PreferencesApplication.Window.YEAR.getValue());
        } else if (checkedId == R.id.sortAll) {
            PreferencesApplication.savePreferences(this, PreferencesApplication.WINDOW,
                    PreferencesApplication.Window.ALL.getValue());
        }
    }

    /**
     * Starts the activity components with the last choice
     */
    public void setDefaults() {

        //Load variables from User Shared Preferences
        SharedPreferences sharedPref = getApplicationContext().
                getSharedPreferences(Constants.APPLLICATION_NAME, Context.MODE_PRIVATE);
        String sort = sharedPref.getString(PreferencesApplication.SORT,
                PreferencesApplication.Sort.VIRAL.getValue());
        String window = sharedPref.getString(PreferencesApplication.WINDOW,
                PreferencesApplication.Window.DAY.getValue());
        String showViral = sharedPref.getString(PreferencesApplication.VIRAL, PreferencesApplication.VIRAL_TRUE);

        if (sort.equals(PreferencesApplication.Sort.VIRAL.getValue())) {
            sortRadioGroup.check(R.id.sortViral);
        } else if (sort.equals(PreferencesApplication.Sort.TOP.getValue())) {
            sortRadioGroup.check(R.id.sortTop);
        } else if (sort.equals(PreferencesApplication.Sort.TIME.getValue())) {
            sortRadioGroup.check(R.id.sortTime);
        }

        if (window.equals(PreferencesApplication.Window.DAY.getValue())) {
            windowRadioGroup.check(R.id.sortDay);
        } else if (window.equals(PreferencesApplication.Window.WEEK.getValue())) {
            windowRadioGroup.check(R.id.sortWeek);
        } else if (window.equals(PreferencesApplication.Window.MONTH.getValue())) {
            windowRadioGroup.check(R.id.sortMonth);
        } else if (window.equals(PreferencesApplication.Window.YEAR.getValue())) {
            windowRadioGroup.check(R.id.sortYear);
        } else if (window.equals(PreferencesApplication.Window.ALL.getValue())) {
            windowRadioGroup.check(R.id.sortAll);
        }

        if (showViral.equals(PreferencesApplication.VIRAL_TRUE)) {
            viralCheckBox.setChecked(true);
        } else {
            viralCheckBox.setChecked(false);
        }
    }
}
