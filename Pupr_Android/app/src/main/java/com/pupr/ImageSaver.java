package com.pupr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
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

    private static final String directoryName = "pupr";
    private String fileName = "image.png";
    private Context context;
    private boolean external;


    ImageSaver(){}

    ImageSaver(Context context) {
        this.context = context;
    }

    static Drawable setDefaultPic(Context context) {

        //Path information for a default picture
        String imagePath = "drawable/defaultpicture"; //path for default picture picture, the P part of the pupr logo
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

    //Creates pupr directory on your phone
    @NonNull
    private File createFile() {
        File directory;
        if(external){
            directory = new File(Environment.getExternalStorageDirectory(), directoryName);
            if(!directory.exists())
                directory.mkdirs();
        }
        else {
            directory = context.getDir(directoryName, Context.MODE_PRIVATE);
            if(!directory.exists())
                directory.mkdirs();
        }

        if(!directory.exists() && !directory.mkdirs()){
            Log.e("ImageSaver","Error creating directory " + directory);
        }

        return new File(directory, fileName);
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
  /*  private static int getOrientation(Context context, Uri photoUri) {
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[] { MediaStore.Images.ImageColumns.ORIENTATION }, null, null, null);

        if (cursor.getCount() != 1) {
            return -1;
        }

        cursor.moveToFirst();
        return cursor.getInt(0);
    }
    */
// https://stackoverflow.com/questions/21085105/get-orientation-of-image-from-mediastore-images-media-data
    private static int getOrientation(String filepath) {// YOUR MEDIA PATH AS STRING
        int degree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filepath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
            if (orientation != -1) {
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }

            }
        }
        return degree;
    }

    static Bitmap getCorrectlyOrientedImage(Context context, Uri photoUri, String path) throws IOException {
        InputStream is = context.getContentResolver().openInputStream(photoUri);
        BitmapFactory.Options dbo = new BitmapFactory.Options();
        dbo.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, dbo);
        is.close();

        int rotatedWidth, rotatedHeight;
        int orientation = getOrientation(path);

        if (orientation == 90 || orientation == 270) {
            rotatedWidth = dbo.outHeight;
            rotatedHeight = dbo.outWidth;
        } else {
            rotatedWidth = dbo.outWidth;
            rotatedHeight = dbo.outHeight;
        }
        Bitmap src;
        is = context.getContentResolver().openInputStream(photoUri);
        if (rotatedWidth > 2000 || rotatedHeight > 2000) {
            float widthRatio = ((float) rotatedWidth) / ((float) 2000);
            float heightRatio = ((float) rotatedHeight) / ((float) 2000);
            float maxRatio = Math.max(widthRatio, heightRatio);
            // Create the bitmap from file
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = (int) maxRatio;
            src = BitmapFactory.decodeStream(is, null, options);

        } else {
            src = BitmapFactory.decodeStream(is);
        }
        is.close();
        /*
         * if the orientation is not 0 (or -1, which means we don't know), we
         * have to do a rotation.
         */
        if (orientation > 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(orientation);

            src = Bitmap.createBitmap(src, 0, 0, src.getWidth(),
                    src.getHeight(), matrix, true);
            Log.d("ImageSaver", "second width = " + src.getWidth());
            Log.d("ImageSaver", "second height = " + src.getHeight());
        }

        return src;
    }

// adapted from https://colinyeoh.wordpress.com/2012/05/18/android-getting-image-uri-from-bitmap/
static Uri getImageUri(Context inContext, Bitmap inImage, int userid) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
       // String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "img" + userid, null);
        //File image= Environment.getExternalStorageDirectory().toString() + "/pupr/img" + userid;

    File image = new File(Environment.getExternalStorageDirectory(), directoryName + "/img" + userid + ".png");
        return Uri.fromFile(image);
    }

}