package me.makeachoice.myappportfolio.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.adapter.ImageAdapter;

/**
 * Created by Usuario on 3/11/2016.
 */
public class SimpleGridFragment extends Fragment {
    /*
        Fragment subclasses require an empty default constructor. If you don't provide one but
        specify a non-empty constructor, Lint will give you an error.
        Android may destroy and later re-create an activity and all its associated fragments when
        the app goes into the background. When the activity comes back, its FragmentManager starts
        re-creating fragments by using the empty default constructor. If it cannot find one, you
        get an exception
     */
    public static SimpleGridFragment newInstance(){
        return new SimpleGridFragment();
    }

    public SimpleGridFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grid_fragment, container, false);

        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(view.getContext()));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
