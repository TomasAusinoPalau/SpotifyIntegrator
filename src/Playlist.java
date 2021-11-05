import java.util.ArrayList;

public class Playlist {
    private String playlistTitle;
    private ArrayList<Cancion> canciones;

    Playlist(String title, Cancion[] canciones) {
        this.playlistTitle = title;
        for(Cancion cancion : canciones) {
            this.canciones.add(cancion);
        }

    }
    
    public void addSong(Cancion cancion) {
        this.canciones.add(cancion);
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;
    }

    public String getPlaylistTitle() {
        return playlistTitle;
    }

    public void setPlaylistTitle(String playlistTitle) {
        this.playlistTitle = playlistTitle;
    }
   
}
