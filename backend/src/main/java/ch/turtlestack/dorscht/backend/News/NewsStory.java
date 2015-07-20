package ch.turtlestack.dorscht.backend.News;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.Set;

/**
 * Created by michael on 07.04.15.
 */
@Entity
public class NewsStory {

    Set<Key<CategoryItem>> Categories;
    @Id
    private Long id;
    private String Titel;
    private String Message;
    private String Url;

}
