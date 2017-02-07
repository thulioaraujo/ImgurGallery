package com.mobilab.imgurgallery.model;

import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Imgur API model for Image metadata.
 * Based on this: https://api.imgur.com/models/gallery_image
 *
 * @author Thúlio Araújo (thuliolins@gmail.com)
 * @since 2/6/2017
 */
public class GalleryImage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String description;
    private Integer width;
    private Integer height;
    private Integer size;
    private String link;
    private Integer ups;
    private Integer downs;
    private Integer score;

    public GalleryImage() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setUps(Integer ups) {
        this.ups = ups;
    }

    public Integer getUps() {
        return ups;
    }

    public Integer getDowns() {
        return downs;
    }

    public void setDowns(Integer downs) {
        this.downs = downs;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * @param array
     * @return ArrayList<GalleryImage>
     * @throws JSONException
     */
    public ArrayList<GalleryImage> parseJSONArrayObjct(JSONArray array) throws JSONException {

        ArrayList<GalleryImage> galleryImages = new ArrayList<GalleryImage>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);

            // Check whether the result set represents a gallery album or not
            if (!object.getBoolean("is_album")) {
                String id = object.getString("id");
                String title = object.getString("title");
                String description = object.getString("description");
                Integer width = object.getInt("width");
                Integer height = object.getInt("height");
                Integer size = object.getInt("size");
                String link = object.getString("link");
                Integer ups = object.getInt("ups");
                Integer downs = object.getInt("downs");
                Integer score = object.getInt("score");

                GalleryImage galleryImage = new GalleryImage();
                galleryImage.setId(id);
                galleryImage.setTitle(title);
                galleryImage.setDescription(description);
                galleryImage.setWidth(width);
                galleryImage.setHeight(height);
                galleryImage.setSize(size);
                galleryImage.setLink(link);
                galleryImage.setUps(ups);
                galleryImage.setDowns(downs);
                galleryImage.setScore(score);
                galleryImages.add(galleryImage);
            }
        }

        return galleryImages;
    }
}