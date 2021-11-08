package CreationalClass;

public class Song {
    private String title, album, artist;
    private float duration; 

    public Song(String title, String album, String artista, float duracion) {
        this.title = title;
        this.album = album;
        this.artist = artista;
        this.duration = duracion;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public float getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

}
