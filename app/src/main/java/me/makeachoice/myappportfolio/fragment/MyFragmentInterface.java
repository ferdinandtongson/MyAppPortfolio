package me.makeachoice.myappportfolio.fragment;


/**
 * MyFragmentInterface is used to keep both MyFragment and MyListFragment abstract classes as
 * closely related as possible.
 *
 * At the moment, it seems that this interface is not very useful but time will tell once the apps
 * being built become more complicated.
 */
public interface MyFragmentInterface {
    String KEY_LAYOUT = "Layout";

    void setLayout(int id);

}
