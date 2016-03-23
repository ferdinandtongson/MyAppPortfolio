package me.makeachoice.myappportfolio.fragment;

import android.support.v4.app.ListFragment;
import android.widget.ListAdapter;

import me.makeachoice.myappportfolio.controller.maid.Maid;

/**
 * Created by Usuario on 3/23/2016.
 */
public abstract class MyListFragment extends ListFragment implements MyFragmentInterface{

    protected String mMaidName;
    protected int mLayoutId;
    protected Bridge mBridge;

    abstract public void setLayout(int id);
    abstract public void setMaidName(String name);

    //Upkeeping Maid class must implement this interface
    public interface Bridge{
        void onItemClick(int position);
        ListAdapter getListAdapter();
    }

}
