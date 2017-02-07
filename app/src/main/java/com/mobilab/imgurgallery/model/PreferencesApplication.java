package com.mobilab.imgurgallery.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.mobilab.imgurgallery.util.Constants;

/**
 * This Class stores the default preferences chosen by the user
 *
 * @author Thúlio Araújo (thuliolins@gmail.com)
 * @since 2/7/2017
 */
public class PreferencesApplication {

    public static final String SECTION = "SECTION";
    public static final String SORT = "SORT";
    public static final String WINDOW = "WINDOW ";
    public static final String VIRAL = "VIRAL";
    public static final String VIRAL_TRUE = "true";
    public static final String VIRAL_FALSE = "false";

    public static void savePreferences(Context ctx, String key, String value) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(Constants.APPLLICATION_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public enum Section {

        HOT("hot"),
        TOP("top"),
        USER("user");

        private final String value;

        Section(final String value) {
            this.value = value;
        }

        public static Section getByValue(final String value) {
            for (final Section section : values()) {
                if (value.equals(section.getValue())) {
                    return section;
                }
            }

            return null;
        }

        public String getValue() {
            return this.value;
        }
    }

    public enum Sort {

        VIRAL("viral"),
        TOP("top"),
        TIME("time");

        private final String value;

        Sort(final String value) {
            this.value = value;
        }

        public static Sort getByValue(final String value) {
            for (final Sort sort : values()) {
                if (value.equals(sort.getValue())) {
                    return sort;
                }
            }

            return null;
        }

        public String getValue() {
            return this.value;
        }
    }

    public enum Window {

        DAY("day"),
        WEEK("week"),
        MONTH("month"),
        YEAR("year"),
        ALL("all");

        private final String value;

        Window(final String value) {
            this.value = value;
        }

        public static Window getByValue(final String value) {
            for (final Window window : values()) {
                if (value.equals(window.getValue())) {
                    return window;
                }
            }

            return null;
        }

        public String getValue() {
            return this.value;
        }
    }
}
