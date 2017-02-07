package com.mobilab.imgurgallery.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.mobilab.imgurgallery.R;
import com.mobilab.imgurgallery.model.PreferencesApplication;
import com.mobilab.imgurgallery.util.Constants;
import com.mobilab.imgurgallery.util.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Main activity which manages the application control
 *
 * @author Thúlio Araújo (thuliolins@gmail.com)
 * @since 2/6/2017
 */
public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private static final String MENU_ITEM_SELECTED = "MENU_ITEM_SELECTED";
    private static final String RADIO_BUTTON_SELECTED = "RADIO_BUTTON_SELECTED";

    @InjectView(R.id.radioGroup)
    RadioGroup radioGroup;
    private int menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (!Utils.isNetworkAvailable(this)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
            builder.setTitle("Internet Connection");
            builder.setMessage("There is a problem with your internet connection! Please check your connection and restart the application!");
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.show();
        } else {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent(getApplicationContext(), FilterActivity.class);
                    startActivityForResult(it, 0);
                }
            });

            radioGroup.setOnCheckedChangeListener(this);

            if (savedInstanceState != null) {
                menuItem = savedInstanceState.getInt(MENU_ITEM_SELECTED);
                radioGroup.check(savedInstanceState.getInt(RADIO_BUTTON_SELECTED));
                displayView(menuItem);
            } else {
                displayView(R.id.grid_view);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            displayView(menuItem);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        // Find which radio button is selected
        if (checkedId == R.id.hotBtn) {
            PreferencesApplication.savePreferences(this, PreferencesApplication.SECTION,
                    PreferencesApplication.Section.HOT.getValue());
            displayView(menuItem);
        } else if (checkedId == R.id.topBtn) {
            PreferencesApplication.savePreferences(this, PreferencesApplication.SECTION,
                    PreferencesApplication.Section.TOP.getValue());
            displayView(menuItem);
        } else if (checkedId == R.id.userBtn) {
            PreferencesApplication.savePreferences(this, PreferencesApplication.SECTION,
                    PreferencesApplication.Section.USER.getValue());
            displayView(menuItem);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        menuItem = item.getItemId();

        switch (menuItem) {
            case (R.id.grid_view):
                displayView(menuItem);
                break;

            case (R.id.list_view):
                displayView(menuItem);
                break;

            case (R.id.grid_staggered):
                displayView(menuItem);
                break;

            case (R.id.about):
                Intent it = new Intent(this, AboutActivity.class);
                startActivity(it);
                break;
        }

        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(MENU_ITEM_SELECTED, menuItem);
        outState.putInt(RADIO_BUTTON_SELECTED, radioGroup.getCheckedRadioButtonId());
    }

    public void displayView(int viewId) {

        // Handle navigation view item clicks here.
        Fragment fragment = null;
        Class fragmentClass = null;
        Bundle args = new Bundle();

        switch (viewId) {
            case (R.id.grid_view):
                fragmentClass = ViewFragment.class;
                args.putInt(Constants.RECYCLER_VIEW_TYPE, R.id.grid_view);
                break;
            case (R.id.list_view):
                fragmentClass = ViewFragment.class;
                args.putInt(Constants.RECYCLER_VIEW_TYPE, R.id.list_view);
                break;
            case (R.id.grid_staggered):
                fragmentClass = ViewFragment.class;
                args.putInt(Constants.RECYCLER_VIEW_TYPE, R.id.grid_staggered);
                break;
            default:
                fragmentClass = ViewFragment.class;
                args.putInt(Constants.RECYCLER_VIEW_TYPE, R.id.grid_view);
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commitAllowingStateLoss();
        }
    }
}
