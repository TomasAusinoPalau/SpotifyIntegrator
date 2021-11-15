package Model.users.Playable;

import java.util.ArrayList;

import Model.Playlist;
import Model.Song;

public interface IPlayable {
    public void shufflePlay(Playlist playlist);
    public void playSong(Song song);
    public void addSong(Song song, Playlist playlist);
    public Playlist createPlaylist(String title, Song[] canciones, String creator);
    public Playlist createPlaylist(String title, ArrayList<Song> canciones, String creator);

}
