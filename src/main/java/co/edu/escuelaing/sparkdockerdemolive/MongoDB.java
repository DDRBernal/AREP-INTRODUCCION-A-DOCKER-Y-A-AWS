package co.edu.escuelaing.sparkdockerdemolive;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MongoDB {

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

        //Crea objeto con configuraciones para el cliente mongo
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();

        //Crea una instancia del cliente mongo conectado a la base de datos
        MongoClient mongoClient = MongoClients.create(settings);

        //Obtiene una lista de objetos bson representando las base de datos disponibles
        // bson es una versión binaria de json creada para mejorar desempeño de mongo.

        List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
        System.out.println(databases.size());
        databases.forEach(db -> System.out.println(db.toJson()));

        //Obtener objeto base de datos. Si no existe lo crea
        MongoDatabase database = mongoClient.getDatabase("myFirstDatabase");
        //Obtener objeto colección. Si no existe lo crea
        MongoCollection<Document> customers = database.getCollection("customer");

        //Obtiene un iterable
        FindIterable<Document> iterable = customers.find();
        MongoCursor<Document> cursor = iterable.iterator();

        //Recorre el iterador obtenido del iterable
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }

        //Crea un documento BSON con el cliente
        Document customer = new Document("_id", new ObjectId());
        customer.append("firstName", "David");
        customer.append("lastName", "Otálora");
        customer.append("_class", "co.edu.escuelaing.mongodemo.Customer.Customer");

        //Agrega el nuevo cliente a la colección
        customers.insertOne(customer);

        //Lee el iterable directamente para imprimir documentos
        for (Document d : iterable) {
            System.out.println(d);
        }
    }

}
