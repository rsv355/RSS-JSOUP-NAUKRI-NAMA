package com.example.krishnakumar.rss_jsoup;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.krishnakumar.rss_jsoup.Fragments.LatestJobs;
import com.example.krishnakumar.rss_jsoup.model.NaukriData;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    TextView headline, description;
    ArrayList<String> data;
    LinearLayout linearData;
    ImageView img, imgBack;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        headline = (TextView) findViewById(R.id.headline);
        description = (TextView) findViewById(R.id.description);
        linearData = (LinearLayout) findViewById(R.id.linearData);
        img = (ImageView) findViewById(R.id.img);
        loading = (ProgressBar) findViewById(R.id.loading);
        imgBack = (ImageView) findViewById(R.id.imgBack);

        headline.setText(getIntent().getStringExtra("headline"));
        description.setText(getIntent().getStringExtra("description"));

        String imgURl = getIntent().getStringExtra("imgURL");


        if(imgURl != null) {
            Glide.with(this)
                    .load(imgURl)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                           loading.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(img);
        }

        data = getIntent().getStringArrayListExtra("data");

        linearData.removeAllViews();

        for(String datum : data) {
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.data_item, null);

            TextView txtDataItem = (TextView) view.findViewById(R.id.txtDataItem);
            txtDataItem.setMovementMethod(LinkMovementMethod.getInstance());
            txtDataItem.setText(Html.fromHtml(datum));
           // setTextViewHTML(txtDataItem, datum);
            linearData.addView(view);

        }

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    protected void makeLinkClickable(SpannableStringBuilder strBuilder, final URLSpan span)
    {
        int start = strBuilder.getSpanStart(span);
        int end = strBuilder.getSpanEnd(span);
        int flags = strBuilder.getSpanFlags(span);
        ClickableSpan clickable = new ClickableSpan() {
            public void onClick(View view) {
                // Do something with span.getURL() to handle the link click...
                Log.e("click ", "123 " + span.getURL() );
                Toast.makeText(DetailsActivity.this, span.getURL()+"", Toast.LENGTH_SHORT).show();
            }
        };
        strBuilder.setSpan(clickable, start, end, flags);
        strBuilder.removeSpan(span);
    }

    protected void setTextViewHTML(TextView text, String html)
    {
        CharSequence sequence = Html.fromHtml(html);
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
        URLSpan[] urls = strBuilder.getSpans(0, sequence.length(), URLSpan.class);
        for(URLSpan span : urls) {
            makeLinkClickable(strBuilder, span);
        }
        text.setText(strBuilder);
    }







//end of main class
}
