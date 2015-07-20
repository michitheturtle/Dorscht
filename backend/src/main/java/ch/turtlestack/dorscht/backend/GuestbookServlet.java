package ch.turtlestack.dorscht.backend; /**
 * Created by mdi on 01.06.2015.
 */
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.turtlestack.dorscht.backend.News.CategoryGroup;
import ch.turtlestack.dorscht.backend.News.Feed;
import ch.turtlestack.dorscht.backend.News.FeedMessage;
import ch.turtlestack.dorscht.backend.News.RSSFeedParser;

public class GuestbookServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Hello, world");

        MyEndpoint endpoint = new MyEndpoint();

        if (req.getParameter("initData") != null) {
            endpoint.CreateSample();
        }

        List<CategoryGroup> categoryGroups = endpoint.GetAllCategories();

        if(categoryGroups != null){
            if(categoryGroups.isEmpty()){
                resp.getWriter().println("No data...");
            }
            else{
                resp.getWriter().println("Yo.. we have " + categoryGroups.size() + " categories");
            }
        }

        RSSFeedParser parser = new RSSFeedParser("http://www.nzz.ch/mehr/digital.rss"); //"http://www.20min.ch/rss/rss.tmpl?type=channel&get=1");
        Feed feed = parser.readFeed();

        resp.getWriter().println("Yo.. we have " + feed.getMessages().size() + " news in category: " + feed.getDescription());

        for (FeedMessage feedMessage : feed.getMessages()) {
            resp.getWriter().println(feedMessage.getGuid() + " : " + feedMessage.getTitle());
        };

    }
}