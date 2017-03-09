package com.eventrip;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inbarsh on 3/9/2017.
 */

public class ListActivity extends AppCompatActivity {
    private List<FeedItem> feedsList;
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        /*posts - replace with number of events
        JSONObject - replace with events metadata
        thumbnail - replace with image
        was:
        for (int i = 0; i < posts.length(); i++) {
            JSONObject post = posts.optJSONObject(i);
            FeedItem item = new FeedItem();
            item.setTitle(post.optString("title"));
            item.setThumbnail(post.optString("thumbnail"));
            feedsList.add(item);
        }*/
        feedsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FeedItem item = new FeedItem();
            item.setTitle("title");
            feedsList.add(item);
        }
        adapter = new MyRecyclerViewAdapter(this, feedsList);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

    }
}
