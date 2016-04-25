package fr.vpm.hellomap;

import java.util.List;

/**
 * Created by vince on 25/04/16.
 */
public interface PictureLoadedListener {

  void onPicturesWithLocationLoaded(List<Picture> pictures);
}
