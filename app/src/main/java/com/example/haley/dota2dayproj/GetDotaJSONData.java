package com.example.haley.dota2dayproj;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*
 *  packaged private upon creation of the class
 *  although this does not extend AsyncData, it does implement
 *   GetRawJSONData which does run asynchronously on a background
 *   Thread; THis means that anything using this GetDotaJSONData
 *   Class wont get any data back immediately. To resolve this,
 *   we are going to use the same callback mechanism as we did
 *   for getRawData. So we are going to use the sme callback mechanism
 *   as we did for getRawData. So we will create a field to store
 *   the callback object and then define an interface which will call
 *   that private
 * */

//This asyncTasks contains the query we want to use to actually filter the flickr feed
//The first parameter is going to be a string -> the same parameter thart we currently
// are parsing to execute on the same
//the second parameter -> is Void because we do not want to display any type of progress bar
//the third-> The list of Heroes we will be returning
class GetDotaJSONData extends AsyncTask<String, Void, List<Hero>> implements GetRawData.OnDownLoadComplete{
    private static final String TAG = "getDotaJSONData";


    private List<Hero> HeroList = null;
    private String baseURL; //the url prior to adding parameters to pull JSON object
    private String language; //different languages (English, Spanish, German, French etc.)
    private boolean matchAll;

    private final OnDataAvailable callBack;
    private boolean runningOnSameThread = false;


    /*
     * This class implements OnDownloadComplete so itr can get callbacks from
     * getRawData and it also defines its own interface OnDataAvailable so that it
     * can send a callback to MainActivity
     *
     * */
    interface OnDataAvailable {
        void onDataAvailable(List<Hero> data, DownloadStatus status);
    }

    //constructor
    public GetDotaJSONData(OnDataAvailable callBack, String baseURL, String language, boolean matchAll) {
        this.baseURL = baseURL;
        this.language = language;
        this.matchAll = matchAll;
        this.callBack = callBack;
        System.out.println("baseURL " + baseURL);
    }


    /*
     *   most of the code here is similar to what is being done in mainactivities
     *   OnCreate method where it creates a new GetRawData object and then calls
     *   the execute method
     *   However, before the above is done we are going to Create a URI with the
     *   correct parameters via a creaURI method.
     * */
    void executeOnSameThread(String searchCriteria){
        Log.d(TAG, "executeOnSameThread: starts...");
        runningOnSameThread = true;
        String destinationURI = createUri(searchCriteria, language, matchAll);

        GetRawData getRawData = new GetRawData(this);
        getRawData.execute(destinationURI);
        Log.d(TAG, "executeOnSameThread: ends...");

    }


    @Override
    protected void onPostExecute(List<Hero> Heros) {
        Log.d(TAG, "onPostExecute: Starts...");

        if(callBack != null){
            callBack.onDataAvailable(HeroList, DownloadStatus.OK);
        }

        Log.d(TAG, "onPostExecute: Ends...");
    }


    @Override
    protected List<Hero> doInBackground(String... params) {
        Log.d(TAG, "doInBackground: Starts...");

        String destinationURL = createUri(params[0], language, matchAll);

        GetRawData getRawData = new GetRawData(this);

        getRawData.runInSameThread(destinationURL);

        Log.d(TAG, "doInBackground: Ends...");

        return HeroList;
    }

    private String createUri(String searchCriteria, String lang, boolean mAll){
        Log.d(TAG, "createUri: starts");

        System.out.println("Uri.parse(baseURL).toString(): " + Uri.parse(baseURL).toString());

        return Uri.parse(baseURL).toString();
    }

    //USING ORG.JSON Library
    //data is the string we created to represent the jsonObject in our code
    //this is what we will be parsing
    //status is verifying that it was successful in creating the "JSON OBject String (data)"
    @Override
    public void onDownloadComplete(String data, DownloadStatus status) {
        Log.d(TAG, "onDownloadComplete: starts... Status = " + status);


        //we are going to create an arraylist which will hold the Heros and the other
        //parsed json info.
        if(status == DownloadStatus.OK){
            HeroList = new ArrayList<>();

            try{
                //JSONArray is an item is going to identify a particular Hero in the JSON Object
                //Basically... this targets an array within the JSON Object we are currently parsing.
                //nd within that array are more JSON objects with various fields
               // JSONObject jsonData = new JSONObject(data);
               // JSONArray heroStatsArray = jsonData.getJSONArray(data);

                JSONArray heroStatsArray = new JSONArray(data);

                /*
                 * HeroUrl will become thew image field of the Hero object and is passed as the last
                 *   parameter to the constructor and if we have a look at the Hero class, the last
                 *   parameter in the constructor sets the value of the image so we'll be using that
                 *   field to display the image for each Hero in the list
                 *
                 * When an item in the list is tapped, we are going to launch another activity to
                 *   display the Hero much larger so that it fills the screen.
                 *   To do this, we need to use the link value; which is why it is separate
                 *   to the Hero url so getImage will give us the URL of the Hero to show in the
                 *   initial list and getLink will provide the URL of the full-size picture
                 *   !!!!FYI these functions discussed above are both below and in the Hero class
                 *
                 *
                 * */
                for(int i = 0; i < heroStatsArray.length(); i++){
                    JSONObject jsonHero = heroStatsArray.getJSONObject(i);
                    int id = jsonHero.getInt("id");
                    String name = jsonHero.getString("localized_name");
                    int pick_1 = jsonHero.getInt("1_pick");
                    int pick_2 = jsonHero.getInt("2_pick");
                    int pick_3 = jsonHero.getInt("3_pick");
                    int pick_4 = jsonHero.getInt("4_pick");
                    int pick_5 = jsonHero.getInt("5_pick");
                    int pick_6 = jsonHero.getInt("6_pick");
                    int pick_7 = jsonHero.getInt("7_pick");
                    String heroIcon = "cdn.dota2.com" + jsonHero.getString("img");
                    String heroImg = "cdn.dota2.com" + jsonHero.getString("icon");

                    //create a Hero object
                    Hero HeroObject = new Hero(id, name, pick_1, pick_2, pick_3, pick_4, pick_5, pick_6, pick_7, heroIcon, heroImg);

                    HeroObject.toString();

                    //store this new Hero object into the list
                    HeroList.add(HeroObject);

                    //logged here to verify everything has worked.
                    Log.d(TAG, "onDownloadComplete" + HeroObject.toString());


                    //the code will keep looping until it has processed all the items in the
                    //JSON Array and we will end up with a list containing the details for each
                    //of the Heros from the flickr feed


                    /*!!!!!!!This is JSON PARSING IN A NUTSHELL!!!!!!!*/
                }

            } catch (JSONException jsone){
                jsone.printStackTrace();
                Log.e(TAG, "onDownloadComplete: Error processing JSON Data" + jsone.getMessage());
                status = DownloadStatus.FAILED_OR_EMPTY;
            }

            /*Once all of this is done we need to notify the calling class that everything
             * is done and send it the list of Heros that we've actually created*/

            if(runningOnSameThread && callBack != null){
                //now inform the caller that processing is done == possibly returning null if there
                //was an error

                callBack.onDataAvailable(HeroList, status);
            }

            Log.d(TAG, "onDownloadComplete: ends");

        }
    }
}

