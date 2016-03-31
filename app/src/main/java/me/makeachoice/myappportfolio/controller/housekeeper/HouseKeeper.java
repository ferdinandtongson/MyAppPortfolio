package me.makeachoice.myappportfolio.controller.housekeeper;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import java.util.HashMap;

import me.makeachoice.myappportfolio.controller.Boss;

/**
 * Created by Usuario on 3/25/2016.
 */
public abstract class HouseKeeper {
    //mBoss - application context object
    Boss mBoss;

    //mActivityContext - context object that follows the Activity lifecycle
    Context mActivityContext;

    //mFragmentManager - manages fragments for the HouseKeeper
    FragmentManager mFragmentManager;

    //mMapFragmentType - hashmap used to hold different fragments types and their type variations
    HashMap<String, Integer> mMapFragmentType;

}
