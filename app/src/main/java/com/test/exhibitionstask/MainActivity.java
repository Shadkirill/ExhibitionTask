package com.test.exhibitionstask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.test.exhibitionstask.fileexhibitsloader.ExhibitsLoaderImpl;
import com.test.exhibitionstask.model.Exhibit;
import com.test.exhibitionstask.model.ExhibitsLoader;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mExhibitsRecyclerView;
    private ExhibitsRecyclerViewAdapter mExhibitsAdapter;
    private ExhibitsLoader mExhibitsLoader;
    private List<Exhibit> mExhibitsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mExhibitsRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_exhibits);
        mExhibitsLoader = new ExhibitsLoaderImpl(this.getApplicationContext());
        loadData();
    }


    private void loadData() {
        List<Exhibit> exhibitList = mExhibitsLoader.getExhibitList();
        if (exhibitList != null) {
            mExhibitsList = exhibitList;
            Log.d(TAG, "data loaded: " + exhibitList.size());
            mExhibitsAdapter = new ExhibitsRecyclerViewAdapter(exhibitList, this);
            LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mExhibitsRecyclerView.setLayoutManager(linearLayoutManager);
            mExhibitsRecyclerView.setAdapter(mExhibitsAdapter);
            mExhibitsAdapter.notifyDataSetChanged();
        }
    }
}
