package me.makeachoice.myappportfolio.controller.housekeeper;

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
    public MainKeeper(Boss boss){
        mBoss = boss;
    }


/**
 * AppList will display a list of names of applications held in the portfolio. There are two types
 * that can be displayed 1)a simple list of names or 2)a list of names with a corresponding icon
 */


}
