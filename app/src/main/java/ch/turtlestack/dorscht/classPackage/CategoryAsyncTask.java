package ch.turtlestack.dorscht.classPackage;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ExpandableListView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.List;

import ch.turtlestack.dorscht.BuildConfig;
import ch.turtlestack.dorscht.backend.myApi.MyApi;
import ch.turtlestack.dorscht.backend.myApi.model.CategoryGroup;


/**
 * Created by michael on 12.03.15.
 */

//Param 1 =
public class CategoryAsyncTask extends AsyncTask<Void, Integer, List<CategoryGroup>> {
    private static MyApi myApiService = null;
    private Context context;
    private ExpandableListView listView;

    public CategoryAsyncTask(ExpandableListView pListView, Context pContext) {
        listView = pListView;
        context = pContext;
    }

    @Override
    protected List<CategoryGroup> doInBackground(Void... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    /**
                     * IP före Micheli:
                     * .setRootUrl("http://192.168.0.11:8080/_ah/api/")
                     */
                    //.setRootUrl("http://192.168.0.11:8080/_ah/api/")

                    .setRootUrl(BuildConfig.SERVER_URL)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();

            try {
                //return myApiService.sayHi(name).execute().getData();
                return myApiService.getAllCategories().execute().getItems();
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    protected void onProgressUpdate(Integer... progress) {
        //setProgressPercent(progress[0]);
    }

    @Override
    protected void onPostExecute(List<CategoryGroup> categories) {
        ExpandableAdapter exAdpt = new ExpandableAdapter(categories, context);
        listView.setAdapter(exAdpt);
    }
}
