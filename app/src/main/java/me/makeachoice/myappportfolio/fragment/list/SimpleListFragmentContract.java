package me.makeachoice.myappportfolio.fragment.list;

/**
 * SimpleListFragmentContract - static values used across all SimpleListFragmentX classes
 */
public class SimpleListFragmentContract {
    /* To prevent someone from accidentally instantiating the contract class */
    public SimpleListFragmentContract () {}

    public static abstract class Value {
        public static final String BUNDLE_KEY_LIST = "List";
        public static final String BUNDLE_KEY_LAYOUT_PRT = "Layout_portrait";
        public static final String BUNDLE_KEY_LAYOUT_LND = "Layout_landscape";
    }
}