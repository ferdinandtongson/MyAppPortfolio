package me.makeachoice.myappportfolio.fragment;

import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import me.makeachoice.myappportfolio.controller.maid.Maid;

/**
 * Created by Usuario on 3/23/2016.
 */
public abstract class MyListFragment extends ListFragment implements MyFragmentInterface{

    protected String mMaidName;
    protected int mLayoutId;
    protected Bridge mBridge;

    abstract public void setLayout(int id);
    abstract public void setBridge(Bridge bridge);

    //Upkeeping Maid class must implement this interface
    public interface Bridge{
        void onItemClick(ListView l, View v, int position, long id);
        ListAdapter getListAdapter();
        void setListAdapter(ListAdapter adapter);
    }
}
