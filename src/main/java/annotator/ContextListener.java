package annotator;

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
    public void contextInitialized(ServletContextEvent ev) {
        this.databaseClient = new MongoClient();
        this.database = this.databaseClient.getDatabase("annotator");
    }

    @Override
    public void contextDestroyed(ServletContextEvent ev) {
        this.databaseClient.close();
    }

    public MongoDatabase getDatabase() {
        return this.database;
    }

}
