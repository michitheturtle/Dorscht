/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package ch.turtlestack.dorscht.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.NamespaceManager;
import com.googlecode.objectify.ObjectifyService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import ch.turtlestack.dorscht.backend.News.CategoryGroup;
import ch.turtlestack.dorscht.backend.News.CategoryItem;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * An endpoint class we are exposing
 */
@Api(name = "myApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.dorscht.turtlestack.ch", ownerName = "backend.dorscht.turtlestack.ch", packagePath = ""))
public class MyEndpoint {

    public MyEndpoint() {
        ObjectifyService.register(CategoryGroup.class);
        ObjectifyService.register(CategoryItem.class);
    }

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

    @ApiMethod(name = "CreateSample")
    public CategoryGroup CreateSample() {

        CategoryGroup sport = new CategoryGroup("Sport");

        ofy().save().entity(sport).now();

        List<CategoryItem> sportItems = new ArrayList();
        sportItems.add(new CategoryItem("Fussball", sport));
        sportItems.add(new CategoryItem("Eishockey", sport));
        sportItems.add(new CategoryItem("Tennis", sport));
        sportItems.add(new CategoryItem("Motorsport", sport));
        sportItems.add(new CategoryItem("Radsport", sport));

        sport.addItems(sportItems);

        // Digital
        CategoryGroup digital = new CategoryGroup("Digital");

        ofy().save().entity(digital).now();

        List<CategoryItem> digitalItems = new ArrayList();
        digitalItems.add(new CategoryItem("Soziale Netzwerke", digital));
        digitalItems.add(new CategoryItem("News", digital));
        digitalItems.add(new CategoryItem("Games", digital));
        digitalItems.add(new CategoryItem("Gadgets", digital));

        digital.addItems(digitalItems);

        if (digital.getId() > 0) {
            return sport;
        }

        return new CategoryGroup("KEINS");

    }

    @ApiMethod(name = "GetAllCategories")
    public List<CategoryGroup> GetAllCategories() {

        List<CategoryGroup> categories = ofy().load().type(CategoryGroup.class).list();

        for (CategoryGroup item : categories) {
            item.Items = item.GetItems();
        }

        return categories;
    }

    @ApiMethod(name = "GetAllCategoryItems")
    public List<CategoryItem> GetAllCategoryItems() {

        List<CategoryItem> cars = ofy().load().type(CategoryItem.class).list();

        return cars;
    }

    @ApiMethod(name = "DeleteAll")
    public void DeleteAll() {

        List<CategoryItem> items = ofy().load().type(CategoryItem.class).list();

        ofy().delete().entities(items);

        List<CategoryGroup> categories = ofy().load().type(CategoryGroup.class).list();

        ofy().delete().entities(categories);


    }


}
