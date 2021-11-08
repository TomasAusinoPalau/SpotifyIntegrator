import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Playlist> playlistContainer;
    private IPlayable tipoUsuario;

    User(String username, String password, IPlayable tipoUsuario) {
        this.username = username;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
        this.playlistContainer = new ArrayList<>();
    }

    public IPlayable getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(IPlayable tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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

    public void playSong(Song song) {
        this.tipoUsuario.playSong(song);
    }

    


}
