package me.makeachoice.myappportfolio.controller.butler;

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
public class AppDemoButler {

    Context mActivityContext;
    public AppDemoButler(Context ctx){
        mActivityContext = ctx;
    }

    private ListAdapter mListAdapter;
    public ListAdapter getListAdapter(){
        Log.d("SimpleListFragment", "Boss.getListAdapter");

        if(mListAdapter == null){
            Log.d("SimpleListFragment", "     adapter is null");
            mListAdapter = new TitleAdapter(mActivityContext, createListItems(),
                    R.layout.item_onlytitle, R.id.item_onlytext_title);
            Log.d("SimpleListFragment", "          size: " + mListAdapter.getCount());

        }
        Log.d("SimpleListFragment", "     adapter: " + mListAdapter.toString());

        return mListAdapter;
    }


    AppDemoModel mAppModel;
    private ArrayList<TitleItem> createListItems( ){
        Log.d("SimpleListFragment", "Boss.createListItems()");
        mAppModel = new AppDemoModel();
        Log.d("SimpleListFragment", "     created appModel object");

        mAppModel.addApp("App1", "Info1");
        mAppModel.addApp("App2", "Info2");
        mAppModel.addApp("App3", "Info3");
        mAppModel.addApp("App4", "Info4");
        mAppModel.addApp("App5", "Info5");


        ArrayList<TitleItem> itemList = new ArrayList<TitleItem>();
        int count = mAppModel.getAppCount();
        for(int i = 0; i < count; i++){
            TitleItem item = new TitleItem(mAppModel.getApp(i).getName());
            itemList.add(item);
        }

        return itemList;
    }


}
