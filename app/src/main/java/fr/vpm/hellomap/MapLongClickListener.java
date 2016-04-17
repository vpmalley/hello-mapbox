package fr.vpm.hellomap;

import android.content.Context;
import android.widget.Toast;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;

/**
 * Created by vince on 17/04/16.
 */
public class MapLongClickListener implements MapboxMap.OnMapLongClickListener {
  private Context context;

  public MapLongClickListener(Context context) {
    this.context = context;
  }

  @Override
  public void onMapLongClick(LatLng point) {
    Toast.makeText(context, "clicked on position " + point.getLatitude()
        + ", " + point.getLongitude(), Toast.LENGTH_SHORT).show();
  }
}
