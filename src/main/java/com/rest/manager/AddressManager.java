package com.rest.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.util.JSON;
import com.rest.clients.MongoDBClient;
import com.rest.dao.Address;
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
public class AddressManager {

    @Autowired
    MongoDBClient mongoDBClient;

    @Autowired
    ObjectMapper objectMapper;

    public ResponseEntity getAddressDetails(String address_id) throws AddressException,DbExceptions {

        if(address_id == null || address_id == "")
        throw new AddressException(MONGO_ID_NULL);

        System.out.println("address_id="+address_id);
        MongoCollection<Document> mongoCollection = mongoDBClient
                .buildMongoCollection(TPCConstants.DB_STRING,TPCConstants.MONGO_ADDRESS_COLLECTION,TPCConstants.DB_NAME);
        BasicDBObject getQuery = new BasicDBObject();
        getQuery.put("address_id",address_id);
        MongoCursor<Document> mongoCursor = mongoDBClient.find(mongoCollection,getQuery);
        if(!mongoCursor.hasNext()) {
            throw new AddressException(MONGO_NO_MATCH_FOUND);
        }
        Document result = mongoCursor.next();
        Address foundaddress = null;
        try {
            foundaddress = objectMapper.readValue(result.toJson(), Address.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(foundaddress);
    }

    public ResponseEntity addNewAddress(Address newaddressbody) throws DbExceptions {
        MongoCollection<Document> mongoCollection = mongoDBClient
                .buildMongoCollection(TPCConstants.DB_STRING, TPCConstants.MONGO_ADDRESS_COLLECTION, TPCConstants.DB_NAME);
        Gson gson = new Gson();
        BasicDBObject dbDocument = (BasicDBObject) JSON.parse(gson.toJson(newaddressbody));
        mongoDBClient.insert(mongoCollection, new Document(dbDocument.toMap()));
        return ResponseEntity.ok(MONGO_CREATED);
    }

    public ResponseEntity updateAddress(String address_id, Address addressbody) throws DbExceptions{
        MongoCollection<Document> mongoCollection = mongoDBClient
                .buildMongoCollection(TPCConstants.DB_STRING,TPCConstants.MONGO_ADDRESS_COLLECTION,TPCConstants.DB_NAME);
        Gson gson = new Gson();
        BasicDBObject dbDocument = (BasicDBObject) JSON.parse(gson.toJson(addressbody));
        Document querydocument = new Document();
        querydocument.put("address_id",address_id);
        return ResponseEntity.ok(mongoDBClient.update(mongoCollection,querydocument,new Document(dbDocument.toMap())));
    }

    public ResponseEntity deleteAddress(String address_id) throws DbExceptions{
        MongoCollection<Document> mongoCollection = mongoDBClient
                .buildMongoCollection(TPCConstants.DB_STRING,TPCConstants.MONGO_ADDRESS_COLLECTION,TPCConstants.DB_NAME);
        Document querydocument = new Document();
        querydocument.put("address_id",address_id);
        return ResponseEntity.ok(mongoDBClient.delete(mongoCollection,querydocument));

    }
}
