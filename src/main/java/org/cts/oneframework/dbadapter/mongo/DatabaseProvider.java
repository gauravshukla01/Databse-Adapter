package org.cts.oneframework.dbadapter.mongo;

import java.sql.SQLException;
import org.bson.Document;
import org.cts.oneframework.configprovider.ConfigProvider;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseProvider {
	private static MongoClient mongoClient;
	private static MongoDatabase database;
	private static MongoCollection<Document> collection;
	
	public static FindIterable<Document> executeQuery(String host,int port, String dbName, String collectionName) throws SQLException {
		System.out.println(host+port+dbName+collectionName);
		return getCollection(host, port, dbName, collectionName).find();
		//return getStatement().executeQuery(query);
	}

	public Boolean execute(String query) throws SQLException {
		return null;
		//return getStatement().execute(query);
	}

	public Integer executeUpdate(String query) throws SQLException {
		return null;
		//return getStatement().executeUpdate(query);
	}

	private static MongoCollection<Document> getCollection(String host,int port, String dbName, String collectionName) throws SQLException {

		if (database == null) {
			System.out.println(host+port+dbName+collectionName);
			mongoClient = new MongoClient(host,port);
			database = mongoClient.getDatabase(dbName);
			collection = database.getCollection(collectionName);
			return collection;
		}
		return collection;

	}

	public void close() throws SQLException {
		if (mongoClient != null) {
			mongoClient.close();
		}
	}

	
}
