package Service.adapters.SecondaryAdapter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Model.Playlist;
import Model.Song;
import Model.users.User;
import Model.users.Playable.*;
import Service.ports.ISecondaryHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class JSONManipulationAdapter implements ISecondaryHandler {

    private AtomicInteger userCounter = new AtomicInteger();
    private AtomicInteger playlistCounter = new AtomicInteger();

    private ArrayList<Playlist> instanciedPlaylist = new ArrayList<Playlist>();
    private ArrayList<User> instanciedUsers = new ArrayList<User>();

    private JSONObject mainObject = new JSONObject();
    private JSONArray arrayUsers = new JSONArray();
    private JSONArray arrayPlaylists = new JSONArray();

    public JSONManipulationAdapter() {
        this.readJSON();
    }

    public void createUser(User user) throws IOException {
        JSONObject objeto = new JSONObject();
        objeto.put("id", userCounter.getAndIncrement());
        objeto.put("username", user.getUsername());
        objeto.put("password", user.getPassword());
        if (user.getUserType().getClass().getName().equals("Basic")) {
            objeto.put("type", "basic");
        } else {
            objeto.put("type", "premium");
        }

        objeto.put("playlists", new JSONArray());
        arrayUsers.add(objeto);
        writeJSON(mainObject);
    }

    public ArrayList<User> getInstanciedUsers() {
        return instanciedUsers;
    }

    public void createPlaylist(String title, Playlist playlist) {
        ArrayList<Song> songs = playlist.getSongs();
        JSONObject objectPlaylist = new JSONObject();
        JSONArray arraySongs = new JSONArray();
        objectPlaylist.put("id", playlistCounter.getAndIncrement());
        objectPlaylist.put("playlistTitle", title);

        for (Song song : songs) {
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

    public void addSongToPlaylist(String title, Song song) {
        for (Object playlistObject : arrayPlaylists) {
            int playlistIndex = arrayPlaylists.indexOf(playlistObject);
            JSONObject playlistJSON = (JSONObject) playlistObject;
            if (playlistJSON.containsValue(title)) {
                JSONArray songs = (JSONArray) playlistJSON.get("songs");
                songs.add(song);
                playlistJSON.replace("songs", songs);
                arrayPlaylists.set(playlistIndex, playlistJSON);
            }
        }
        writeJSON(mainObject);
    }

    private void writeJSON(JSONObject object) {
        object.clear();
        object.put("Users", arrayUsers);
        object.put("Playlists", arrayPlaylists);

        try (FileWriter file = new FileWriter("./database.json", false)) {
            file.write(object.toJSONString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void readJSON() {
        JSONParser parser = new JSONParser();

        try (FileReader file = new FileReader("./database.json")) {
            Object obj = parser.parse(file);
            JSONObject json = (JSONObject) obj;
            arrayPlaylists = (JSONArray) json.get("Playlists");
            arrayUsers = (JSONArray) json.get("Users");
            
            for (Object object : arrayPlaylists) {
                JSONObject playlist = (JSONObject) object;
                JSONArray actualSongs = (JSONArray) playlist.get("songs");
                ArrayList<Song> instanciedSongs = new ArrayList<>();

                for (Object objCancion : actualSongs) {
                    JSONObject cancionObj = (JSONObject) objCancion;
                    Object duration = cancionObj.get("duration");
                    Double durationValue = (Double) duration;
                    instanciedSongs.add(new Song((String) cancionObj.get("title"), (String) cancionObj.get("album"),
                            (String) cancionObj.get("artist"), durationValue));
                }

                Playlist actual = new Playlist((String) playlist.get("playlistTitle"), actualSongs, "admin");
                instanciedPlaylist.add(actual);
            }

            for (Object object : arrayUsers) {
                JSONObject user = (JSONObject) object;
                String userType = (String) user.get("type");
                if (userType.equals("basic")) {
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

    public JSONArray getArrayPlaylists() {
        return arrayPlaylists;
    }

    public JSONArray getArrayUsers() {
        return arrayUsers;
    }

    public static void main(String[] args) throws java.io.IOException {
        JSONManipulationAdapter mani = new JSONManipulationAdapter();
        Song newHit = new Song("La cancion", "album", "Bad Bunny", 4);
        Song newHit2 = new Song("The bells", "album", "Jeff Mills", 6);
        User usuario = new User("claudio", "mypasswordglock", new Basic());
        ArrayList<Song> canciones = new ArrayList();
        canciones.add(newHit);
        canciones.add(newHit2);
        Playlist play = usuario.createPlaylist("Mis favoritas", canciones, usuario.getUsername());
        mani.createPlaylist(play.getPlaylistTitle(), play);
        mani.createUser(usuario);
        mani.readJSON();
    }
}