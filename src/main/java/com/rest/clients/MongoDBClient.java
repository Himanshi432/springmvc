package com.rest.clients;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.stereotype.Service;

@Service
public class MongoDBClient {

    public MongoCollection<Document> buildMongoCollection(String connection_string, String collection_name, String dbName){
        MongoClient mongoClient = new MongoClient("localhost",27017);
        return mongoClient.getDatabase(dbName).getCollection(collection_name);
    }

    public MongoCursor<Document> find(MongoCollection<Document> mongoCollection, BasicDBObject findQuery){
        return mongoCollection.find(findQuery).iterator();
    }
}
