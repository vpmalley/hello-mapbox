package fr.vpm.hellomap;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by vince on 29/05/16.
 */
public class OnPictureClickListener implements View.OnClickListener {
  @Override
  public void onClick(View view) {
    ImageView pictureView = (ImageView) view;
    pictureView.setBackgroundColor(view.getResources().getColor(R.color.mapbox_blue));
  }
}
