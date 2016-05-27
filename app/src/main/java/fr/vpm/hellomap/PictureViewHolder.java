package fr.vpm.hellomap;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

/**
 * Created by vince on 24/05/16.
 */
public class PictureViewHolder extends RecyclerView.ViewHolder {

  final ImageView imageView;

  public PictureViewHolder(ImageView imageView) {
    super(imageView);
    this.imageView = imageView;
  }

  public ImageView getImageView() {
    return imageView;
  }
}
