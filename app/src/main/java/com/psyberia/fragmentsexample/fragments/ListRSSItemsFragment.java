package com.psyberia.fragmentsexample.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.psyberia.fragmentsexample.R;
import com.psyberia.fragmentsexample.models.RSSItem;
import com.psyberia.fragmentsexample.ui.recyclerView.RVSimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by combo on 21.09.2016.
 */
public class ListRSSItemsFragment extends BaseFragment{
    private RecyclerView rView;
    Context mContext;

    //private static ListRSSItemsFragment ourInstance;

    public static ListRSSItemsFragment getInstance(/*int index*/) {
        //return ourInstance;
        ListRSSItemsFragment f = new ListRSSItemsFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        //args.putInt("rss", index);
        //f.setArguments(args);

        return f;
    }

    public ListRSSItemsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    List<RSSItem> rssItems = new ArrayList<RSSItem>();
    // Array list for list view
    ArrayList<HashMap<String, String>> rssItemList = new ArrayList<HashMap<String,String>>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        mContext = getContext();
        View rootView = inflater.inflate(R.layout.fragment_rss_item_list, container, false);
        this.rView = (RecyclerView) rootView.findViewById(R.id.rv_rss_list);

        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rView.setLayoutManager(llm);
        rView.setItemAnimator(new DefaultItemAnimator());

        initInstances();
        return rootView;
    }

    private void initInstances() {
        //getArguments().getInt("index", 0);


        String rss_link = "https://news.yandex.ru/auto_racing.rss";
        new loadRSSFeedItems().execute(rss_link);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    RSSParser rssParser = new RSSParser();


    /**
     * Background Async Task to get RSS Feed Items data from URL
     * */
    class loadRSSFeedItems extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getListener().showPDialog();
        }

        /**
         * getting all recent articles and showing them in listview
         * */
        @Override
        protected String doInBackground(String... args) {
            // rss link url
            String rss_url = args[0];

            // list of rss items
            rssItems = rssParser.getRSSFeedItems(rss_url);
            // updating UI from Background Thread
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed items into listview
                     *
                    ListAdapter adapter = new SimpleAdapter(
                            ListRSSItemsActivity.this,
                            rssItemList, R.layout.rss_item_list_row,
                            new String[] { TAG_LINK, TAG_TITLE, TAG_PUB_DATE, TAG_DESRIPTION },
                            new int[] { R.id.page_url, R.id.title, R.id.pub_date, R.id.link });

                    // updating listview
                    setListAdapter(adapter);


                     заполняем...

                     */

                    Log.e(TAG, "rssItemList.toString()" + rssItems.toString());
                    RVSimpleAdapter rcAdapter = new RVSimpleAdapter(getContext(), rssItems);
                    rView.setAdapter(rcAdapter);
                }
            });
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String args) {
            // dismiss the dialog after getting all products
            getListener().hidePDialog();
        }
    }
}
