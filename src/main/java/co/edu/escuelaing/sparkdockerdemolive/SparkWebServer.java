package co.edu.escuelaing.sparkdockerdemolive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static spark.Spark.*;


//co.edu.escuelaing.sparkdockerdemolive.SparkWebServer.java
public class SparkWebServer {

    //
    //server.port=8081
    //
    //mongodb.database=tasks
    //mongodb.connection.string=mongodb://localhost:27017
    public static void main(String... args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        MongoDB.MongoConnection();
        port(getPort());
        System.out.println(getPort());
        RoundRobin roundRobin = new RoundRobin();
        staticFiles.location("/files");
        get("hello", (req,res) -> "Hello Docker!");
        get("/home", (req,res) -> {
            return "{\"confirm\":" + "ok" + "}";
        });
        get("/showWords", (req,res) -> {
            ArrayList<String> data = MongoDB.getData();
            String serverRoundRobin = roundRobin.getServer();
            System.out.println("server: "+serverRoundRobin);
            return data;
        });
        post("/addWord", (req, res) -> {
            res.status(200);
            String serverRoundRobin = roundRobin.getServer();
            System.out.println("server: "+serverRoundRobin);
            MongoDB.insertMessage(req.body());
            return "word added";
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}
