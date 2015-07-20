package ch.turtlestack.dorscht.backend.Endpoints;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.NamespaceManager;
import com.googlecode.objectify.ObjectifyService;

import java.util.List;

import ch.turtlestack.dorscht.backend.News.CategoryGroup;
import ch.turtlestack.dorscht.backend.News.CategoryItem;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by michael on 07.04.15.
 */
@Api(name = "newsApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "endpoints.backend.dorscht.turtlestack.ch", ownerName = "endpoints.backend.dorscht.turtlestack.ch", packagePath = ""))
public class CategoryEndpoint {

    public CategoryEndpoint() {
        ObjectifyService.register(CategoryGroup.class);
        ObjectifyService.register(CategoryItem.class);
    }

    @ApiMethod(name = "GetAllCategories")
    public List<CategoryGroup> GetAllCategories() {

        List<CategoryGroup> categories = ofy().load().type(CategoryGroup.class).list();

        for (CategoryGroup item : categories) {
            item.Items = item.GetItems();
        }

        return categories;
    }
}
