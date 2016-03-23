package me.makeachoice.myappportfolio.fragment;

import android.support.v4.app.ListFragment;

import me.makeachoice.myappportfolio.controller.maid.Maid;

/**
 * Created by Usuario on 3/23/2016.
 */
public abstract class MyListFragment extends ListFragment implements MyFragmentInterface{
    int mLayoutId;
    Maid mMaid;
    abstract public void setLayout(int id);
}
