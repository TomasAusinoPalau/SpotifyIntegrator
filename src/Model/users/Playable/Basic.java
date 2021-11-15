package Model.users.Playable;
import java.util.ArrayList;

import Model.Playlist;
import Model.Song;

public class Basic implements IPlayable {
    
    public Basic() {
        // super.setPlaylistContainer(new ArrayList<>());
    }

    @Override
    public void shufflePlay(Playlist playlist) {
        Song song = playlist.selectRandomSong();
        // TODO Auto-generated method stub
        System.out.println("Reproduciendo " + song.getTitle() + " de " + song.getArtist());
    }
    
    @Override
    public void playSong(Song song) {
        // TODO Auto-generated method stub
        System.out.println("Contrata a premium para elegir una canción");
    }

    @Override
    public void addSong(Song song, Playlist playlist) {
        // TODO Auto-generated method stub
        if(playlist.getSongs().size() <= 30) {
            playlist.addSong(song);
        } else System.out.println("Contrata premium para poder crear playlist con más de 30 canciones");

    }
        
    @Override
    public Playlist createPlaylist(String title, Song[] canciones, String creator) {
        // TODO Auto-generated method stub
        if(canciones.length <= 30) {
            //super.reatePlaylist(title, canciones);
            return new Playlist(title, canciones, creator);
        } else {
            System.out.println("Contrata premium para poder crear playlist con más de 30 canciones");
            return null;
        }
    }

    public Playlist createPlaylist(String title, ArrayList<Song> canciones, String creator) {
        // TODO Auto-generated method stub
        if(canciones.size() <= 30) {
            return new Playlist(title, canciones, creator);
        } else {
            System.out.println("Contrata premium para poder crear playlist con más de 30 canciones");
            return null;
        }
    }
}
