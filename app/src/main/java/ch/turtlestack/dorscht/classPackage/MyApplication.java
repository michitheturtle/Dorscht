package ch.turtlestack.dorscht.classPackage;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.google.android.gms.plus.model.people.Person;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Florian on 26.01.2015.
 *
 * Android Application Class, handles the application as a global. It is initialized before
 * anything else is started (see Manifest) and is declared (filled with a Person Object in the
 * GooglePlayServicesActivity.onConnected() method and called by several different activities.
 */

public class MyApplication extends Application {

    /**
     * App Context.
     */
    private static Context context;
    private JSONObject jUser;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
            }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
            }

    @Override
    public void onTerminate() {
        super.onTerminate();
            }

    /**
     * Set the UserAccount Object in the Application Class.
     * @param user
     */
    public void setUserAccount(Person user){
        try {
            JSONObject jUser = new JSONObject();
            jUser.put("prename", "Hanspeter");
            jUser.put("name", "Peterhans");
            jUser.put("score", new Integer(200));
            jUser.put("current", new Double(152.32));
            jUser.put("nickname", "kingFU");

        } catch (JSONException e){
            System.out.println("MyApplication.setUserAccount");
        }


    }

    /**
     * Get the UserAccount Object from the Application Class.
     * @return The UserData as a Person Class Object
     */
    public JSONObject getUserAccount(){
        try {
            JSONObject jUser = new JSONObject();
            jUser.put("prename", "Hanspeter");
            jUser.put("name", "Peterhans");
            jUser.put("score", new Integer(200));
            jUser.put("current", new Double(152.32));
            jUser.put("nickname", "kingFU");

            return jUser;

        } catch (JSONException e){
            System.out.println("MyApplication.setUserAccount");
            return null;
        }
    }

    /**
     * Get the Application Context
     * @return the Application Context
     */
    public static Context getAppContext() {
        return context;
    }



}

