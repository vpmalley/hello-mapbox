package fr.vpm.hellomap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.List;

public class MapActivity extends AppCompatActivity implements PictureLoadedListener{

  private static final int REQ_READ_PICS = 12;
  public static final String PERM_READ_PICS = Manifest.permission.READ_EXTERNAL_STORAGE;

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

        picLoader = new PictureWithLocationLoader(MapActivity.this, MapActivity.this);
        if (checkPermission(PERM_READ_PICS, REQ_READ_PICS)) {
          loadPicturesWithLocations();
        }
      }
    });
  }

  private void findViews() {
    mapview = (MapView) findViewById(R.id.mapview);
  }

  private void loadPicturesWithLocations() {
    picLoader.getLatestPics();
  }

  public void onPicturesWithLocationLoaded(List<Picture> pictures) {
    for (Picture p : pictures) {
      if (p != null) {
        MarkerOptions markerOptions = new MarkerOptions()
            .position(new LatLng(p.getLatitude(), p.getLongitude()))
            .title("cool views here: " + p.getDescription())
            .getThis();
        mapboxMap.addMarker(markerOptions);
      }
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
