package CreationalClass;
import java.util.ArrayList;

public class Playlist {
    private String playlistTitle;
    private ArrayList<Song> songs;
    private String userCreator;

    public Playlist(String title, Song[] canciones, String creator) {
        this.playlistTitle = title;
        this.userCreator = creator;
        for(Song cancion : canciones) {
            this.songs.add(cancion);
        }
    }

    public Playlist(String title, ArrayList<Song> songs, String creator) {
        this.playlistTitle = title;
        this.songs = songs;
        this.userCreator = creator;
    }
    
    public void addSong(Song cancion) {
        this.songs.add(cancion);
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> canciones) {
        this.songs = canciones;
    }

    public String getPlaylistTitle() {
        return playlistTitle;
    }

    public void setPlaylistTitle(String playlistTitle) {
        this.playlistTitle = playlistTitle;
    }

    public String getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(String userCreator) {
        this.userCreator = userCreator;
    }

    public Song selectRandomSong() {
        return this.getSongs().get((int) Math.random() * this.getSongs().size());
    }
   
}
