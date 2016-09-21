package com.psyberia.fragmentsexample.ui.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.psyberia.fragmentsexample.R;
import com.psyberia.fragmentsexample.models.RSSItem;

import java.util.List;

/**
 * Created by combo on 21.09.2016.
 */
public class RVSimpleAdapter extends RecyclerView.Adapter<RVSimpleAdapter.ViewHolder> {

    private final List<RSSItem> objects;
    private final Context mContext;

    public RVSimpleAdapter(Context context, List<RSSItem> rssItemList) {
        this.objects = rssItemList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rss_item_list_row, null);
        ViewHolder rcv = new ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(objects.get(position).getTitle());
        holder.link.setText(objects.get(position).getLink());
        holder.pubDate.setText(objects.get(position).getPubdate());


        // taking only 200 chars from description
        String description = objects.get(position).getDescription();
        if (description.length() > 100) {
            description = description.substring(0, 97) + "...";
        }
        holder.description.setText(description);
    }


    @Override
    public int getItemCount() {
        return this.objects.size();
    }


    /*
    VIEW
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView pubDate;
        TextView link;

        public ViewHolder(View itemView) {
            super(itemView);

            link = (TextView) itemView.findViewById(R.id.page_url);
            title = (TextView) itemView.findViewById(R.id.title);
            pubDate = (TextView) itemView.findViewById(R.id.pub_date);
            description = (TextView) itemView.findViewById(R.id.description);

        }
    }

}