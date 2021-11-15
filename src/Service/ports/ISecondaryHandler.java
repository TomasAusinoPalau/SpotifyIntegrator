package Service.ports;
import java.io.IOException;

import Model.Playlist;
import Model.Song;
import Model.users.*;
public interface ISecondaryHandler {
    public  void createUser(User user) throws Exception;
    public  void createPlaylist(String title, Playlist playlist);
    public  void addSongToPlaylist(String title, Song song);
    
}
