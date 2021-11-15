package Model;

public class Song {
    private String title, album, artist;
    private double duration; 

    public Song(String title, String album, String artista, double duracion) {
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

    public double getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

}
