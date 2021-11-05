import java.util.ArrayList;

public abstract class User implements IPlayable {
    private String username;
    private String password;
    private ArrayList<Playlist> playlistContainer;

    User(String username, String password) {
        this.username = username;
        this.password = password;
        this.playlistContainer = new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public ArrayList<Playlist> getPlaylistContainer() {
        return playlistContainer;
    }

    public void setPlaylistContainer(Playlist playlist) {
        this.playlistContainer.add(playlist);
    }

    public void createPlaylist(String title, Cancion[] canciones) {
        Playlist playlist = new Playlist(title, canciones);
        this.setPlaylistContainer(playlist);
    }


}
