import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class JSONManipulation {
    private AtomicInteger userCounter = new AtomicInteger();
    private AtomicInteger playlistCounter = new AtomicInteger();
    JSONManipulation(){}


    private JSONArray arrayUsers;
    private JSONArray arrayPlaylists;

    // createPremium createBasic
    public void createBasic(BasicUser user) throws IOException {
        JSONObject objetoBasic = new JSONObject();
        objetoBasic.put("id", userCounter.getAndIncrement());
        objetoBasic.put("username", user.getUsername());
        objetoBasic.put("password", user.getPassword());
        objetoBasic.put("type", "basic");

        objetoBasic.put("playlists", new JSONArray());
        arrayUsers.add(objetoBasic);
        
    }

    public void createPremium(PremiumUser user) throws IOException {
        JSONObject objetoPremium = new JSONObject();
        objetoPremium.put("username", user.getUsername());
        objetoPremium.put("password", user.getPassword());
        objetoPremium.put("type", "premium");

        objetoPremium.put("playlists", new JSONArray());
        arrayUsers.add(objetoPremium);
    }

    public void createPlaylist(String title, Playlist playlist){
        ArrayList<Cancion> songs = playlist.getCanciones();
        JSONObject objectPlaylist = new JSONObject();
        JSONArray arrayJSON = new JSONArray();
        objectPlaylist.put("playlist", title);

        for(Cancion song : songs) {
            JSONObject objetoSong = new JSONObject();

            objetoSong.add("title", song.getTitle());
            objetoSong.add("duration", song.getDuration());
            objetoSong.add("artist", song.getArtist());
            objetoSong.add("album", song.getAlbum());
            arrayJSON.add(song);
        }
        objectPlaylist.put("playlist"+ playlistCounter.getAndIncrement(), arrayJSON);   

        arrayPlaylists.add(objectPlaylist);         
    }

    

    private static void writeJSON(JSONObject object) {
        try(FileWriter file = new FileWriter("./database.json", true)) {
            file.write(object.toJSONString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}


