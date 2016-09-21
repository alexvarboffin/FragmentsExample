package com.psyberia.fragmentsexample;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.psyberia.fragmentsexample.fragments.CategoriesFragment;
import com.psyberia.fragmentsexample.fragments.IOnFragmentInteractionListener;
import com.psyberia.fragmentsexample.fragments.ListRSSItemsFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
//        PlaceholderFragment.IChildFragmentListener
        IOnFragmentInteractionListener {

    @SuppressWarnings("unused")
    private static final String TAG = MainActivity.class.getSimpleName();
    private CharSequence mTitle;
    DrawerLayout drawer;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayoutAndroid;
    CoordinatorLayout rootLayoutAndroid;


    //reprecated GridView gridView;
    Context context;
    ArrayList arrayList;


    private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = getApplicationContext();


        tracker();

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //mTitle = getTitle();
        //Log.e(TAG, "" + mTitle);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            CategoriesFragment firstFragment = CategoriesFragment.newInstance();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, firstFragment).commit();
        }

        initInstances();
    }

    private void initInstances() {
        CoordinatorLayout rootLayoutAndroid = (CoordinatorLayout) findViewById(R.id.android_coordinator_layout);
        CollapsingToolbarLayout collapsingToolbarLayoutAndroid = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_android_layout);
        collapsingToolbarLayoutAndroid.setTitle("Material Grid #1");
    }

    private void tracker() {
        // Obtain the shared Tracker instance.
        //AnalyticsApplication application = (AnalyticsApplication) getApplication();
        //Tracker mTracker = application.getDefaultTracker();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Only show items in the action bar relevant to this screen
        // if the drawer is not showing. Otherwise, let the drawer
        // decide what to show in the action bar.

        return super.onCreateOptionsMenu(menu);
    }

    private void restoreActionBar() {
        //toolbar.setNavigationIcon();

        //Random r = new Random();
        //int r0 = (r.nextInt(100));
        // + " " + String.valueOf(r0)

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            // The action bar home/up action should open or close the drawer.
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;

            //noinspection SimplifiableIfStatement
            case R.id.action_settings:
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        selectDrawerItem(item);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void selectDrawerItem(MenuItem item) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass = null;

        switch (item.getItemId()) {
            case R.id.nav_categories:
                fragmentClass = CategoriesFragment.class;
                break;
            case R.id.nav_gallery:
                fragmentClass = ListRSSItemsFragment.class;
                break;
            case R.id.nav_slideshow:
                //fragmentClass = FirstFragment.class;
                break;
            case R.id.nav_manage:
                //fragmentClass = FirstFragment.class;
                break;
            case R.id.nav_share:
                //fragmentClass = FirstFragment.class;
                break;

            case R.id.nav_send:
                //fragmentClass = FirstFragment.class;
                break;
            default:
                fragmentClass = CategoriesFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle(item.getTitle());

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Add the fragment to the activity, pushing this transaction
        // on to the back stack.
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, fragment); //PlaceholderFragment.newInstance(item.getTitle())
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }


    @Override
    public void onSectionAttached(String var0) {
        //       Log.e(TAG, "onSectionAttached(): " + var0);
/*        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
*/
        mTitle = var0;
        restoreActionBar();
    }


    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE! Make sure to override the method with only a single `Bundle` argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //
    }


    // Progress Dialog
    private ProgressDialog pDialog;

    @Override
    public void showPDialog() {
        /*pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Loading recent articles...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();*/
    }

    @Override
    public void hidePDialog() {
        if (pDialog != null)
            pDialog.dismiss();
    }
}

/*if (!drawer.isDrawerOpen(GravityCompat.START)) {
            //restoreActionBar();
            return true;
        }*/




/*
*
*
*  Log.i(TAG, "Setting screen name: " + name);
    mTracker.setScreenName("Image~" + name);
    mTracker.send(new HitBuilders.ScreenViewBuilder().build());



     mTracker.send(new HitBuilders.EventBuilder()
            .setCategory("Action")
            .setAction("Share")
            .build());
*
* */