package com.common.util;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnection {
	public static Connection getConnection() throws Exception {
		Connection conn = null;
		Context initContext = new InitialContext();
		Context envContent = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContent.lookup("jdbc/Top");
		conn = ds.getConnection();
		return conn;
	}

}
