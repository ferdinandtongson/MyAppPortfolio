package me.makeachoice.myappportfolio.model;

import android.util.Log;

import java.util.ArrayList;

/**
 * AppDemoModel models the information data for a demo Android Application
 */
public class AppDemoModel {
    private static String DEBUG = "AppDemoModel";
    private ArrayList<AppDetail> mList = new ArrayList<AppDetail>();

    public class AppDetail{

        private String mName;
        private String mDescription;
        private int mIconId = -1;
        private String[] mTags = null;
        private String mVideoFile = "";
        private String mAppLink = "";

        public AppDetail(String name, String description){
            super();
            Log.d(DEBUG, "AppDetail.create");
            Log.d(DEBUG, "     name: " + name);
            Log.d(DEBUG, "     info: " + description);

            mName = name;
            mDescription = description;
        }

        public AppDetail(String name, String description, int iconId){
            super();

            mName = name;
            mDescription = description;
            mIconId = iconId;
        }

        public AppDetail(String name, String description, int iconId, String[] tags,
                         String videoFile, String appLink){
            super();

            mName = name;
            mDescription = description;
            mIconId = iconId;
            mTags = tags;
            mVideoFile = videoFile;
            mAppLink = appLink;
        }

        public String getName(){return mName;}
        public String getDescription(){return mDescription;}
        public int getIconId(){return mIconId;}
        public String[] getTags(){return mTags;}
        public String getVideoFile(){return mVideoFile;}
        public String getAppLink(){return mAppLink;}


        public void setName(String appName){mName = appName;}
        public void setDescription(String description){mDescription = description;}
        public void setIconId(int iconId){mIconId = iconId;}
        public void setTags(String[] tags){mTags = tags;}
        public void setVideoFile(String file){mVideoFile = file;}
        public void setAppLink(String link){mAppLink = link;}


        public ArrayList getAppList(){return mList;}
    }

    public AppDemoModel(){
        //AppDemoModel constructor
    }

    public AppDetail getApp(int index){
        return mList.get(index);
    }

    public void addApp(String name, String description){
        mList.add(new AppDetail(name, description));
    }

    public void addApp(String name, String description, int iconId){
        mList.add(new AppDetail(name, description, iconId));
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
