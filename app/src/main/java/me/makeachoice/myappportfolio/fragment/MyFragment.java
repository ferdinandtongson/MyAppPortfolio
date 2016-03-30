package me.makeachoice.myappportfolio.fragment;

import android.support.v4.app.Fragment;
import android.widget.ListAdapter;

import me.makeachoice.myappportfolio.controller.maid.Maid;

/**
 * Fragment abstract used for all my fragments
 */
public abstract class MyFragment extends Fragment implements MyFragmentInterface{

    protected String mMaidName;
    protected int mLayoutId;
    protected Bridge mBridge;

    abstract public void setLayout(int id);
    abstract public void setBridge(Bridge bridge);

    //Upkeeping Maid class must implement this interface
    public interface Bridge{

        void onItemClick(int position);
        ListAdapter getListAdapter();
    }
}

