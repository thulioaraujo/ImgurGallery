package com.mobilab.imgurgallery.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobilab.imgurgallery.R;
import com.mobilab.imgurgallery.activity.ImageDetailActivity;
import com.mobilab.imgurgallery.model.GalleryImage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * This class represents the Adapter of the RecyclerView, which will
 * iterate each image retrieved from the imgur api
 *
 * @author Thúlio Araújo (thuliolins@gmail.com)
 * @since 2/6/2017
 */
public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {

    private ArrayList<GalleryImage> galleryImages;
    private Context mContext;
    private int idViewType;

    public ViewAdapter(Context context, ArrayList<GalleryImage> galleryImages, int idViewType) {
        this.galleryImages = galleryImages;
        this.mContext = context;
        this.idViewType = idViewType;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        ViewHolder viewHolder = null;

        switch (idViewType) {
            case (R.id.grid_view):
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_gridview, parent, false);
                viewHolder = new ViewHolder(view);
                break;

            case (R.id.list_view):
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_listview, parent, false);
                viewHolder = new ViewHolder(view);
                break;

            case (R.id.grid_staggered):
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_gridstaggered, parent, false);
                viewHolder = new ViewHolder(view);
                break;

            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_gridview, parent, false);
                viewHolder = new ViewHolder(view);
                break;
        }
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GalleryImage galleryImage = galleryImages.get(position);
        if (galleryImage != null) {

            holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //Set Images with picasso library.
            Picasso.with(mContext).load(galleryImage.getLink()).
                    placeholder(R.drawable.progress_animation).into(holder.imageView);

            if (idViewType == R.id.list_view) {
                if (galleryImage.getTitle().equals("null")) {
                    holder.imageTitle.setText(R.string.no_title);
                } else {
                    holder.imageTitle.setText(galleryImage.getTitle());
                }
            }

            if (galleryImage.getDescription().equals("null")) {
                holder.imageDescription.setText(R.string.no_description);
            } else {
                holder.imageDescription.setText(galleryImage.getDescription());
            }
        }
    }

    // Return the size of the dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return galleryImages.size();
    }

    // Provide a reference to the views for each data item.
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView imageTitle;
        public TextView imageDescription;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);

            if (idViewType == R.id.list_view) {
                imageTitle = (TextView) v.findViewById(R.id.imageTitle);
            }

            imageDescription = (TextView) v.findViewById(R.id.imageDescription);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent it = new Intent(mContext, ImageDetailActivity.class);
            final GalleryImage galleryImage = galleryImages.get(getAdapterPosition());
            it.putExtra(GalleryImage.class.getName(), galleryImage);
            mContext.startActivity(it);
        }
    }
}
