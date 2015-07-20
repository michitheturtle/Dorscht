package ch.turtlestack.dorscht;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import ch.turtlestack.dorscht.classPackage.AlarmReceiver;
import ch.turtlestack.dorscht.classPackage.EndpointsAsyncTask;
import ch.turtlestack.dorscht.classPackage.NavigationDrawerFragment;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, CategoriesFragment.OnFragmentInteractionListener {


    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ch.turtlestack.dorscht.R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(ch.turtlestack.dorscht.R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                ch.turtlestack.dorscht.R.id.navigation_drawer,
                (DrawerLayout) findViewById(ch.turtlestack.dorscht.R.id.drawer_layout));

        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));
    }

    @SuppressWarnings("deprecation")
    public void notify(View vobj) {

        PendingIntent mAlarmSender = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(getApplicationContext(), AlarmReceiver.class), 0);

        //Set the alarm to 20 seconds from now
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, 20);
        long firstTime = c.getTimeInMillis();
        // Schedule the alarm!
        AlarmManager am = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, firstTime, mAlarmSender);


/*        String title = "ActA Reminder";
        String subject = "Apfel essen";
        String body = "Es wäre jetzt an der Zeit einen Apfel zu essen! Pörschtli!";

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 2);


        //System.currentTimeMillis()

      //  NM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = new Notification(android.R.drawable.stat_notify_voicemail, title,  calendar.getTimeInMillis());

        PendingIntent pending = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);

        notify.setLatestEventInfo(getApplicationContext(), subject, body, pending);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                pending);


      //  NM.notify(0, notify);*/
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // do something on back.
            this.finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        //Hier wird entschieden welches Fragment angezeigt werden soll

        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = null;

        //Wenn 3. Eintrag (position 2) dann Kategorien anzeigen
        if (position == 2) {
            //Categories Fragment laden
            fragment = new CategoriesFragment();

        } else {
            fragment = PlaceholderFragment.newInstance(position + 1);
            // update the main content by replacing fragments

        }

        fragmentManager.beginTransaction()
                .replace(ch.turtlestack.dorscht.R.id.container, fragment)
                .commit();


    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(ch.turtlestack.dorscht.R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(ch.turtlestack.dorscht.R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == ch.turtlestack.dorscht.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //durch CategoriesFragment Interface
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(ch.turtlestack.dorscht.R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

    }

}
