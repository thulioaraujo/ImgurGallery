package com.mobilab.imgurgallery.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.mobilab.imgurgallery.R;
import com.mobilab.imgurgallery.adapter.ViewAdapter;
import com.mobilab.imgurgallery.model.GalleryImage;
import com.mobilab.imgurgallery.model.PreferencesApplication;
import com.mobilab.imgurgallery.util.ClientConnection;
import com.mobilab.imgurgallery.util.Constants;
import com.mobilab.imgurgallery.util.Logger;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This fragment implements the functionality to display the images
 *
 * @author Thúlio Araújo (thuliolins@gmail.com)
 * @since 2/6/2017
 */
public class ViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressDialog pDialog;
    private ArrayList<GalleryImage> galleryImages;
    private int idViewTipe;

    // JSON Main Handler
    private JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            try {
                JSONArray array = response.getJSONArray("data");// Get Array From Data Object
                final GalleryImage galleryImage = new GalleryImage();
                galleryImages = galleryImage.parseJSONArrayObjct(array);

                mAdapter = new ViewAdapter(getActivity(), galleryImages, idViewTipe);
                mRecyclerView.setAdapter(mAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                pDialog.dismiss();
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
            super.onFailure(statusCode, headers, throwable, errorResponse);

            Logger.error("Error Responce: " + errorResponse);

            Toast.makeText(getActivity(), "Conection Error: ", Toast.LENGTH_SHORT).show();
            pDialog.dismiss();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_view, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        idViewTipe = getArguments().getInt(Constants.RECYCLER_VIEW_TYPE);

        int currentOrientation = getResources().getConfiguration().orientation;
        if (idViewTipe == R.id.grid_view && currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            mLayoutManager = new GridLayoutManager(getActivity(), 3);
        } else {
            mLayoutManager = new GridLayoutManager(getActivity(), 2);
        }

        initDisplayRecylerView(idViewTipe);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading images...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        //Update a URL with selected parameters.
        requestUrl();

        return view;
    }

    private void requestUrl() {
        String baseUrl = null;

        //Load variables from User Shared Preferences
        SharedPreferences sharedPref = getActivity().getApplicationContext().
                getSharedPreferences(Constants.APPLICATION_NAME, Context.MODE_PRIVATE);
        String section = sharedPref.getString(PreferencesApplication.SECTION,
                PreferencesApplication.Section.HOT.getValue());
        String sort = sharedPref.getString(PreferencesApplication.SORT,
                PreferencesApplication.Sort.VIRAL.getValue());
        String window = sharedPref.getString(PreferencesApplication.WINDOW,
                PreferencesApplication.Window.DAY.getValue());
        String showViral = sharedPref.getString(PreferencesApplication.VIRAL, PreferencesApplication.VIRAL_TRUE);
        baseUrl = section + '/' + sort + '/' + window + "?showViral=" + showViral;

        if (baseUrl != null) {
            ClientConnection.requestAccess(baseUrl, null, handler);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initDisplayRecylerView(int recyclerViewType) {

        switch (recyclerViewType) {
            case (R.id.grid_view):
                mRecyclerView.setLayoutManager(mLayoutManager);
                break;

            case (R.id.list_view):
                mLayoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.setLayoutManager(mLayoutManager);
                break;

            case (R.id.grid_staggered):
                mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(mLayoutManager);
                break;

            default:
                mRecyclerView.setLayoutManager(mLayoutManager);
                break;
        }
    }
}
