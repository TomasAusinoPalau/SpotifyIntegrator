package Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import CreationalClass.Playlist;
import CreationalClass.Song;
import CreationalClass.User;
import Playable.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class JSONManipulation implements IHandler {
    private static AtomicInteger userCounter = new AtomicInteger();
    private static AtomicInteger playlistCounter = new AtomicInteger();

    private static ArrayList<Playlist> instanciedPlaylist = new ArrayList<Playlist>();
    private static ArrayList<User> instanciedUsers = new ArrayList<User>();
    
    
    private static JSONObject mainObject = new JSONObject();
    private static JSONArray arrayUsers = new JSONArray();
    private static JSONArray arrayPlaylists = new JSONArray();
    
    public JSONManipulation(){
        this.readJSON();
    }

    public static void createUser(User user) throws IOException {
        JSONObject objeto = new JSONObject();
        objeto.put("id", userCounter.getAndIncrement());
        objeto.put("username", user.getUsername());
        objeto.put("password", user.getPassword());
        if(user.getUserType().getClass().getName().equals("Basic")) {
            objeto.put("type", "basic");
        } else {
            objeto.put("type", "premium");
        }

        objeto.put("playlists", new JSONArray());
        arrayUsers.add(objeto);
        writeJSON(mainObject);
    }

    public static ArrayList<User> getInstanciedUsers() {
        return instanciedUsers;
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

    public static void readJSON() {
        JSONParser parser = new JSONParser();

        try(FileReader file = new FileReader("./database.json")) {
            Object obj = parser.parse(file);
            JSONObject json = (JSONObject) obj;
            arrayPlaylists = (JSONArray) json.get("Playlists");
            arrayUsers = (JSONArray) json.get("Users");

            for(Object object : arrayPlaylists) {
                JSONObject playlist = (JSONObject) object;
                Playlist actual = new Playlist((String) playlist.get("title"),(ArrayList<Song>)  playlist.get("songs"),(String) playlist.get("creator"));
                instanciedPlaylist.add(actual);
            }

            for(Object object : arrayUsers) {
                JSONObject user = (JSONObject) object;
                String userType = (String) user.get("type");
                if(userType.equals("basic")) {
                    User actual = new User((String) user.get("username"), (String) user.get("password"), new Basic());
                    instanciedUsers.add(actual);
                } else {
                    User actual = new User((String) user.get("username"), (String) user.get("password"), new Premium());
                    instanciedUsers.add(actual);
                }
            }        
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
    //     JSONManipulation.readJSON();
    // }
}