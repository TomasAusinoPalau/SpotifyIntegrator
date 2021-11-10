package CreationalClass;
import java.util.ArrayList;

import Playable.IPlayable;

public class User {
    private String username;
    private String password;
    private ArrayList<Playlist> playlistContainer;
    private IPlayable userType;

    public User(String username, String password, IPlayable tipoUsuario) {
        this.username = username;
        this.password = password;
        this.userType = tipoUsuario;
        this.playlistContainer = new ArrayList<>();
    }

    public IPlayable getUserType() {
        return userType;
    }

    public void setUserType(IPlayable tipoUsuario) {
        this.userType = tipoUsuario;
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

    public void setPlaylist(Playlist playlist) {
        this.playlistContainer.add(playlist);
    }

    public void playSong(Song song) {
        this.userType.playSong(song);
    }

    public Playlist createPlaylist(String title, ArrayList<Song> songs, String creator) {
        return this.getUserType().createPlaylist(title, songs, creator);
    }

}
