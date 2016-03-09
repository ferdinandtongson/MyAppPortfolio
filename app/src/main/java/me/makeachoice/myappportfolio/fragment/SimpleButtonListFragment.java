package me.makeachoice.myappportfolio.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.makeachoice.myappportfolio.R;

/**
 * SimpleButtonListFragment - a simple list of buttons
 */
public class SimpleButtonListFragment extends Fragment {

    /*
        Fragment subclasses require an empty default constructor. If you don't provide one but
        specify a non-empty constructor, Lint will give you an error.
        Android may destroy and later re-create an activity and all its associated fragments when
        the app goes into the background. When the activity comes back, its FragmentManager starts
        re-creating fragments by using the empty default constructor. If it cannot find one, you
        get an exception
     */
    public static SimpleButtonListFragment newInstance(){
        return new SimpleButtonListFragment();
    }

    public SimpleButtonListFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);

        return view;
    }
}
