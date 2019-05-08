package org.cts.oneframework.dbadapter.tests;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.cts.oneframework.annotation.ExcelDetails;
import org.cts.oneframework.dbadapter.oracle.DatabaseProvider;
import org.cts.oneframework.excelreader.ExcelDataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@ExcelDetails(excelName = "test")
public class TestConnection_Excel {

	DatabaseProvider databaseProvider;

	@BeforeMethod
	public void init() {
		databaseProvider = new DatabaseProvider();
	}

	@Test(dataProvider = "data")
	public void TestConnection_Test(Map<String, String> input) throws SQLException {
		ResultSet resultSet = databaseProvider.executeQuery(input.get("query"));
		while (resultSet.next()) {
			System.out.println(resultSet.getString(1));
		}
	}

	@DataProvider(name = "data")
	public Object[][] readExcelData(Method method) {
		return new ExcelDataProvider(getClass()).data(method);
	}

	@AfterMethod
	public void tearDown() throws SQLException {
		databaseProvider.close();
	}
}
