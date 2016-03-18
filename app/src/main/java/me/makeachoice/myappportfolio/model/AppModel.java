package me.makeachoice.myappportfolio.model;

import android.util.Log;

import java.util.ArrayList;

/**
 * AppModel models the information data for an Android Application
 */
public class AppModel {
    private static String DEBUG = "AppModel";
    private ArrayList<AppDetail> mList = new ArrayList<AppDetail>();

    public class AppDetail{

        private String mAppName;
        private String mAppInfo;

        public AppDetail(String appName, String appInfo){
            super();
            Log.d(DEBUG, "AppDetail.create");
            Log.d(DEBUG, "     name: " + appName);
            Log.d(DEBUG, "     info: " + appInfo);

            mAppName = appName;
            mAppInfo = appInfo;
        }

        public String getAppName(){
            return mAppName;
        }

        public String getmAppInfo(){
            return mAppInfo;
        }

        public void setAppName(String appName){
            mAppName = appName;
        }

        public void setAppInfo(String appInfo){
            mAppInfo = appInfo;
        }

        public ArrayList getAppList(){
            return mList;
        }
    }

    public AppDetail getApp(int index){
        return mList.get(index);
    }

    public void addApp(AppDetail app){
        //this inserts app at the end of the list
        mList.add(app);
    }

    public void addApp(AppDetail app, int index){
        //this inserts app at the index and moves everything ahead of it one position
        mList.add(index, app);
    }

    public void updateApp(int index, AppDetail app){
        //this replaces the old app at the index with an updated version
        mList.set(index, app);
    }

    public void removeApp(AppDetail app){
        //removes app object from list
        mList.remove(app);
    }

    public void removeApp(int index){
        //removes app object from list using the index value
        mList.remove(index);
    }

    public boolean checkForApp(AppDetail app){
        return mList.contains(app);
    }

    public int getAppCount(){
        return mList.size();
    }
}
