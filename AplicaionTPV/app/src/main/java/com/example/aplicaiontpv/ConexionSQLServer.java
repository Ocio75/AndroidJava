package com.example.aplicaiontpv;

import android.os.StrictMode;

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
    private final String driver = "net.sourceforge.jtds.jdbc.Driver";
    public Connection con;

    public ConexionSQLServer() {
    }
    public static Connection conexionBD(){
        Connection Conexion = null;
        try{
            final String ip = "sql521.sql.dinaserver.com";
            final int port = 1433;
            final String BBDD = "Reto1G2";
            final String user = "AdminGR2";
            final String passwd = "Reto01Ayuda-";
            final String cadena = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databaseName=" + BBDD + ";user=" + user + ";password=" + passwd;
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            Conexion = DriverManager.getConnection(cadena);
            Conexion = DriverManager.getConnection(cadena);
            Conexion = DriverManager.getConnection(cadena);
            Conexion = DriverManager.getConnection(cadena);
            Conexion = DriverManager.getConnection(cadena);
            Conexion = DriverManager.getConnection(cadena);

        }catch(Exception ex){

        }
        int boolen ;
        return Conexion;
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
