package com.example.krishnakumar.rss_jsoup;

import android.content.Context;
import android.content.Intent;
import android.sax.RootElement;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.krishnakumar.rss_jsoup.model.NaukriData;

import java.util.ArrayList;

/**
 * Created by krishnakumar on 23-12-2015.
 */

public class CustomAdapter extends BaseAdapter {
    private Context _ctx;
    ArrayList<NaukriData> data;
    private LayoutInflater inflater;

    public CustomAdapter(Context ctx,ArrayList<NaukriData> val){
        inflater = LayoutInflater.from(ctx);
        this._ctx = ctx;
        this.data = val;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder{
        TextView postTitleLabel,txtPubDate;
        ImageView imgProfile;
        ProgressBar loading;
        RelativeLayout relativeParent;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.postitem, parent, false);

            holder.relativeParent = (RelativeLayout) convertView.findViewById(R.id.relativeParent);
            holder.loading = (ProgressBar) convertView.findViewById(R.id.loading);
            holder.imgProfile = (ImageView) convertView.findViewById(R.id.postThumb);
            holder.postTitleLabel = (TextView) convertView.findViewById(R.id.postTitleLabel);
            holder.txtPubDate = (TextView) convertView.findViewById(R.id.txtPubDate);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }


        holder.postTitleLabel.setText("" + data.get(position).getHeadline());
        holder.txtPubDate.setText("" + data.get(position).getPublishDate());


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
                .into(holder.imgProfile);

        holder.relativeParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_ctx, data.get(position).getHeadline(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(_ctx, DetailsActivity.class);
                intent.putStringArrayListExtra("data", data.get(position).data);
                intent.putExtra("headline", data.get(position).getHeadline());
                intent.putExtra("description", data.get(position).getPublishDate());
                intent.putExtra("imgURL", data.get(position).getImgUrl());
                _ctx.startActivity(intent);

            }
        });

        return convertView;
    }
}