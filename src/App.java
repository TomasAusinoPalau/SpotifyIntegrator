import CreationalClass.*;
import Playable.Basic;
import Playable.IPlayable;
import Playable.Premium;

public class App {
    public static void main(String[] args) throws Exception {
        Song newHit = new Song("cancion", "album", "artista", 4);
        IPlayable tipoBasico = new Basic();
        IPlayable tipoPremium = new Premium();
        User usuario = new User("username", "password", tipoBasico);
        usuario.playSong(newHit);
        usuario.setTipoUsuario(tipoPremium);
        usuario.playSong(newHit);
    }
}
