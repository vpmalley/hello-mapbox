package fr.vpm.hellomap;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by vince on 17/04/16.
 */
public class PictureWithLocationLoader {

  private Activity context;

  public PictureWithLocationLoader(Activity context) {
    this.context = context;
  }

  public Picture getLatestPics() {
    int perm = ContextCompat.checkSelfPermission(context,
        Manifest.permission.READ_EXTERNAL_STORAGE);
    Log.d("picloader", String.valueOf(perm));
    if (PackageManager.PERMISSION_GRANTED == perm) {
      return getLatestPicForReal();
    } else {
      if (ActivityCompat.shouldShowRequestPermissionRationale(context,
          Manifest.permission.READ_EXTERNAL_STORAGE)) {
        Log.d("picloader", "should show something");
      } else {
        Log.d("picloader", "it is requesting it");
        ActivityCompat.requestPermissions(context,
            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
      }
    }
    return null;
  }

  private Picture getLatestPicForReal() {
    Picture p = new Picture();
    String[] projection = new String[] {
        MediaStore.Images.ImageColumns._ID,
        MediaStore.Images.ImageColumns.DATE_TAKEN,
        MediaStore.Images.ImageColumns.LATITUDE,
        MediaStore.Images.ImageColumns.LONGITUDE,
        MediaStore.Images.ImageColumns.DESCRIPTION
    };
    final Cursor cursor = context.getContentResolver()
        .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, MediaStore.Images.ImageColumns.LATITUDE + " IS NOT NULL",
            null, MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC");
    if (cursor.moveToFirst()) {
      Log.d("picloader", cursor.getString(2) + cursor.getString(3));
      p.setStoreId(cursor.getString(0));
      if ((cursor.getString(3) != null) && (cursor.getString(4) != null)) {
        p.setLatitude(Double.valueOf(cursor.getString(2)));
        p.setLongitude(Double.valueOf(cursor.getString(3)));
      }
      p.setDescription(cursor.getString(4));
    }
    return p;
  }

}
