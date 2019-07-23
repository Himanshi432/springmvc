package com.rest.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.rest.clients.MongoDBClient;
import com.rest.dao.Address;
import com.rest.utils.TPCConstants;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AddressManager {

    @Autowired
    MongoDBClient mongoDBClient;

    @Autowired
    ObjectMapper objectMapper;
    public Address getAddressDetails(String address_id){
        MongoCollection<Document> mongoCollection = mongoDBClient
                .buildMongoCollection(TPCConstants.DB_STRING,TPCConstants.MONGO_ADDRESS_COLLECTION,TPCConstants.DB_NAME);
        BasicDBObject getQuery = new BasicDBObject();
        getQuery.put("address_id",address_id);
        MongoCursor<Document> mongoCursor = mongoDBClient.find(mongoCollection,getQuery);
        System.out.println("Addres_id=" + address_id);
        Document result = mongoCursor.next();
        Address foundaddress = null;
        try {
            foundaddress = objectMapper.readValue(result.toJson(), Address.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foundaddress;
    }
}
