package com.rest.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.rest.clients.MongoDBClient;
import com.rest.dao.Role;
import com.rest.utils.TPCConstants;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RoleManager {

    //fetch roles from mongo db
    @Autowired
    MongoDBClient mongoDBClient;

    @Autowired
    ObjectMapper objectMapper;


    public Role getRoleDetails(String role_id) {
        MongoCollection<Document> mongoCollection = mongoDBClient.buildMongoCollection(TPCConstants.DB_STRING,TPCConstants.MONGO_ROLE_COLLECTION,TPCConstants.DB_NAME);
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("role_id",role_id);
        MongoCursor<Document> mongoCursor = mongoDBClient.find(mongoCollection,basicDBObject);
        Document document = mongoCursor.next();
        String result = document.toJson();
        Role role = null;
        try {
            role = objectMapper.readValue(result, Role.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return role;
    }
    //documents to json conversion
    //json to Role object
}
