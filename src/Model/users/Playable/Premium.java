package Model.users.Playable;

import java.util.ArrayList;

import Model.Playlist;
import Model.Song;

public class Premium implements IPlayable {

    public Premium() {

    }

    @Override
    public void shufflePlay(Playlist playlist) {
        // TODO Auto-generated method stub
        Song song = playlist.selectRandomSong();
        System.out.println("Reproduciendo " + song.getTitle() + " de " + song.getArtist());
        
    }

    @Override
    public void playSong(Song song) {
        // TODO Auto-generated method stub
        System.out.println("Reproduciendo " + song.getTitle() + " de " + song.getArtist());
    }

    @Override
    public void addSong(Song song, Playlist playlist) {
        // TODO Auto-generated method stub
        playlist.addSong(song);
    }

    @Override
    public Playlist createPlaylist(String title, Song[] canciones, String creator) {
        // TODO Auto-generated method stub
        return new Playlist(title, canciones, creator);
    }

    public Playlist createPlaylist(String title, ArrayList<Song> canciones, String creator) {
        // TODO Auto-generated method stub
            return new Playlist(title, canciones, creator);
    }
    
}
