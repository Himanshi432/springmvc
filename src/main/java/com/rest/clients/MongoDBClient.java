package com.rest.clients;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
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

    public void insert(MongoCollection<Document> mongoCollection,Document document){
        mongoCollection.insertOne(document);
    }

    public UpdateResult update(MongoCollection<Document> mongoCollection, Document queryDocument, Document updateDocument){
        return mongoCollection.replaceOne(queryDocument,updateDocument,new UpdateOptions().upsert(false));
    }

    public DeleteResult delete(MongoCollection<Document> mongoCollection, Document queryDocument){
        return mongoCollection.deleteOne(queryDocument);
    }
}
