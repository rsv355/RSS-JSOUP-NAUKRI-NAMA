package com.example.krishnakumar.rss_jsoup.Fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.krishnakumar.rss_jsoup.R;
import com.example.krishnakumar.rss_jsoup.model.NaukriData;

public class FlipAdapter extends BaseAdapter implements OnClickListener {
	
	public interface Callback{
		public void onPageRequested(int page);
	}
	ArrayList<NaukriData> data;
	private Context _ctx;
	private LayoutInflater inflater;
	private Callback callback;
	
	public FlipAdapter(Context context,ArrayList<NaukriData> dat) {
		inflater = LayoutInflater.from(context);
		this._ctx = context;
		this.data = dat;
	}

	public void setCallback(Callback callback) {
		this.callback = callback;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.page, parent, false);

			holder.loading = (ProgressBar) convertView.findViewById(R.id.loading);
			holder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
			holder.imgBanner = (ImageView) convertView.findViewById(R.id.imgBanner);
			holder.txtDate = (TextView) convertView.findViewById(R.id.txtDate);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtTitle.setText("" + data.get(position).getHeadline());
		holder.txtDate.setText("" + data.get(position).getPublishDate());

		holder.loading.setVisibility(View.VISIBLE);

		Glide.with(_ctx)
				.load(data.get(position).getImgUrl())
				.listener(new RequestListener<String, GlideDrawable>() {
					@Override
					public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
						return false;
					}

					@Override
					public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
						holder.loading.setVisibility(View.GONE);
						return false;
					}
				})
				.into(holder.imgBanner);


		return convertView;
	}

	static class ViewHolder{
		TextView txtTitle,txtDate;
		ImageView imgBanner;
		ProgressBar loading;
	}

	@Override
	public void onClick(View v) {


	}


}
