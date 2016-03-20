package me.makeachoice.myappportfolio.controller.maid;

import android.content.Context;
import android.util.Log;
import android.widget.ListAdapter;

import java.util.ArrayList;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.adapter.TitleAdapter;
import me.makeachoice.myappportfolio.adapter.item.TitleItem;
import me.makeachoice.myappportfolio.model.AppDemoModel;

/**
 * Created by Usuario on 3/19/2016.
 */
public class AppListMaid{

    Context mActivityContext;
    public AppListMaid(Context ctx){
        mActivityContext = ctx;
    }

    private ListAdapter mListAdapter;
    public ListAdapter getListAdapter(AppDemoModel model){
        Log.d("SimpleListFragment", "Boss.getListAdapter");


        if(mListAdapter == null){
            Log.d("SimpleListFragment", "     adapter is null");
            mListAdapter = new TitleAdapter(mActivityContext, createListItems(model),
                    R.layout.item_onlytitle, R.id.item_onlytext_title);
            Log.d("SimpleListFragment", "          size: " + mListAdapter.getCount());

        }
        Log.d("SimpleListFragment", "     adapter: " + mListAdapter.toString());

        return mListAdapter;
    }

    private ArrayList<TitleItem> createListItems(AppDemoModel model){
        Log.d("SimpleListFragment", "Boss.createListItems()");


        ArrayList<TitleItem> itemList = new ArrayList<TitleItem>();
        int count = model.getAppCount();
        for(int i = 0; i < count; i++){
            TitleItem item = new TitleItem(model.getApp(i).getName());
            itemList.add(item);
        }

        return itemList;
    }




}
