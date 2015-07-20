package ch.turtlestack.dorscht.backend.News;


import com.google.appengine.api.NamespaceManager;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.io.Serializable;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by mdi on 12.03.2015.
 */
@Entity
public class CategoryGroup implements Serializable {

    public List<CategoryItem> Items;
    @Id
    private Long id;
    private String Name;

    public CategoryGroup() {
        super();
    }

    public CategoryGroup(String name) {
        super();
        this.Name = name;
    }

    public static CategoryGroup findByName(long id) {

        NamespaceManager.set("ch.turtlestack.dorscht");

        return ofy().load().type(CategoryGroup.class).id(id).now();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void addItem(CategoryItem item) {
        item.setCategory(this);
        ofy().save().entity(item).now();
    }

    public void addItems(List<CategoryItem> items) {

        NamespaceManager.set("ch.turtlestack.dorscht");

        for (CategoryItem item : items) {
            item.setCategory(this);
        }

        ofy().save().entities(items).now();
    }

    public List<CategoryItem> GetItems() {

        NamespaceManager.set("ch.turtlestack.dorscht");

        return ofy().load().type(CategoryItem.class).ancestor(this).list();
    }
}