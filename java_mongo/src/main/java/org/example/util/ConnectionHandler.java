package org.example.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionHandler {

    private static MongoClient client;
    private static MongoDatabase database;

    static {
        if(client == null || database == null){
            Properties properties = new Properties();

            try(InputStream input = ConnectionHandler.class.getClassLoader().getResourceAsStream("database.properties")){

                if(input == null){
                    throw new Exception("Unable to find database.properties");
                }else{
                    properties.load(input);
                }

                client = MongoClients.create(properties.getProperty("db.url"));
                database = client.getDatabase(properties.getProperty("db.database"));

            }catch(IOException | ClassNotFoundException e){
                throw new RuntimeException("Failed to load databse configuration");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static MongoClient getClient(){
        return client;
    }

    public static MongoDatabase getDatabase(){
        return database;
    }

    public static void closeClient(){
        client.close();
    }

}
