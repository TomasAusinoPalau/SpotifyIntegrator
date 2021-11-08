import java.util.ArrayList;

public class Playlist {
    private String playlistTitle;
    private ArrayList<Song> canciones;

    Playlist(String title, Song[] canciones) {
        this.playlistTitle = title;
        for(Song cancion : canciones) {
            this.canciones.add(cancion);
        }
    }

    Playlist(String title, ArrayList<Song> canciones) {
        this.playlistTitle = title;
        this.canciones = canciones;
    }
    
    public void addSong(Song cancion) {
        this.canciones.add(cancion);
    }

    public ArrayList<Song> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Song> canciones) {
        this.canciones = canciones;
    }

    public String getPlaylistTitle() {
        return playlistTitle;
    }

    public void setPlaylistTitle(String playlistTitle) {
        this.playlistTitle = playlistTitle;
    }

    public Song selectRandomSong() {
        return this.getCanciones().get((int) Math.random() * this.getCanciones().size());
    }
   
}
