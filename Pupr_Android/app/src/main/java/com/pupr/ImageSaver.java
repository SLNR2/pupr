package com.pupr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 Adapted from:
 * Created by Ilya Gazman on 3/6/2016.
 https://stackoverflow.com/questions/17674634/saving-and-reading-bitmaps-images-from-internal-memory-in-android

 */
class ImageSaver {

    private String directoryName = "pupr";
    private String fileName = "image.png";
    private Context context;
    private boolean external;


    ImageSaver(){}

    ImageSaver(Context context) {
        this.context = context;
    }

    static void setDefaultPic(Context context, User newUser) {

        //Path information for a default picture
        String imagePath = "drawable/defaultpicture"; //path for defaultpicture picture, the P part of the pupr logo
        int imageKey = context.getResources().getIdentifier(imagePath, "drawable", "com.pupr"); //imageKey for the defaultpicture pic
        Drawable defaultPicture = context.getResources().getDrawable(imageKey); //turn image into a drawable
        newUser.setPic(defaultPicture);
    }

    ImageSaver setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    ImageSaver setExternal(boolean external) {
        this.external = external;
        return this;
    }

    ImageSaver setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
        return this;
    }

    void save(Bitmap bitmapImage) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(createFile());
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @NonNull //File f = new File(Environment.getExternalStorageDirectory(), path);
    private File createFile() {
        File directory;
        if(external){
            directory = getAlbumStorageDir(directoryName);
            if(!directory.exists())
                directory.mkdir();
        }
        else {
            directory = context.getDir(directoryName, Context.MODE_PRIVATE);
            if(!directory.exists())
                directory.mkdir();
        }

        if(!directory.exists() && !directory.mkdirs()){
            Log.e("ImageSaver","Error creating directory " + directory);
        }

        return new File(directory, fileName);
    }

    private File getAlbumStorageDir(String albumName) {
        return new File(Environment.getExternalStoragePublicDirectory(
                "/pupr"), albumName);
    }

    Bitmap load(String path) {
        FileInputStream inputStream = null;
        try {
            File f = new File(Environment.getExternalStorageDirectory(), path);
            inputStream = new FileInputStream(f);
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
