import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import CreationalClass.Playlist;
import CreationalClass.Song;
import CreationalClass.User;
import Playable.Basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class JSONManipulation implements IHandler {
    private static AtomicInteger userCounter = new AtomicInteger();
    private static AtomicInteger playlistCounter = new AtomicInteger();
    
    
    private static JSONObject mainObject = new JSONObject();
    private static JSONArray arrayUsers = new JSONArray();
    private static JSONArray arrayPlaylists = new JSONArray();
    
    JSONManipulation(){
        this.readJSON(mainObject);
    }
    public static void createBasic(User user) throws IOException {
        JSONObject objetoBasic = new JSONObject();
        objetoBasic.put("id", userCounter.getAndIncrement());
        objetoBasic.put("username", user.getUsername());
        objetoBasic.put("password", user.getPassword());
        objetoBasic.put("type", "basic");

        objetoBasic.put("playlists", new JSONArray());
        arrayUsers.add(objetoBasic);
        writeJSON(mainObject);
    }

    public static void createPremium(User user) throws IOException {
        JSONObject objetoPremium = new JSONObject();
        objetoPremium.put("id", userCounter.getAndIncrement());
        objetoPremium.put("username", user.getUsername());
        objetoPremium.put("password", user.getPassword());
        objetoPremium.put("type", "premium");

        objetoPremium.put("playlists", new JSONArray());
        arrayUsers.add(objetoPremium);
        writeJSON(mainObject);
    }

    public static void createPlaylist(String title, Playlist playlist){
        ArrayList<Song> songs = playlist.getSongs();
        JSONObject objectPlaylist = new JSONObject();
        JSONArray arraySongs = new JSONArray();
        objectPlaylist.put("id", playlistCounter.getAndIncrement());
        objectPlaylist.put("playlistTitle", title);

        for(Song song : songs) {
            JSONObject objetoSong = new JSONObject();

            objetoSong.put("title", song.getTitle());
            objetoSong.put("duration", song.getDuration());
            objetoSong.put("artist", song.getArtist());
            objetoSong.put("album", song.getAlbum());

            arraySongs.add(objetoSong);
        }
        objectPlaylist.put("songs", arraySongs);   
        arrayPlaylists.add(objectPlaylist);  
        writeJSON(mainObject);
    }

    public static void addSongToPlaylist(String title, Song song){

        for(Object playlistObject : arrayPlaylists) {
            int playlistIndex = arrayPlaylists.indexOf(playlistObject);
            JSONObject playlistJSON = (JSONObject) playlistObject;
            if(playlistJSON.containsValue(title)) {
                JSONArray songs = (JSONArray) playlistJSON.get("songs");
                songs.add(song);
                playlistJSON.replace("songs", songs);
                arrayPlaylists.set(playlistIndex, playlistJSON);
            }
        }
        writeJSON(mainObject);
    }
    

    private static void writeJSON(JSONObject object) {
            object.clear();
            object.put("Users", arrayUsers);
            object.put("Playlist", arrayPlaylists);


        try(FileWriter file = new FileWriter("./database.json", false)) {
            file.write(object.toJSONString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private static void readJSON(JSONObject object) {
        try(FileReader file = new FileReader("./database.json")) {
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JSONArray getArrayPlaylists() {
        return arrayPlaylists;
    }

    public static JSONArray getArrayUsers() {
        return arrayUsers;
    }

    // public static void main(String[] args) throws java.io.IOException {
        
    //     User user = new User("Claudio", "password", new Basic());
    //     JSONManipulation.createBasic(user);
    //     JSONManipulation.createBasic(user);
    //     JSONManipulation.createBasic(user);
    //     System.out.println(JSONManipulation.getArrayUsers());
    // }
}


