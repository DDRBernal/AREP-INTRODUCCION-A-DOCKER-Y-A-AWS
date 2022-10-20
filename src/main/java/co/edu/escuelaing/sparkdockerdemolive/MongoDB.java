package co.edu.escuelaing.sparkdockerdemolive;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;


import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MongoDB {

    private static MongoClient mongoClient;
    private static MongoDatabase database;
    public static void main(String[] args) {
        MongoConnection();
    }

    public static void MongoConnection(){

        // Conexión a base de datos mongodb
        //URL para Atlasdb en la nube
        //              "mongodb+srv://admin:<password>@cluster0.85ubqzs.mongodb.net/?retryWrites=true&w=majority"
        String connstr ="mongodb+srv://admin:admin@cluster0.85ubqzs.mongodb.net/?retryWrites=true&w=majority";
        //URL para conexión local
        //String connstr ="mongodb://localhost:27017/?retryWrites=true&w=majority";
        //Crea objeto de tipo ConnectionString
        ConnectionString connectionString = new ConnectionString(connstr);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        mongoClient = MongoClients.create(settings);
        List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
        System.out.println(databases.size());

        List<Document> lastElementsArray = databases.subList(Math.max(databases.size() - 1, 0), databases.size());

        databases.forEach(db -> System.out.println(db.toJson()));
        database = mongoClient.getDatabase("AREPmongoDB");
    }

    public static void insertMessage(String message) {
        MongoDatabase database = mongoClient.getDatabase("AREPmongoDB");
        MongoCollection<Document> customers = database.getCollection("messages");
        FindIterable<Document> iterable = customers.find();
        MongoCursor<Document> cursor = iterable.iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
        Document messages = new Document("_id", new ObjectId());
        ArrayList<String> data = getData();
        String messageEdited = message.replace("{\"word\":","").replace("}","");
        messages.append("message"+data.size(), messageEdited);
        customers.insertOne(messages);
    }

    public static ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        MongoCollection<Document> customers = database.getCollection("messages");
        FindIterable<Document> iterable = customers.find();
        MongoCursor<Document> cursor = iterable.iterator();
        for (Document d : iterable) {
            System.out.println(d);
            data.add(d.toString());
        }
        return data;
    }
}
