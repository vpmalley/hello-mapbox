package fr.vpm.hellomap;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by vince on 24/05/16.
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureViewHolder> {

  private final Activity activity;

  private final int resource;

  private final List<Picture> pictures;

  public PictureAdapter(Activity activity, int resource, List<Picture> objects) {
    super();
    this.activity = activity;
    this.resource = resource;
    this.pictures = objects;
  }

  @Override
  public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(resource, parent, false);
    ImageView pictureView = (ImageView) v.findViewById(R.id.picture);
    return new PictureViewHolder(pictureView);
  }

  @Override
  public void onBindViewHolder(PictureViewHolder pictureHolder, int position) {
    Picture p = pictures.get(position);
    Glide
        .with(activity)
        .load(p.getUri())
        .into(pictureHolder.getImageView());
  }

  @Override
  public int getItemCount() {
    return pictures.size();
  }
}
