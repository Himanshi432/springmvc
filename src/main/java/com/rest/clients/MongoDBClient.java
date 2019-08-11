package com.rest.clients;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.rest.exceptions.DbExceptions;
import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.rest.utils.TPCConstants.*;

@Service
public class MongoDBClient {

    public MongoCollection<Document> buildMongoCollection(String connection_string, String collection_name, String dbName) {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        return mongoClient.getDatabase(dbName).getCollection(collection_name);
    }

    public MongoCursor<Document> find(MongoCollection<Document> mongoCollection, BasicDBObject findQuery) throws DbExceptions {

        MongoCursor<Document> cursor =  mongoCollection.find(findQuery).iterator();
        if(!cursor.hasNext()){
            throw new DbExceptions(API_NO_MATCH_FOUND);
        }
        return cursor;
    }

    public void insert(MongoCollection<Document> mongoCollection, Document document) throws DbExceptions {
        try {
            mongoCollection.insertOne(document);
        }
        catch (Exception e) {
            if (e.getMessage().contains(MONGO_DUPLICATE_KEY)) {
                throw new DbExceptions(API_DUPLICATE_KEY);
            }
        }
    }

    public UpdateResult update(MongoCollection<Document> mongoCollection, Document queryDocument, Document updateDocument) throws DbExceptions {
        UpdateResult updateResult = null;
        updateResult = mongoCollection.replaceOne(queryDocument, updateDocument, new UpdateOptions().upsert(false));
        if (updateResult.getMatchedCount() == 0) {
            throw new DbExceptions(API_NO_MATCH_FOUND);
        }
        return updateResult;
    }

    public DeleteResult delete(MongoCollection<Document> mongoCollection, Document queryDocument) throws DbExceptions {
        DeleteResult deleteResult = null;
        deleteResult = mongoCollection.deleteOne(queryDocument);
        if (deleteResult.getDeletedCount() == 0) {
            throw new DbExceptions(API_NO_MATCH_FOUND);
        }
        return deleteResult;
    }
}
