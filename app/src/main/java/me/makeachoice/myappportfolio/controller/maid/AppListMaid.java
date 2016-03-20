package me.makeachoice.myappportfolio.controller.maid;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ListAdapter;

import java.util.ArrayList;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.adapter.TitleAdapter;
import me.makeachoice.myappportfolio.adapter.item.TitleItem;
import me.makeachoice.myappportfolio.fragment.list.SimpleListFragment;
import me.makeachoice.myappportfolio.model.AppDemoModel;

/**
 * SimpleListMaid takes care of updating the view details of a simple
 */
public class AppListMaid implements SimpleListFragment.Bridge{

    Context mActivityContext;

    public AppListMaid(Context ctx){
        mActivityContext = ctx;

    }

    private ListAdapter mListAdapter;
    public ListAdapter getListAdapter(){
        Log.d("SimpleListFragment", "Boss.getListAdapter");


        if(mListAdapter == null){
            Log.d("SimpleListFragment", "     adapter is null");
            mListAdapter = createListAdapter(mModel);
            Log.d("SimpleListFragment", "          size: " + mListAdapter.getCount());

        }
        Log.d("SimpleListFragment", "     adapter: " + mListAdapter.toString());

        return mListAdapter;
    }


    public ListAdapter createListAdapter(AppDemoModel model){
        Log.d("SimpleListFragment", "Boss.createListItems()");
        mModel = model;

        ArrayList<TitleItem> itemList = new ArrayList<TitleItem>();
        int count = mModel.getAppCount();
        for(int i = 0; i < count; i++){
            TitleItem item = new TitleItem(mModel.getApp(i).getName());
            itemList.add(item);
        }

        mListAdapter = new TitleAdapter(mActivityContext, itemList,
                R.layout.item_onlytitle, R.id.item_onlytext_title);

        return mListAdapter;
    }

    AppDemoModel mModel;
    public void setModel(AppDemoModel model){
        mModel = model;
    }


    public void onSimpleListItemClick(int position){
        Log.d("SimpleListFragment", "Maid.onListItemClick");
        //Log.d("SimpleListFragment", "     info: " + mAppModel.getApp(position).getDescription());
    }

    SimpleListFragment mFragment;
    public Fragment getFragment(){
        // Create a new Fragment to be placed in the activity layout
        mFragment = new SimpleListFragment();

        mFragment.setLayout(R.layout.list_fragment);

        Log.d("SimpleListFragment", "MainActivity.onCreate:");

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        //mFragment.setArguments(mActivityContext.getIntent().getExtras());
        return (Fragment)mFragment;
    }
}
