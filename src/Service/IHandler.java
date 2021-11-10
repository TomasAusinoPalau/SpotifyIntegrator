package Service;
import CreationalClass.*;
public interface IHandler {
    public static void createUser(User user) {};
    public static void createPlaylist(String title, Playlist playlist){};
    public static void addSongToPlaylist(String title, Song song){};
}
