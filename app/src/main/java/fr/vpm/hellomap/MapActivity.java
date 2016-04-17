package fr.vpm.hellomap;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

public class MapActivity extends AppCompatActivity {

  private MapView mapview;
  private MapboxMap mapboxMap;
  private PictureWithLocationLoader picLoader;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map);
    findViews();
    mapview.onCreate(savedInstanceState);
    mapview.getMapAsync(new OnMapReadyCallback() {
      @Override
      public void onMapReady(MapboxMap mapboxMap) {
        MapActivity.this.mapboxMap = mapboxMap;
        mapboxMap.setOnMapLongClickListener(new MapLongClickListener(MapActivity.this));

        picLoader = new PictureWithLocationLoader(MapActivity.this);
        addMarkerForPicture();
      }
    });
  }

  private void addMarkerForPicture() {
    Picture p = picLoader.getLatestPics();
    if (p != null) {
      MarkerOptions markerOptions = new MarkerOptions()
          .position(new LatLng(p.getLatitude(), p.getLongitude()))
          .title("cool views here: " + p.getDescription())
          .getThis();
      mapboxMap.addMarker(markerOptions);
    }
  }

  private void findViews() {
    mapview = (MapView) findViewById(R.id.mapview);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode,
                                         String permissions[], int[] grantResults) {
    Log.d("permissions", String.valueOf(requestCode) + String.valueOf(grantResults[0]));
    if ((3 == requestCode) && (PackageManager.PERMISSION_GRANTED == grantResults[0])) {
      addMarkerForPicture();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    mapview.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    mapview.onPause();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mapview.onSaveInstanceState(outState);
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
    mapview.onLowMemory();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mapview.onDestroy();
  }
}
