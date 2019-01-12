package com.basgeekball.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DetectorPostgreSQL {
  public static void main(String[] args) throws InterruptedException {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("#==> ⚠ Can not find JDBC driver for PostgreSQL.");
      e.printStackTrace();
      System.exit(1);
    }
    String url = "jdbc:postgresql://postgres:5432/sonar";
    String user = "sonar";
    String password = "sonar";
    int retries = 120;
    long interval = 500;
    Connection connection;
    for (int i = 0; i < retries; i++) {
      try {
        connection = DriverManager.getConnection(url, user, password);
        if (connection != null) {
          System.out.println("#==> ⚡ DB connection is successful.");
          return;
        }
      } catch (SQLException e) {
        System.out.println("#==> ⚠ Can not establish a connection to the DB.");
      }
      Thread.sleep(interval);
    }
    System.out.println("#==> ⚠ Failed to connect to the DB.  Quit.");
    System.exit(1);
  }
}
