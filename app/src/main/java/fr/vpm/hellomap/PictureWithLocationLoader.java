package fr.vpm.hellomap;

import android.app.Activity;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vince on 17/04/16.
 */
public class PictureWithLocationLoader {

  private Activity context;

  private PictureLoadedListener pictureLoadedListener;

  public PictureWithLocationLoader(Activity context, PictureLoadedListener pictureLoadedListener) {
    this.context = context;
    this.pictureLoadedListener = pictureLoadedListener;
  }

  public void getLatestPics() {
    String[] projection = new String[] {
        MediaStore.Images.ImageColumns._ID,
        MediaStore.Images.ImageColumns.DATE_TAKEN,
        MediaStore.Images.ImageColumns.LATITUDE,
        MediaStore.Images.ImageColumns.LONGITUDE,
        MediaStore.Images.ImageColumns.DATA
    };
    final Cursor cursor = context.getContentResolver()
        .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null,
            null, MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC limit 10");
    List<Picture> pics = new ArrayList<>();
    Log.d("pictures", String.valueOf(cursor.getCount()));
    while (cursor.moveToNext()) {
      Picture p = new Picture();
      p.setStoreId(cursor.getString(0));
      if ((cursor.getString(2) != null) && (cursor.getString(3) != null)) {
        p.setLatitude(Double.valueOf(cursor.getString(2)));
        p.setLongitude(Double.valueOf(cursor.getString(3)));
      }
      p.setDescription(cursor.getString(4));
      pics.add(p);
    }
    pictureLoadedListener.onPicturesWithLocationLoaded(pics);
  }

}
