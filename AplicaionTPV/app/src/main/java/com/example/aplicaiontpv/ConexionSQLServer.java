package com.example.aplicaiontpv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLServer {
    private final String ip = "sql518.sql.dinaserver.com";
    private final int port = 1433;
    private final String BBDD = "Reto1";
    private final String user = "AdminG2";
    private final String passwd = "Reto01Ayuda-";
    private final String cadena = "jdbc:sqlserver://" + ip + ":" + port + ";databaseName=" + BBDD + ";encrypt=false;trustServerCertificate=true";
    private final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    protected static Connection con;

    public void Conectar() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(cadena, user, passwd);
            if (con != null) {
                System.out.println("Conexión a BD " + ip + " con éxito");
            } else {
                System.out.println("Imposible conexión con " + ip);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Excepción de clase no encontrada " + ex);
        } catch (SQLException ex) {
            System.out.println("Excepción de SQL " + ex);
        }
    }

    public void Cerrar() {
        try {
            if (con != null) {
                if (!con.isClosed()) {
                    con.close();
                }
            }
        } catch (SQLException ex) {
            System.out.println("Excepción al cerrar la conexión " + ex);
        }
    }

}
