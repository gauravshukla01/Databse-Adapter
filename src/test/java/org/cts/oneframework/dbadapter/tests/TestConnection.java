package org.cts.oneframework.dbadapter.tests;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.cts.oneframework.dbadapter.oracle.DatabaseProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestConnection {

	DatabaseProvider databaseProvider;

	@BeforeMethod
	public void init() {
		databaseProvider = new DatabaseProvider();
	}

	@Test
	public void TestConnection_Test() throws SQLException {
		ResultSet resultSet = databaseProvider.executeQuery("select * from ");
		while (resultSet.next()) {
			System.out.println(resultSet.getString(1));
		}
	}

	@AfterMethod
	public void tearDown() throws SQLException {
		databaseProvider.close();
	}

}
