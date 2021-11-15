package Service.ports;
import java.sql.Statement;
import java.io.IOException;

import Model.users.*;
public interface IPrimaryHandler {
    public Statement stablishConnection() throws Exception;

    public String getUser(String request);
    public String getPlaylist(String request);
    public String getSong(String request);
}
