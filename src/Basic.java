import java.util.ArrayList;

public class Basic implements IPlayable {
    
    Basic() {
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
        if(playlist.getCanciones().size() <= 30) {
            playlist.addSong(song);
        } else System.out.println("Contrata premium para poder crear playlist con más de 30 canciones");

    }
        
    @Override
    public Playlist createPlaylist(String title, Song[] canciones) {
        // TODO Auto-generated method stub
        if(canciones.length <= 30) {
            //super.createPlaylist(title, canciones);
            return new Playlist(title, canciones);
        } else {
            System.out.println("Contrata premium para poder crear playlist con más de 30 canciones");
            return null;
        }
    }

    public Playlist createPlaylist(String title, ArrayList<Song> canciones) {
        // TODO Auto-generated method stub
        if(canciones.size() <= 30) {
            return new Playlist(title, canciones);
        } else {
            System.out.println("Contrata premium para poder crear playlist con más de 30 canciones");
            return null;
        }
    }
}
