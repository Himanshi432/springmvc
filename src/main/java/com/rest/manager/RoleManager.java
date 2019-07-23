package com.rest.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.util.JSON;
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


    public UpdateResult updateRoleDetails(String role_id, Role role_body) {
        MongoCollection<Document> mongoCollection = mongoDBClient.buildMongoCollection(TPCConstants.DB_STRING,TPCConstants.MONGO_ROLE_COLLECTION,TPCConstants.DB_NAME);
        BasicDBObject basicDBObject = new BasicDBObject();
        Document query = new Document();
        query.put("role_id",role_id);
        Gson gson = new Gson();
        BasicDBObject dbUpdateDocument = (BasicDBObject) JSON.parse(gson.toJson(role_body));
        Document update_role_document = new Document(dbUpdateDocument.toMap());

        return mongoDBClient.update(mongoCollection,query,update_role_document);
    }
}
