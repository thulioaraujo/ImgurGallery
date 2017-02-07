package com.mobilab.imgurgallery.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.mobilab.imgurgallery.BuildConfig;
import com.mobilab.imgurgallery.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * This activity shows a dialog with informations about the application
 *
 * @author Thúlio Araújo (thuliolins@gmail.com)
 * @since 2/6/2017
 */
public class AboutActivity extends AppCompatActivity {

    @InjectView(R.id.appVersion)
    TextView appVersion;
    @InjectView(R.id.appBuildTime)
    TextView appBuildTime;
    @InjectView(R.id.btn_close)
    Button btnClose;

    private String buildTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_about_dialog);
        ButterKnife.inject(this);

        this.appVersion.setText(BuildConfig.VERSION_NAME);
        String buildDate = SimpleDateFormat.getInstance().format(new Date(BuildConfig.TIMESTAMP));
        this.appBuildTime.setText(buildDate);

        this.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
