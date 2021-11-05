public class Cancion {
    private String name, album, artist;
    private float duration; 

    Cancion(String nombre, String album, String artista, float duracion) {
        this.name = nombre;
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

    public String getName() {
        return name;
    }

}
