package controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.net.UnknownHostException;
import java.util.Collection;

public class DatabaseController {
    private static final String HOST = "localhost";
    private static final int PORT = 27017;
    private static final String DBNAME = "crayon_db";
    private static final String USER_COLLECTION = "USER_COLLECTION";

    public static String getDBName() {
        return DBNAME;
    }

    private static MongoClient getMongoClient_1() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(HOST, PORT);
        return mongoClient;
    }

    public static MongoClient getMongoClient() throws UnknownHostException {
        // Connect to MongoDB is not mandatory security.
        return getMongoClient_1();
    }

    public static MongoCollection getMongoCollection(String collection, MongoClient client) {
        MongoDatabase database = client.getDatabase(DatabaseController.getDBName());
        return database.getCollection(collection);
    }


    public static DB getMongoDB() throws UnknownHostException{
        return getMongoClient_1().getDB(DatabaseController.getDBName());
    }

    public static void closeMongoConnection(MongoClient client) {
        client.close();
    }

    public static boolean addNewUser(String username, String password, String email) {
        try {
            MongoClient client = getMongoClient();
            MongoCollection collection = getMongoCollection(USER_COLLECTION, client);
            Document doc = new Document();
            doc.append("username", username);
            doc.append("password", password);
            doc.append("email", email);
            collection.insertOne(doc);
            client.close();
            return true;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean authorizeUser(String username, String password) {
        try {
            MongoClient client = getMongoClient();
            MongoCollection collection = getMongoCollection(USER_COLLECTION, client);
            BasicDBObject searchQ = new BasicDBObject();
            searchQ.append("username", username);
            searchQ.append("password", password);
            MongoCursor<Document> cursor = collection.find(searchQ).iterator();
            if (cursor.hasNext()) {
                return true;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return false;

    }

    public static void processNewImage() {

    }
}
