package fr.vpm.hellomap;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by vince on 24/05/16.
 */
public class PictureViewHolder extends RecyclerView.ViewHolder {

  private final View mainView;

  private final ImageView imageView;

  public PictureViewHolder(View mainView, ImageView imageView) {
    super(imageView);
    this.mainView = mainView;
    this.imageView = imageView;
    this.imageView.setOnClickListener(new OnPictureClickListener());
  }

  public ImageView getImageView() {
    return imageView;
  }
}
