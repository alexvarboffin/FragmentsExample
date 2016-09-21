package com.psyberia.fragmentsexample.ui;

/**
 * Created by combo on 19.09.2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.psyberia.fragmentsexample.R;
import com.psyberia.fragmentsexample.sql.Category;

import java.util.List;

/**
 * Created by HP on 5/11/2016.
 */

public class CustomAndroidGridViewAdapter extends BaseAdapter {

    private final List<Category> objects;
    private Context mContext;
    //private final String[] string;
    //private final int[] Imageid;

    //public CustomAndroidGridViewAdapter(Context context,String[] string,int[] Imageid ) {
    //    mContext = context;
        //this.Imageid = Imageid;
        //this.string = string;
    //}

    public CustomAndroidGridViewAdapter(Context var0, List<Category> var1) {
        mContext = var0;
        this.objects = var1;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.gridview_custom_item, null);
            TextView textView = (TextView) grid.findViewById(R.id.gridview_text);
            ImageView imageView = (ImageView)grid.findViewById(R.id.gridview_image);

            Category category = getCategory(position);

            textView.setText(category.getTitle());
            imageView.setImageResource(R.mipmap.ic_launcher);//Imageid[p]
        } else {
            grid = (View) convertView;
        }

        return grid;
    }

    private Category getCategory(int position) {
        return (Category) getItem(position);
    }
}