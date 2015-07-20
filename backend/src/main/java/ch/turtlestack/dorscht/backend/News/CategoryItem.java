package ch.turtlestack.dorscht.backend.News;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

import java.io.Serializable;

/**
 * Created by michael on 15.03.15.
 */
@Entity
public class CategoryItem implements Serializable {

    @Id
    Long id;
    private String name;
    private String url;

    @Parent
    private Key<CategoryGroup> category;

    public CategoryItem() {
        super();
    }

    public CategoryItem(String name, CategoryGroup cat) {
        super();
        this.name = name;
        this.url = "";

    }

    public void setCategory(CategoryGroup cat) {
        this.category = Key.create(CategoryGroup.class, cat.getId());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}