package com.example.aplicaiontpv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLServer {
    private final String ip = "sql521.sql.dinaserver.com";
    private final int port = 1433;
    private final String BBDD = "Reto1G2";
    private final String user = "AdminGR2";
    private final String passwd = "Reto01Ayuda-";
    private final String cadena = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databaseName=" + BBDD + ";user=" + user + ";password=" + passwd;
    private final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public Connection con;

    public ConexionSQLServer() {
    }

    public void Conectar() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(cadena);
            con = DriverManager.getConnection(cadena);
            con = DriverManager.getConnection(cadena);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void Cerrar() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
