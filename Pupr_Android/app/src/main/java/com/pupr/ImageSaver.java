package com.pupr;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

    static Drawable setDefaultPic(Context context) {

        //Path information for a default picture
        String imagePath = "drawable/defaultpicture"; //path for defaultpicture picture, the P part of the pupr logo
        int imageKey = context.getResources().getIdentifier(imagePath, "drawable", "com.pupr"); //imageKey for the defaultpicture pic
        return context.getResources().getDrawable(imageKey); //turn image into a drawable
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

    static Bitmap rescale(Bitmap bm){
        return Bitmap.createScaledBitmap(bm, 500, 350, true);
    }

    void save(Bitmap bitmapImage) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(createFile());
            Bitmap rescaled = rescale(bitmapImage);
            rescaled.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);

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

/*
    Note:
    Methods getOrientation and getCorrectlyOrientedImage come from:
    https://stackoverflow.com/questions/3647993/android-bitmaps-loaded-from-gallery-are-rotated-in-imageview/8914291#8914291

*/
    private static int getOrientation(Context context, Uri photoUri) {
        /* it's on the external media. */
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[] { MediaStore.Images.ImageColumns.ORIENTATION }, null, null, null);

        if (cursor.getCount() != 1) {
            return -1;
        }

        cursor.moveToFirst();
        return cursor.getInt(0);
    }
    static Bitmap getCorrectlyOrientedImage(Context context, Uri photoUri) throws IOException {
        InputStream is = context.getContentResolver().openInputStream(photoUri);
        BitmapFactory.Options dbo = new BitmapFactory.Options();
        dbo.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, dbo);
        is.close();

        int rotatedWidth, rotatedHeight;
        int orientation = getOrientation(context, photoUri);

        if (orientation == 90 || orientation == 270) {
            rotatedWidth = dbo.outHeight;
            rotatedHeight = dbo.outWidth;
        } else {
            rotatedWidth = dbo.outWidth;
            rotatedHeight = dbo.outHeight;
        }

        Bitmap srcBitmap;
        is = context.getContentResolver().openInputStream(photoUri);
        if (rotatedWidth > 500 || rotatedHeight > 500) {
            float widthRatio = ((float) rotatedWidth) / ((float) 500);
            float heightRatio = ((float) rotatedHeight) / ((float) 500);
            float maxRatio = Math.max(widthRatio, heightRatio);

            // Create the bitmap from file
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = (int) maxRatio;
            srcBitmap = BitmapFactory.decodeStream(is, null, options);
        } else {
            srcBitmap = BitmapFactory.decodeStream(is);
        }
        is.close();

        /*
         * if the orientation is not 0 (or -1, which means we don't know), we
         * have to do a rotation.
         */
        if (orientation > 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(orientation);

            srcBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(),
                    srcBitmap.getHeight(), matrix, true);
        }

        return srcBitmap;
    }

// https://colinyeoh.wordpress.com/2012/05/18/android-getting-image-uri-from-bitmap/
    static Uri getImageUri(Context inContext, Bitmap inImage, int userid) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "img" + userid, null);
        return Uri.parse(path);
    }
}