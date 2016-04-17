package fr.vpm.hellomap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

public class MapActivity extends AppCompatActivity {

  MapView mapview;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map);
    findViews();
    mapview.onCreate(savedInstanceState);
    mapview.getMapAsync(new OnMapReadyCallback() {
      @Override
      public void onMapReady(MapboxMap mapboxMap) {

        MarkerOptions markerOptions = new MarkerOptions()
            .position(new LatLng(48.19866,-69.86450))
            .title("cool views here")
            .getThis();
        mapboxMap.addMarker(markerOptions);
        mapboxMap.setOnMapLongClickListener(new MapLongClickListener(MapActivity.this));
      }
    });
  }

  private void findViews() {
    mapview = (MapView) findViewById(R.id.mapview);
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
