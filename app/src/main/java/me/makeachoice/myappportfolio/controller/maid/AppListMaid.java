package me.makeachoice.myappportfolio.controller.maid;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ListAdapter;

import me.makeachoice.myappportfolio.controller.Boss;
import me.makeachoice.myappportfolio.fragment.list.SimpleListFragment;

/**
 * SimpleListMaid takes care of updating the view details of a simple
 */
public class AppListMaid implements SimpleListFragment.Bridge, Boss.AppListBridge{


    Boss mBoss;
    public AppListMaid(Boss boss){
        mBoss = boss;

    }

    public void setListAdapter(ListAdapter adapter){
        mListAdapter = adapter;
    }

    public void setFragment(Fragment fragment){
        mFragment = fragment;
    }

    private ListAdapter mListAdapter;
    public ListAdapter getListAdapter(){
        Log.d("SimpleListFragment", "AppListMaid.getListAdapter");

        if(mListAdapter == null){
            mListAdapter = mBoss.createAppListAdapter();
        }

        return mListAdapter;
    }




    public void onSimpleListItemClick(int position){
        Log.d("SimpleListFragment", "Maid.onListItemClick");
        //Log.d("SimpleListFragment", "     info: " + mAppModel.getApp(position).getDescription());
    }

    Fragment mFragment;
    public Fragment getFragment(){
        return mFragment;
    }
}
