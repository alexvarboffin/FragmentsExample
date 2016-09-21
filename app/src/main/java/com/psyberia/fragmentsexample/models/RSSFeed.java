package com.psyberia.fragmentsexample.models;

/**
 * Created by combo on 19.09.2016.
 */
import java.util.List;

/**
 * This class handle rss xml
 *
 * <channel>
 <title></title>
 <link></link>
 <description></description>
 <item>
 <title></title>
 <link></link>
 <pubDate></pubDate>
 <description></description>
 </item>
 <item>
 .
 .
 .</item>
 </channel>
 * */
public class RSSFeed {
    // xml nodes
    String _title;
    String _description;
    String _link;
    String _rss_link;
    String _language;
    List<RSSItem> _items;

    // constructor
    public RSSFeed(String title, String description, String link, String rss_link, String language) {
        this._title = title;
        this._description = description;
        this._link = link;
        this._rss_link = rss_link;
        this._language = language;
    }

    /**
     * All set methods
     * */
    public void setItems(List<RSSItem> items) {
        this._items = items;
    }

    /**
     * All get methods
     * */
    public List<RSSItem> getItems() {
        return this._items;
    }

    public String getTitle() {
        return this._title;
    }

    public String getDescription() {
        return this._description;
    }

    public String getLink() {
        return this._link;
    }

    public String getRSSLink() {
        return this._rss_link;
    }

    public String getLanguage() {
        return this._language;
    }
}