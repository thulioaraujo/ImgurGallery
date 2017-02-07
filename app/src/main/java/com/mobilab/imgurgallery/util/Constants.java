package com.mobilab.imgurgallery.util;

/**
 * This class holds the main constants of the entire application
 *
 * @author Thúlio Araújo (thuliolins@gmail.com)
 * @since 2/6/2017
 */
public class Constants {

    /*
      Application name
     */
    public static final String APPLLICATION_NAME = "IMGUR_GALLERY";

    /*
      Logging flag
     */
    public static final boolean LOGGING = false;

    /*
      Your imgur client id. You need this to upload to imgur.
      More here: https://api.imgur.com/
     */
    public static final String MY_IMGUR_CLIENT_ID = "########";

    /*
      Redirect URL for android.
     */
    public static final String MY_IMGUR_REDIRECT_URL = "http://android";

    /*
      Client Auth
     */
    public static String getClientAuth() {
        return "Client-ID " + MY_IMGUR_CLIENT_ID;
    }

    /*
      Type presentation
     */
    public static final String RECYCLER_VIEW_TYPE = "RECYCLER_VIEW_TYPE";
}
