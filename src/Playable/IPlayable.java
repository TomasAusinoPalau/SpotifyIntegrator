package Playable;

import CreationalClass.Playlist;
import CreationalClass.Song;
import java.util.ArrayList;

public interface IPlayable {
    public void shufflePlay(Playlist playlist);
    public void playSong(Song song);
    public void addSong(Song song, Playlist playlist);
    public Playlist createPlaylist(String title, Song[] canciones, String creator);
    public Playlist createPlaylist(String title, ArrayList<Song> canciones, String creator);

}
