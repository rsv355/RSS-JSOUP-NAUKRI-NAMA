package com.example.krishnakumar.rss_jsoup.Fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.krishnakumar.rss_jsoup.CustomAdapter;
import com.example.krishnakumar.rss_jsoup.R;

import com.example.krishnakumar.rss_jsoup.flipview.FlipView;
import com.example.krishnakumar.rss_jsoup.flipview.OverFlipMode;
import com.example.krishnakumar.rss_jsoup.model.NaukriData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by krishnakumar on 23-12-2015.
 */
public class Flipper extends Fragment implements  FlipAdapter.Callback, FlipView.OnFlipListener, FlipView.OnOverFlipListener {
    public ArrayList<NaukriData> data;
    private static final String ARG_URL = "URL";
    private String URL;
    private ProgressDialog pd;

    private FlipView mFlipView;
    private FlipAdapter mAdapter;

    public static Flipper newInstance(String url) {
        Flipper f = new Flipper();
        Bundle b = new Bundle();
        b.putString(ARG_URL, url);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        URL = getArguments().getString(ARG_URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View convertView = inflater.inflate(R.layout.flip, container, false);

       // final ListView listView = (ListView) convertView.findViewById(R.id.listView);
        mFlipView = (FlipView) convertView.findViewById(R.id.flip_view);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pd = new ProgressDialog(getActivity());
                pd.setMessage("Fetching data...");
                pd.show();
            }

            @Override
            protected Void doInBackground(Void... params) {
                call(URL);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                pd.dismiss();

             //   CustomAdapter adp = new CustomAdapter(getActivity(), data);
              //  listView.setAdapter(adp);
                mAdapter = new FlipAdapter(getActivity(),data);
                mAdapter.setCallback(Flipper.this);
                mFlipView.setAdapter(mAdapter);
                mFlipView.setOnFlipListener(Flipper.this);
                mFlipView.peakNext(false);
                mFlipView.setOverFlipMode(OverFlipMode.RUBBER_BAND);
                mFlipView.setOnOverFlipListener(Flipper.this);
            }
        }.execute();



        return convertView;
    }

    private void call(String url) {

        Document doc;
        try {
            data = new ArrayList<>();

            // need http protocol
            doc = Jsoup.connect(url).get();


            // get page title
            String title = doc.title();

            Log.e("title : ", title);

            // get all links
            Elements links = doc.getElementsByTag("item");
            for (Element link : links) {

                NaukriData item = new NaukriData();

                //Log.e("##item : " , ""+link.getElementsByTag("title").text());
                item.setHeadline(link.getElementsByTag("title").text());
                item.setPublishDate(link.getElementsByTag("pubDate").text());

                String div = link.select("description").text();

                Document doc2 = Jsoup.parse(div);
                Elements img = doc2.getElementsByTag("img");
                for (Element el : img) {
                    // Log.e("src attribute is : ",el.attr("src"));
                    item.setImgUrl(el.attr("src"));
                }

                String contentElements = link.getElementsByTag("content:encoded").text();

                Document contentDoc = Jsoup.parse(contentElements);
                //Log.e("contentElements", contentElements);
                item.insertIntoData(contentElements);
                Elements para = contentDoc.getElementsByTag("p");
                for (Element el : para) {
                    //Log.e("el value ", el.text());
                    //item.insertIntoData(el.text());
                    /*Elements para1 = el.getElementsByTag("strong");
                    for(Element e2 : para1) {
                        Log.e("e2 Text ", e2.text());
                        Log.e("e2 value ", el.val());
                    }*/
                }
                data.add(item);
            }
            Log.e("data : ", data.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageRequested(int page) {

    }

    @Override
    public void onFlippedToPage(FlipView v, int position, long id) {

    }


    @Override
    public void onOverFlip(FlipView v, OverFlipMode mode, boolean overFlippingPrevious, float overFlipDistance, float flipDistancePerPage) {

    }




    //end of main class

}
