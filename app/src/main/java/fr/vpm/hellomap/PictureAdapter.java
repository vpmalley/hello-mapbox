package fr.vpm.hellomap;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by vince on 24/05/16.
 */
public class PictureAdapter extends ArrayAdapter<Picture> {

  private final Activity activity;

  private final int resource;

  public PictureAdapter(Activity activity, int resource, List<Picture> objects) {
    super(activity, resource, objects);
    this.activity = activity;
    this.resource = resource;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    PictureViewHolder pictureHolder;

    // retrieving the view
    if (convertView == null) {
      LayoutInflater layoutInflater = activity.getLayoutInflater();
      convertView = layoutInflater.inflate(resource, parent, false);
      pictureHolder = findAllViews(convertView);
      convertView.setTag(pictureHolder);
    } else {
      pictureHolder = (PictureViewHolder) convertView.getTag();
    }
    Picture p = getItem(position);
    Glide.with(getContext()).load(p.getDescription()).into(pictureHolder.getImageView());
    return convertView;
  }

  private PictureViewHolder findAllViews(View convertView) {
    return new PictureViewHolder((ImageView) convertView.findViewById(R.id.picture));
  }
}
