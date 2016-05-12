package annotator.server;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    private MongoClient databaseClient;
    private MongoDatabase database;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        this.databaseClient = new MongoClient();
        this.database = this.databaseClient.getDatabase("annotator");

        event.getServletContext().setAttribute("database", this.database);
    }

    @Override
    public void contextDestroyed(ServletContextEvent ev) {
        this.databaseClient.close();
    }

}
