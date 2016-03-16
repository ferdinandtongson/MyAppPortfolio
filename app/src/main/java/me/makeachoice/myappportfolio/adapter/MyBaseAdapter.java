package me.makeachoice.myappportfolio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import me.makeachoice.myappportfolio.adapter.item.MyBaseRowItem;


/**
 * MyBaseAdapter - abstract class to act as my Base class for all my custom Adapters
 */
public abstract class MyBaseAdapter extends BaseAdapter{
    Context mContext;
    LayoutInflater mInflator;
}
