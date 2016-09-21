package com.psyberia.fragmentsexample.sql;

/**
 * Created by combo on 19.09.2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RSSDatabaseHandler extends SQLiteOpenHelper {

    private String TAG;

    public RSSDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, String TAG) {
        super(context, name, factory, version);
        this.TAG = TAG;
    }

    public RSSDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler, String TAG) {
        super(context, name, factory, version, errorHandler);
        this.TAG = TAG;
    }

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "therssfeeder";

    // Contacts table name
    private static final String TABLE_RSS = "tbl_websites";
    //Category
    private static final String TABLE_CATEGORIES = "tbl_categories_t";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_LINK = "link";
    private static final String KEY_RSS_LINK = "rss_link";
    private static final String KEY_DESCRIPTION = "description";

    public RSSDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RSS_TABLE = "CREATE TABLE " + TABLE_RSS + "(" + KEY_ID
                + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT," + KEY_LINK
                + " TEXT," + KEY_RSS_LINK + " TEXT," + KEY_DESCRIPTION
                + " TEXT" + ")";
        db.execSQL(CREATE_RSS_TABLE);


        db.execSQL("CREATE TABLE "
                + TABLE_CATEGORIES + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT,"
                //+ KEY_LINK + " TEXT,"
                //+ KEY_RSS_LINK + " TEXT,"
                + KEY_DESCRIPTION + " TEXT" + ")");

        //create category
        Log.e(TAG, "ON_CREATE_DATA_BASE_!!!!");




    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RSS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        // Create tables again
        onCreate(db);
    }

    /**
     * Adding a new website in websites table Function will check if a site
     * already existed in database. If existed will update the old one else
     * creates a new row
     */
/*    public void addSite(WebSite site) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, site.getTitle()); // site title
        values.put(KEY_LINK, site.getLink()); // site url
        values.put(KEY_RSS_LINK, site.getRSSLink()); // rss link url
        values.put(KEY_DESCRIPTION, site.getDescription()); // site description

        // Check if row already existed in database
        if (!isSiteExists(db, site.getRSSLink())) {
            // site not existed, create a new row
            db.insert(TABLE_RSS, null, values);
            db.close();
        } else {
            // site already existed update the row
            updateSite(site);
            db.close();
        }
    }*/
    public void addCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, category.getTitle()); // site title
        //values.put(KEY_LINK, site.getLink()); // site url
        //values.put(KEY_RSS_LINK, site.getRSSLink()); // rss link url
        values.put(KEY_DESCRIPTION, category.getDescription()); // site description

        // Check if row already existed in database
        if (!isCategoryExists(db, category.getTitle())) { //getRSSLink
            // site not existed, create a new row
            db.insert(TABLE_CATEGORIES, null, values);
            db.close();
        } else {
            // site already existed update the row
            updateCategory(category);
            db.close();
        }
    }

    /**
     * Reading all rows from database
     */
    /*public List<WebSite> getAllSites() {
        List<WebSite> siteList = new ArrayList<WebSite>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RSS
                + " ORDER BY id DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                WebSite site = new WebSite();
                site.setId(Integer.parseInt(cursor.getString(0)));
                site.setTitle(cursor.getString(1));
                site.setLink(cursor.getString(2));
                site.setRSSLink(cursor.getString(3));
                site.setDescription(cursor.getString(4));
                // Adding contact to list
                siteList.add(site);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return contact list
        return siteList;
    }*/
    public List<Category> getAllCategories() {
        List<Category> siteList = new ArrayList<Category>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CATEGORIES + " ORDER BY " + KEY_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(Integer.parseInt(cursor.getString(0)));
                category.setTitle(cursor.getString(1));
                //site.setLink(cursor.getString(2));
                //site.setRSSLink(cursor.getString(3));
                category.setDescription(cursor.getString(2));
                // Adding contact to list
                siteList.add(category);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return contact list
        return siteList;
    }

    /**
     * Updating a single row row will be identified by rss link
     */
/*    public int updateSite(WebSite site) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, site.getTitle());
        values.put(KEY_LINK, site.getLink());
        values.put(KEY_RSS_LINK, site.getRSSLink());
        values.put(KEY_DESCRIPTION, site.getDescription());

        // updating row return
        int update = db.update(TABLE_RSS, values, KEY_RSS_LINK + " = ?",
                new String[] { String.valueOf(site.getRSSLink()) });
        db.close();
        return update;
    }*/
    public int updateCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, category.getTitle());
        //values.put(KEY_LINK, site.getLink());
        //values.put(KEY_RSS_LINK, site.getRSSLink());
        values.put(KEY_DESCRIPTION, category.getDescription());

        // updating row return
        int update = db.update(TABLE_CATEGORIES, values, KEY_TITLE + " = ?",
                new String[]{String.valueOf(category.getTitle())});
        db.close();
        return update;
    }

    /**
     * Reading a row (website) row is identified by row id
     */
/*    public WebSite getSite(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RSS, new String[] { KEY_ID, KEY_TITLE,
                        KEY_LINK, KEY_RSS_LINK, KEY_DESCRIPTION }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        WebSite site = new WebSite(cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4));

        site.setId(Integer.parseInt(cursor.getString(0)));
        site.setTitle(cursor.getString(1));
        site.setLink(cursor.getString(2));
        site.setRSSLink(cursor.getString(3));
        site.setDescription(cursor.getString(4));
        cursor.close();
        db.close();
        return site;
    }*/
    public Category getCategory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CATEGORIES, new String[]{KEY_ID, KEY_TITLE,
                        //        KEY_LINK, KEY_RSS_LINK,
                        KEY_DESCRIPTION}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null,
                null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Category category = new Category(cursor.getString(1), cursor.getString(2)
                //, cursor.getString(3), cursor.getString(4)
        );

        category.setId(Integer.parseInt(cursor.getString(0)));
        category.setTitle(cursor.getString(1));
        //site.setLink(cursor.getString(2));
        //site.setRSSLink(cursor.getString(3));
        category.setDescription(cursor.getString(4));
        cursor.close();
        db.close();
        return category;
    }

    /**
     * Deleting single row
     */
/*    public void deleteSite(WebSite site) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RSS, KEY_ID + " = ?",
                new String[] { String.valueOf(site.getId())});
        db.close();
    }*/
    public void deleteCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATEGORIES, KEY_ID + " = ?",
                new String[]{String.valueOf(category.getId())});
        db.close();
    }

    /**
     * Checking whether a site is already existed check is done by matching rss
     * link
     */
    /*public boolean isSiteExists(SQLiteDatabase db, String rss_link) {

        Cursor cursor = db.rawQuery("SELECT 1 FROM " + TABLE_RSS
                + " WHERE rss_link = '" + rss_link + "'", new String[] {});
        boolean exists = (cursor.getCount() > 0);
        return exists;
    }*/
    public boolean isCategoryExists(SQLiteDatabase db, String title) {

        Cursor cursor = db.rawQuery("SELECT 1 FROM " + TABLE_CATEGORIES
                + " WHERE " + KEY_TITLE + " = '" + title + "'", new String[]{});
        boolean exists = (cursor.getCount() > 0);
        return exists;
    }

}