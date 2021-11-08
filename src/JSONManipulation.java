import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class JSONManipulation {
    private static AtomicInteger userCounter = new AtomicInteger();
    private static AtomicInteger playlistCounter = new AtomicInteger();
    
    JSONManipulation(){}


    private static JSONArray arrayUsers;
    private static JSONArray arrayPlaylists;

    // createPremium createBasic
    public static void createBasic(User user) throws IOException {
        JSONObject objetoBasic = new JSONObject();
        objetoBasic.put("id", userCounter.getAndIncrement());
        objetoBasic.put("username", user.getUsername());
        objetoBasic.put("password", user.getPassword());
        objetoBasic.put("type", "basic");

        objetoBasic.put("playlists", new JSONArray());
        arrayUsers.add(objetoBasic);
        
    }

    public static void createPremium(User user) throws IOException {
        JSONObject objetoPremium = new JSONObject();
        objetoPremium.put("username", user.getUsername());
        objetoPremium.put("password", user.getPassword());
        objetoPremium.put("type", "premium");

        objetoPremium.put("playlists", new JSONArray());
        arrayUsers.add(objetoPremium);
    }

    public static void createPlaylist(String title, Playlist playlist){
        ArrayList<Song> songs = playlist.getCanciones();
        JSONObject objectPlaylist = new JSONObject();
        JSONArray arraySongs = new JSONArray();
        objectPlaylist.put("playlist", title);

        for(Song song : songs) {
            JSONObject objetoSong = new JSONObject();

            objetoSong.put("title", song.getTitle());
            objetoSong.put("duration", song.getDuration());
            objetoSong.put("artist", song.getArtist());
            objetoSong.put("album", song.getAlbum());

            arraySongs.add(objetoSong);
        }
        objectPlaylist.put("playlist"+ playlistCounter.getAndIncrement(), arraySongs);   

        arrayPlaylists.add(objectPlaylist);  
    }

    public static void addSongToPlaylist(String title, Song song){
        for(Object playlistObject : arrayPlaylists) {
            JSONObject playlistJSON = (JSONObject) playlistObject;
            int playlistIndex = arrayPlaylists.indexOf(playlistObject);
            if(playlistJSON.containsValue(title)) {
                playlistJSON.get("playlist");
            }
            
        }
    }
    

    private static void writeJSON(JSONObject object) {
        object.put("Users", arrayUsers);
        object.put("Playlist", arrayPlaylists);
        try(FileWriter file = new FileWriter("./database.json", true)) {
            file.write(object.toJSONString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}


