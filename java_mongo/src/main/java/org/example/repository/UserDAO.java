package org.example.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.example.repository.entity.UserEntity;
import org.example.util.ConnectionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UserDAO {

    private final MongoCollection<Document> collection;

    public UserDAO(String collection){
        this.collection = ConnectionHandler.getDatabase().getCollection(collection);
    }

    // Convert User to Document
    private Document toDocument(UserEntity userEntity){
        Document doc = new Document();

        if(userEntity.getId() != null){
            doc.append("_id", userEntity.getId());
        }

        doc.append("username", userEntity.getUsername())
                .append("password", userEntity.getPassword())
                .append("createdAt", userEntity.getCreatedAt());

        if(userEntity.getRoles() != null){
            doc.append("roles", userEntity.getRoles());
        }

        return doc;
    }

    // Convert document to user
    private UserEntity fromDocument(Document doc){
        if(doc == null) return null;

        UserEntity userEntity = new UserEntity();
        userEntity.setId(doc.getObjectId("_id"));
        userEntity.setUsername(doc.getString("username"));
        userEntity.setPassword(doc.getString("password"));
        userEntity.setCreatedAt(doc.get("createdAt", Date.class));
        userEntity.setRoles(doc.getList("roles", String.class));

        return userEntity;
    }

    // CRUD Operations

    // Create
    public UserEntity insert(UserEntity userEntity){
        Document doc = toDocument(userEntity);
        collection.insertOne(doc);

        // Get the generated ID
        userEntity.setId(doc.getObjectId("_id"));
        return userEntity;
    }
    public List<UserEntity> insertMany(List<UserEntity> users) {
        List<Document> documents = users.stream()
                .map(this::toDocument)
                .collect(Collectors.toList());

        collection.insertMany(documents);

        // Update IDs
        for (int i = 0; i < documents.size(); i++) {
            users.get(i).setId(documents.get(i).getObjectId("_id"));
        }

        return users;
    }

    // Read
    public UserEntity findById(String id) {
        Document doc = collection.find(new Document("_id", new ObjectId(id))).first();
        return fromDocument(doc);
    }

    public UserEntity findByUsernameAndPassword(String username, String password){
        Document query = new Document("$and", List.of(
                new Document("username", username),
                new Document("password", password)
        ));
        Document doc = collection.find(query).first();
        return fromDocument(doc);
    }

    public List<UserEntity> findAll() {
        return StreamSupport.stream(collection.find().spliterator(), false)
                .map(this::fromDocument)
                .collect(Collectors.toList());
    }

    // Update
    public boolean update(UserEntity user) {
        Document query = new Document("_id", user.getId());
        Document update = new Document("$set", toDocument(user));

        UpdateResult result = collection.updateOne(query, update);
        return result.getModifiedCount() > 0;
    }

    // Add role to user (array operation)
    public boolean addRole(String userId, String role) {
        Document query = new Document("_id", new ObjectId(userId));
        Document update = new Document("$push",
                new Document("roles", role));

        UpdateResult result = collection.updateOne(query, update);
        return result.getModifiedCount() > 0;
    }

    // Delete
    public boolean delete(String userId) {
        Document query = new Document("_id", new ObjectId(userId));
        return collection.deleteOne(query).getDeletedCount() > 0;
    }

    // ========== ADVANCED QUERIES ==========
//    Key operators:
//
//    $or: Matches if ANY of the conditions are true
//
//    $regex: Pattern matching (like SQL LIKE)
//
//    $options: "i": Case-insensitive search

    public List<UserEntity> search(String keyword) {
        Document query = new Document("$or", List.of(
                new Document("username",
                        new Document("$regex", keyword).append("$options", "i")),
                new Document("password",
                        new Document("$regex", keyword).append("$options", "i"))
        ));

        return StreamSupport.stream(collection.find(query).spliterator(), false)
                .map(this::fromDocument)
                .collect(Collectors.toList());
    }

}
