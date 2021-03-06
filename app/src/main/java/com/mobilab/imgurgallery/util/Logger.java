package com.mobilab.imgurgallery.util;

import android.util.Log;

/**
 * This class prints on console logs information about the application
 *
 * @author Thúlio Araújo (thuliolins@gmail.com)
 * @since 2/6/2017
 */
public class Logger {

    /**
     * Print INFO-level logging statement
     *
     * @param text Text to log
     */
    public static void info(String text) {
        if (Constants.LOGGING) {
            Log.i(Constants.APPLICATION_NAME, text);
        }
    }

    /**
     * Print DEBUG-level logging statement
     *
     * @param text Text to log
     */
    public static void debug(String text) {
        if (Constants.LOGGING) {
            Log.d(Constants.APPLICATION_NAME, text);
        }
    }

    /**
     * Print ERROR-level logging statement
     *
     * @param text Text to log
     */
    public static void error(String text) {
        if (Constants.LOGGING) {
            Log.e(Constants.APPLICATION_NAME, text);
        }
    }
}
