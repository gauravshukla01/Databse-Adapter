package org.cts.oneframework.dbadapter.tests;

import java.sql.SQLException;

import org.bson.Document;
import org.cts.oneframework.configprovider.ConfigProvider;
import org.cts.oneframework.dbadapter.mongo.DatabaseProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mongodb.client.FindIterable;

public class TestConnectionMongo {

	DatabaseProvider databaseProvider;

	@BeforeMethod
	public void init() {
		databaseProvider = new DatabaseProvider();
	}

	@Test
	public void TestConnection_Test() throws SQLException {
		FindIterable<Document> documents = DatabaseProvider.executeQuery(ConfigProvider.getAsString("host"),ConfigProvider.getAsInt("port"),ConfigProvider.getAsString("dbName"),ConfigProvider.getAsString("collectionName"));
		for( Document document : documents) {
			System.out.println(document.toString());
		}
	}

	@AfterMethod
	public void tearDown() throws SQLException {
		databaseProvider.close();
	}

}
