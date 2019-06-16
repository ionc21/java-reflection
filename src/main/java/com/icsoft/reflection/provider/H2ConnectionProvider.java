package com.icsoft.reflection.provider;

import com.icsoft.reflection.anotation.Provides;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2ConnectionProvider {

    @Provides
    public Connection buildConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:h2:C:\\WORK\\java-reflection\\db-files",
                "sa", "");
        return connection;
    }
}
