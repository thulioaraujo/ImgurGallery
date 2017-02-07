# Imgur Gallery

Imgur Gallery application was created with the purpose to show gallery images from the http://api.imgur.com/. It was developed under the Android platform using Android Studio 2.2.3 and some dependencies which you can check on the `build.gradle` file of the application.
- --

#### Imgur Gallery Configuration
```sh
applicationId "com.mobilab.imgurgallery"
minSdkVersion 21
targetSdkVersion 24
```
#### Install
In order to run and debug the application, you have to set your *MY_IMGUR_CLIENT_ID* on the file `Constants.java` located at `app.src.main.java.com.mobilab.imgurgallery.util`.
```sh
public static final String MY_IMGUR_CLIENT_ID = "########";
```
Otherwise, you can download the latest `.apk` from: https://github.com/thulioaraujo/ImgurGallery/blob/master/app-debug.apk

#### Usage

* The main screen of the application displays as default a grid layout, but you can change it clicking on the type of layout you prefer on the **Menu Bar**. The main types are *Grid Layout*, *List Layout*, and *Staggered Grid Layout*;

* On the **Menu Bar** of the application you can also see a *Menu* which displays the *About* of the application, containing information about the Author, Version, and Build Time.

* On top of the screen, you can see three kinds of parameters to choose:
    * Hot (Default)
    * Top
    * User

* On the bottom of the screen, right side, you can click on the *Filter Button*, which will displays a dialog screen containing filters to choose, as:
    * Sort: `Viral (Default)` | `Top` | `Time`
    * Window: `Day (Default)` | `Week` | `Month` | `Year` | `All`
    * Viral: `Show viral` (`True` (Default) or `False`)

* This application allows you to change the screen orientation as (`Landscape` and `Portrait`);