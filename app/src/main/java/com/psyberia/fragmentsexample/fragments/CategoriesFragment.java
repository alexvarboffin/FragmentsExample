package com.psyberia.fragmentsexample.fragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.psyberia.fragmentsexample.R;
import com.psyberia.fragmentsexample.sql.Category;
import com.psyberia.fragmentsexample.sql.RSSDatabaseHandler;
import com.psyberia.fragmentsexample.ui.recyclerView.RecyclerItemClickListener;
import com.psyberia.fragmentsexample.ui.recyclerView.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by combo on 19.09.2016.
 */
public class CategoriesFragment extends BaseFragment {

    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DESCRIPTION = "description";


    private GridLayoutManager lLayout;
    private RecyclerView rView;

    //GridView gridView;
    Context context;

    // Array list for list view
    ArrayList<HashMap<String, String>> rssFeedList;

    public static CategoriesFragment newInstance() {
        CategoriesFragment f = new CategoriesFragment();
        // Supply num input as an argument.
        //Bundle args = new Bundle();
        //args.putInt("num", num);
        //f.setArguments(args);

        return f;
    }

    public static String[] gridViewStrings = {
            "Android",
            "Java",
            "GridView",
            "ListView",
            "Adapter",
            "Custom GridView",
            "Material",
            "XML",
            "Code",
            "Android",


            "Android",
            "Java",
            "GridView",
            "ListView",
            "Adapter",
            "Custom GridView",
            "Material",
            "XML",
            "Code",
            "Android",

            "Android",
            "Java",
            "GridView",
            "ListView",
            "Adapter",
            "Custom GridView",
            "Material",
            "XML",
            "Code",
            "Android",

            "Android",
            "Java",
            "GridView",
            "ListView",
            "Adapter",
            "Custom GridView",
            "Material",
            "XML",
            "Code",
            "Android",

            "Android",
            "Java",
            "GridView",
            "ListView",
            "Adapter",
            "Custom GridView",
            "Material",
            "XML",
            "Code",
            "Android",

            "Android",
            "Java",
            "GridView",
            "ListView",
            "Adapter",
            "Custom GridView",
            "Material",
            "XML",
            "Code",
            "Android",

            "Android",
            "Java",
            "GridView",
            "ListView",
            "Adapter",
            "Custom GridView",
            "Material",
            "XML",
            "Code",
            "Android",

            "Android",
            "Java",
            "GridView",
            "ListView",
            "Adapter",
            "Custom GridView",
            "Material",
            "XML",
            "Code",
            "Android",


    };
    public static int[] gridViewImages = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
    };


    // array to trace sqlite ids
    String[] sqliteIds;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);


        //gridView = (GridView) rootView.findViewById(R.id.grid);
        //gridView.setAdapter(new CustomAndroidGridViewAdapter(getContext(), gridViewStrings, gridViewImages));


        //        toolbar.setLogo(R.mipmap.ic_launcher);
//        toolbar.setLogoDescription(getResources().getString(R.string.app_name));

        //       gridView = (GridView) findViewById(R.id.grid);

/*



        gridView.setAdapter(new CustomAndroidGridViewAdapter(getApplicationContext(), objects));

        initInstances();*/


        //RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //recyclerView.setAdapter(new Adapter());
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Category> rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(getActivity(), 4);

        rView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        rView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(view.getContext(), "Clicked Country Position = " + position, Toast.LENGTH_SHORT).show();
                    }
                })
        );


        initInstances();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initInstances() {
        //rootLayoutAndroid = (CoordinatorLayout) findViewById(R.id.android_coordinator_layout);
        //collapsingToolbarLayoutAndroid = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_android_layout);
        //collapsingToolbarLayoutAndroid.setTitle("Material Grid");


        /**
         * Calling a background thread which will load
         * web sites stored in SQLite database
         * */
        new loadStoreCategories().execute();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    private class loadStoreCategories extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*pDialog = new ProgressDialog(
                    AndroidRSSReaderApplicationActivity.this);
            pDialog.setMessage("Loading websites ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();*/
        }

        /**
         * getting all stored website from SQLite
         */
        @Override
        protected String doInBackground(String... args) {

            Log.e("#################", "00000000000000000000000000000");
            // updating UI from Background Thread
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    RSSDatabaseHandler rssDb = new RSSDatabaseHandler(
                            getActivity().getApplicationContext());


                    Category category;
                    for (int i = 0; i < 40; i++) {
                        category = new Category("Категория №" + i, "Описание категории №" + i);
                        rssDb.addCategory(category);
                    }

                    // listing all websites from SQLite
                    List<Category> categoryList = rssDb.getAllCategories();

                    sqliteIds = new String[categoryList.size()];

                    // loop through each website
/*                    for (int i = 0; i < categoryList.size(); i++) {

                        Category c = categoryList.get(i);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_ID, "" + c.getId());
                        map.put(TAG_TITLE, "" + c.getTitle());
                        map.put(TAG_DESCRIPTION, "" + c.getDescription());

                        // adding HashList to ArrayList
                        rssFeedList.add(map);

                        // add sqlite id to array
                        // used when deleting a website from sqlite
                        sqliteIds[i] = "" + c.getId();

                        Log.e("#################", c.getTitle());
                    }*/
                    /**
                     * Updating list view with websites
                     * */
                    /*ListAdapter adapter = new SimpleAdapter(
                            AndroidRSSReaderApplicationActivity.this,
                            rssFeedList, R.layout.site_list_row,
                            new String[]{TAG_ID, TAG_TITLE, TAG_LINK},
                            new int[]{R.id.sqlite_id, R.id.title, R.id.link});
                    // updating listview
                    lv.setAdapter(adapter);
                    registerForContextMenu(lv);*/


                    /**
                     *GRID
                     * CustomAndroidGridViewAdapter adapter = new CustomAndroidGridViewAdapter(getContext(),
                     categoryList);//rssFeedList
                     gridView.setAdapter(adapter);
                     */
                    RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(getContext(), categoryList);
                    rView.setAdapter(rcAdapter);

                }
            });
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String args) {
            // dismiss the dialog after getting all products
            //pDialog.dismiss();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putInt("curChoice", mCurCheckPosition);
    }


    private List<Category> getAllItemList() {
        List<Category> objects = new ArrayList<>();

        for (int i = 0; i < gridViewStrings.length; i++) {
            Category c = new Category();
            c.setId(i);
            c.setTitle(gridViewStrings[i] + gridViewStrings.length);
            c.setDescription(gridViewStrings[i]);
            objects.add(c);
        }
        return objects;
    }
}
