package me.makeachoice.myappportfolio.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static me.makeachoice.myappportfolio.R.layout.detail_fragment;

/**
 * SimpleDetailFragment - a simple textfield display
 */
public class SimpleDetailFragment extends Fragment {
    /*
        Fragment subclasses require an empty default constructor. If you don't provide one but
        specify a non-empty constructor, Lint will give you an error.
        Android may destroy and later re-create an activity and all its associated fragments when
        the app goes into the background. When the activity comes back, its FragmentManager starts
        re-creating fragments by using the empty default constructor. If it cannot find one, you
        get an exception
     */
    public static SimpleDetailFragment newInstance(){
        return new SimpleDetailFragment();
    }

    public SimpleDetailFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(detail_fragment, container, false);

        return view;
    }

}
