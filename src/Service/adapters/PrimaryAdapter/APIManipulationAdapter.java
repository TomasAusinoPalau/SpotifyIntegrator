package Service.adapters.PrimaryAdapter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Service.adapters.SecondaryAdapter.JSONManipulationAdapter;
import Service.ports.IPrimaryHandler;

public class APIManipulationAdapter implements IPrimaryHandler {
    /**
     *
     */
    
    private static final String MYPASS = "mypass123";
    private static final String FAKE_SPOTIFY = "fakeSpotify";
    String url = "http://192.168.0.117:8081/";
    String dbName = FAKE_SPOTIFY;

    String user = "root";
    String password = MYPASS;
    
    @Override
    public Statement stablishConnection() throws Exception {
        try {
            Connection conexion;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection((url + dbName), user, password);
            Statement statement = conexion.createStatement();
            return statement;
        } catch(Exception e) {
            e.fillInStackTrace();
            return null;
        } 
    }
    
    
    @Override
    public String getUser(String request) {
        // TODO Auto-generated method stub
        
        return null;
    }

    @Override
    public String getPlaylist(String request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getSong(String request) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
