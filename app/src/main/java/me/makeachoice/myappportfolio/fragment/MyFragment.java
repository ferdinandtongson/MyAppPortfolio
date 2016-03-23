package me.makeachoice.myappportfolio.fragment;

import android.support.v4.app.Fragment;
import android.widget.ListAdapter;

import me.makeachoice.myappportfolio.controller.maid.Maid;

/**
 * Fragment abstract used for all my fragments
 */
public abstract class MyFragment extends Fragment implements MyFragmentInterface{

    protected int mLayoutId;
    protected Bridge mBridge;

    abstract public void setLayout(int id);
    abstract public void setBridge(Maid maid);

    //Upkeeping Maid class must implement this interface
    public interface Bridge{
        String KEY_LAYOUT = "Layout";

        void onItemClick(int position);
        ListAdapter getListAdapter();
    }
}

