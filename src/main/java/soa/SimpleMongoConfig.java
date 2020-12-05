package soa;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

// https://www.baeldung.com/spring-data-mongodb-tutorial
@Configuration
public class SimpleMongoConfig {

    @Bean
    public MongoClient mongo() {
        String mongoUser = System.getenv("MONGO_INITDB_ROOT_USERNAME");
        String mongoPassword = System.getenv("MONGO_INITDB_ROOT_PASSWORD");
        String mongoContainerName = System.getenv("MONGODB_CONTAINER_NAME");
        String mongoContainerPort = System.getenv("MONGODB_CONTAINER_PORT");

        ConnectionString connectionString = new ConnectionString(
                "mongodb://" + mongoUser + ":" + mongoPassword + "@" + mongoContainerName + ":" + mongoContainerPort);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), "mongo");
    }
}