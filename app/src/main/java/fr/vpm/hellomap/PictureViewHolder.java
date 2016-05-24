package fr.vpm.hellomap;

import android.widget.ImageView;

/**
 * Created by vince on 24/05/16.
 */
public class PictureViewHolder {

  final ImageView imageView;

  public PictureViewHolder(ImageView imageView) {
    this.imageView = imageView;
  }

  public ImageView getImageView() {
    return imageView;
  }
}
