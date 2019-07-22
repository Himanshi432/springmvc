package com.rest.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;
import com.rest.clients.MongoDBClient;
import com.rest.dao.Client;
import com.rest.utils.TPCConstants;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ClientManager {

    @Autowired
    public MongoDBClient mongoDBClient;

    @Autowired
    ObjectMapper objectMapper;

    public Client getClientDetails(String client_id){
        //Get client data based on client id from DB
        MongoCollection<Document> mongoCollection = mongoDBClient
                .buildMongoCollection(TPCConstants.DB_STRING,TPCConstants.MONGO_CLIENT_COLLECTION,TPCConstants.DB_NAME);
        BasicDBObject findQuery = new BasicDBObject();
        findQuery.put("client_id",client_id);
        MongoCursor<Document> mongoCursor = mongoDBClient.find(mongoCollection,findQuery);
        Document result = mongoCursor.next();
        Client client = null;
        try {
            client = objectMapper.readValue(result.toJson(),Client.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Wrap the response to client DAO object
        //return DAO object
        return client;
    }

    public String createClient(Client requestBody){
        MongoCollection<Document> mongoCollection = mongoDBClient
                .buildMongoCollection(TPCConstants.DB_STRING,TPCConstants.MONGO_CLIENT_COLLECTION,TPCConstants.DB_NAME);
        Gson gson = new Gson();
        BasicDBObject dbDocument = (BasicDBObject) JSON.parse(gson.toJson(requestBody));
        mongoDBClient.insert(mongoCollection,new Document(dbDocument.toMap()));
        return "client added";
    }
}
