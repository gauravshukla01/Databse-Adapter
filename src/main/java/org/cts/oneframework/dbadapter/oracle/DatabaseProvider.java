package org.cts.oneframework.dbadapter.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.cts.oneframework.configprovider.ConfigProvider;

public class DatabaseProvider {

	private Statement statement;
	private Connection connection;

	public ResultSet executeQuery(String query) throws SQLException {
		return getStatement().executeQuery(query);
	}

	public Boolean execute(String query) throws SQLException {
		return getStatement().execute(query);
	}

	public Integer executeUpdate(String query) throws SQLException {
		return getStatement().executeUpdate(query);
	}

	private Statement getStatement() throws SQLException {
		if (statement == null) {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@" + ConfigProvider.getAsString("db.url") + ":" + ConfigProvider.getAsString("db.portNumber") + ":" + ConfigProvider.getAsString("db.servicePid"), ConfigProvider.getAsString("db.userName"), ConfigProvider.getAsString("db.password"));
			statement = connection.createStatement();
			return statement;
		}
		return statement;

	}

	public void close() throws SQLException {
		if (statement != null && connection != null) {
			statement.close();
			connection.close();
		}
	}
}
