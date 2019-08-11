package com.rest.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.util.JSON;
import com.rest.clients.MongoDBClient;
import com.rest.dao.Client;
import com.rest.exceptions.AddressException;
import com.rest.exceptions.DbExceptions;
import com.rest.utils.TPCConstants;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.rest.utils.TPCConstants.*;

@Service
public class ClientManager {

    @Autowired
    public MongoDBClient mongoDBClient;

    @Autowired
    ObjectMapper objectMapper;

    public ResponseEntity getClientDetails(String client_id) throws DbExceptions{
        //Get client data based on client id from DB
        if(client_id == null || client_id == "")
            throw new DbExceptions(MONGO_ID_NULL);
        MongoCollection<Document> mongoCollection = mongoDBClient
                .buildMongoCollection(TPCConstants.DB_STRING,TPCConstants.MONGO_CLIENT_COLLECTION,TPCConstants.DB_NAME);
        BasicDBObject findQuery = new BasicDBObject();
        findQuery.put("client_id",client_id);
        MongoCursor<Document> mongoCursor = mongoDBClient.find(mongoCollection,findQuery);
        Document result = mongoCursor.next();
        Client client = null;
        try {
            client = objectMapper.readValue(result.toJson(),Client.class);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //Wrap the response to client DAO object and return DAO object
        return ResponseEntity.ok(client);
    }

    public ResponseEntity createClient(Client requestBody) throws DbExceptions {
        MongoCollection<Document> mongoCollection = mongoDBClient
                .buildMongoCollection(TPCConstants.DB_STRING,TPCConstants.MONGO_CLIENT_COLLECTION,TPCConstants.DB_NAME);
        Gson gson = new Gson();
        BasicDBObject dbDocument = (BasicDBObject) JSON.parse(gson.toJson(requestBody));
        mongoDBClient.insert(mongoCollection,new Document(dbDocument.toMap()));
        return ResponseEntity.ok(MONGO_CREATED);
    }

    public ResponseEntity updateClient(Client requestBody, String client_id) throws DbExceptions{
        MongoCollection<Document> mongoCollection = mongoDBClient
                .buildMongoCollection(TPCConstants.DB_STRING,TPCConstants.MONGO_CLIENT_COLLECTION,TPCConstants.DB_NAME);
        Gson gson = new Gson();
        BasicDBObject dbUpdateDocument = (BasicDBObject) JSON.parse(gson.toJson(requestBody));
        Document queryDocument = new Document();
        System.out.println("PUT request client id is " + client_id);
        queryDocument.put("client_id",client_id);
        return ResponseEntity.ok(mongoDBClient.update(mongoCollection,queryDocument,new Document(dbUpdateDocument.toMap())));
    }

    public ResponseEntity deleteClient(String client_id) throws DbExceptions{
        MongoCollection<Document> mongoCollection = mongoDBClient
                .buildMongoCollection(TPCConstants.DB_STRING,TPCConstants.MONGO_CLIENT_COLLECTION,TPCConstants.DB_NAME);
        Document queryDocument = new Document();
        queryDocument.put("client_id",client_id);
        return ResponseEntity.ok(mongoDBClient.delete(mongoCollection,queryDocument));
    }

}
