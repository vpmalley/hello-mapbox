package fr.vpm.hellomap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.List;

public class MapActivity extends AppCompatActivity implements PictureLoadedListener{

  private static final int REQ_READ_PICS = 12;
  public static final String PERM_READ_PICS = Manifest.permission.READ_EXTERNAL_STORAGE;

  private MapView mapView;
  private MapboxMap mapboxMap;
  private PictureWithLocationLoader picLoader;
  private ListView picturesView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_map);
    findViews();
    mapView.onCreate(savedInstanceState);
    mapView.getMapAsync(new OnMapReadyCallback() {
      @Override
      public void onMapReady(MapboxMap mapboxMap) {
        MapActivity.this.mapboxMap = mapboxMap;
        mapboxMap.setOnMapLongClickListener(new MapLongClickListener(MapActivity.this));

        picLoader = new PictureWithLocationLoader(MapActivity.this, MapActivity.this);
        if (checkPermission(PERM_READ_PICS, REQ_READ_PICS)) {
          loadPicturesWithLocations();
        }
      }
    });
  }

  private void findViews() {
    picturesView = (ListView) findViewById(R.id.pictures);
    mapView = (MapView) findViewById(R.id.mapview);
  }

  private void loadPicturesWithLocations() {
    picLoader.getLatestPics();
  }

  public void onPicturesWithLocationLoaded(List<Picture> pictures) {
    for (Picture p : pictures) {
      if (p != null) {
        Log.d("pictures", "loading pic : " + p.getDescription());
        MarkerOptions markerOptions = new MarkerOptions()
            .position(new LatLng(p.getLatitude(), p.getLongitude()))
            .title("cool views here: " + p.getDescription())
            .getThis();
        mapboxMap.addMarker(markerOptions);
      }
      picturesView.setAdapter(new PictureAdapter(this, R.layout.picture_list_item, pictures));
    }
  }

  public boolean checkPermission(String permission, int requestCode) {
    int perm = ContextCompat.checkSelfPermission(this,
        permission);
    if (PackageManager.PERMISSION_GRANTED != perm) {
      if (ActivityCompat.shouldShowRequestPermissionRationale(this,
          Manifest.permission.READ_EXTERNAL_STORAGE)) {
      } else {
        ActivityCompat.requestPermissions(this,
            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, requestCode);
      }
    }
    return PackageManager.PERMISSION_GRANTED == perm;
  }

  @Override
  public void onRequestPermissionsResult(int requestCode,
                                         String permissions[], int[] grantResults) {
    if ((REQ_READ_PICS == requestCode) && (PackageManager.PERMISSION_GRANTED == grantResults[0])) {
      loadPicturesWithLocations();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    mapView.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    mapView.onPause();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mapView.onSaveInstanceState(outState);
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
    mapView.onLowMemory();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mapView.onDestroy();
  }
}
