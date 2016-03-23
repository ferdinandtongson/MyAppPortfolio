package me.makeachoice.myappportfolio.fragment;

import me.makeachoice.myappportfolio.controller.maid.Maid;

/**
 * Created by Usuario on 3/23/2016.
 */
public interface MyFragmentInterface {
    String KEY_LAYOUT = "Layout";
    String KEY_MAID_NAME = "MaidName";


    void setLayout(int id);
    void setMaidName(String name);
}
