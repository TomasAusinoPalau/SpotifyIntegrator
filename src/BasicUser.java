import java.util.ArrayList;

public class BasicUser extends User {
    
    BasicUser(String username, String password) {
        super(username, password);
        // super.setPlaylistContainer(new ArrayList<>());
    }

    @Override
    public void shufflePlay() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void playSong(Cancion song) {
        // TODO Auto-generated method stub
        System.out.println("Contrata a premium para elegir la canción");
    }
    @Override
    public void addSong(Cancion song) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void createPlaylist(String title, Cancion[] canciones) {
        // TODO Auto-generated method stub
        if(canciones.length <= 30) {
            super.createPlaylist(title, canciones);
        } else {
            System.out.println("Contrata premium para poder crear playlist con más de 30 canciones");
        }
    }


    


}
