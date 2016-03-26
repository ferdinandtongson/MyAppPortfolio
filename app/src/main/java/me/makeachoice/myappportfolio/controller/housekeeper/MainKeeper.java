package me.makeachoice.myappportfolio.controller.housekeeper;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.widget.Toast;

import me.makeachoice.myappportfolio.R;
import me.makeachoice.myappportfolio.controller.Boss;

/**
 * Created by Usuario on 3/25/2016.
 */
public class MainKeeper extends HouseKeeper{
/**
 * MainKeeper initializes and takes care of the Main Activity details of MyAppPortfolio. The Main
 * Activity will display a list of Applications held in the portfolio and will initialize the
 * appropriate Maid to handle the Fragments that will be displayed in the Activity.
 *
 * The MainKeeper will take care of the communication between the Boss and the Fragment and it will
 * prepare the Maid in handling the details of the Fragment creation.
 *
 * MainKeeper will be able to display the following fragments:
 *      AppList
 *      AppListIcon
 *      AppGrid
 *      AppGridInfo
 *      AppInfo
 *
 * Variables from HouseKeeper:
 *      Boss mBoss
 *
 * Implements Boss.xxxBridge Methods:
 *
 *
 * Implements xxxMaid.xxxBridge Methods:
 *
 *
 */
    private Context mActivityContext;
    public MainKeeper(Boss boss, Context ctx){
        mBoss = boss;
        mActivityContext = ctx;
    }

    private int LAYOUT_MAIN = R.layout.activity_main;
    private int LAYOUT_MAIN_CONTAINER = R.id.fragment_container;

    private int TOOLBAR_MAIN = R.id.toolbar;
    private int MENU_MAIN = R.menu.menu_main;


    public int getActivityLayout(){
        return LAYOUT_MAIN;
    }

    public int getActivityContainer(){
        return LAYOUT_MAIN_CONTAINER;
    }



    public int getMenuId(){
        return MENU_MAIN;
    }

    public int getToolbarId(){
        return TOOLBAR_MAIN;
    }

    public void onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        String strAction;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_bar_main_simple) {
            strAction = mBoss.getString(R.string.action_bar_main_simple);
        }
        else if (id == R.id.action_bar_main_advance) {
            strAction = mBoss.getString(R.string.action_bar_main_advance);
        }
        else if (id == R.id.action_bar_main_grid) {
            strAction = mBoss.getString(R.string.action_bar_main_grid);
        }
        else {
            strAction = "no action selected";
        }

        Toast toast = Toast.makeText(mActivityContext, strAction, Toast.LENGTH_SHORT);
        toast.show();

    }





    private FragmentManager mManager;
    public void setFragmentManager(FragmentManager manager){
        mManager = manager;
        mManager.beginTransaction().add(LAYOUT_MAIN_CONTAINER, mBoss.getListFragment()).commit();
    }


/**
 * AppList will display a list of names of applications held in the portfolio. There are two types
 * that can be displayed 1)a simple list of names or 2)a list of names with a corresponding icon
 */


}
