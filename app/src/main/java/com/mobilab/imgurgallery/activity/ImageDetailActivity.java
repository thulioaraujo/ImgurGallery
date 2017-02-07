package com.mobilab.imgurgallery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobilab.imgurgallery.R;
import com.mobilab.imgurgallery.model.GalleryImage;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * This activity shows a dialog with informations of the image selected
 *
 * @author Thúlio Araújo (thuliolins@gmail.com)
 * @since 2/6/2017
 */
public class ImageDetailActivity extends AppCompatActivity {

    @InjectView(R.id.imageDetail)
    ImageView imageDetail;
    @InjectView(R.id.imageTitle)
    TextView imageTitle;
    @InjectView(R.id.imageUpVotes)
    TextView imageUpVotes;
    @InjectView(R.id.imageDownVotes)
    TextView imageDownVotes;
    @InjectView(R.id.imageScore)
    TextView imageScore;
    @InjectView(R.id.imageDescription)
    TextView imageDescription;
    @InjectView(R.id.btn_close)
    Button btnClose;

    private GalleryImage galleryImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.image_description_alert);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        galleryImage = (GalleryImage) intent.getSerializableExtra(GalleryImage.class.getName());

        Picasso.with(this).load(galleryImage.getLink()).placeholder(R.drawable.progress_animation).into(imageDetail);
        if (!galleryImage.getTitle().equals("null")) {
            imageTitle.setText(galleryImage.getTitle());
        } else {
            imageTitle.setText(R.string.no_title);
        }

        imageUpVotes.setText(String.valueOf(galleryImage.getUps()));
        imageDownVotes.setText(String.valueOf(galleryImage.getDowns()));
        imageScore.setText(String.valueOf(galleryImage.getScore()));

        if (!galleryImage.getDescription().equals("null")) {
            imageDescription.setText(galleryImage.getDescription());
        } else {
            imageDescription.setText(R.string.no_description);
        }

        this.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
