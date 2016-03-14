package me.makeachoice.myappportfolio.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment abstract used for all my fragments
 */
public interface MyFragmentInterface {
    /** onAttach(...) is called when a fragment is first attached to its context. onCreate(...) will
     * will be called after this.
     *
     * OnAttach(Activity) has been deprecated in API level 23
     */
    public void onAttach(Context ctx);

    /** onCreate(...) is called to do initial creation of a fragment. This is called after
     * onAttach(...) and before onCreateView(...)
     *
     * You should initialize essential components of the fragment that you want to retain when
     * the fragment is paused or stopped, then resumed.
     *
     * Note that this can be called while the fragment's activity is still in the process of being
     * created. As such, you can not rely on things like the activity's content view hierarchy being
     * initialized at this point; use onActivityCreated(...)
     */
    public void onCreate(Bundle savedInstanceState);

    /** onCreateView(...) is called when it's time for the fragment to draw its UI for the first
     * time. To draw a UI for your fragment, you must return a View from this method that is the
     * root of your fragment's layout. You can return null if the fragment does not provide a UI.
     *
     * This is called between onCreate(...) and onActivityCreated(...). If you return a View from
     * here, you will later be called in onDestroyView() when the view is being released.
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState);

    /** onStart called when the fragment is visible to the user. This is generally tied to
     * Activity.onStart of the containing Activity's lifecycle
     */
    public void onStart();

    /** onResume is called when the fragment is visible to the user and actively running. This is
     * generally tied to Activity.onResume of the containning Activity's lifecycle
     */
    public void onResume();

    /** onPause() is called when the Fragment is no longer resumed. This is generally tied to
     * Activity.onPause of the containing Activity's lifecylce.
     */
    public void onPause();

    /** onStop()
     *
     */
    public void onStop();

    /** onDestroyView() is called when the view previously created by onCreateView(...) has been
     * detached from the fragment. The next time the fragment needs to be displayed, a new view
     * will be created.
     *
     * This is called after onStop() and before onDestroy(), it is called regardless of whether
     * onCreateView(...) returned a non-null view.
     */
    public void onDestroyView();

    /** onDestroy() is called when the fragment is no longer in use. This is called after onStop()
     * and before onDetach()
     */
    public void onDestroy();

    /** onDetach() is called when the fragment is no longer attached to its activity. This is called
     * after onDestroy()
     */
    public void onDetach();
}
