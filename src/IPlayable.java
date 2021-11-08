public interface IPlayable {
    public void shufflePlay(Playlist playlist);
    public void playSong(Song song);
    public void addSong(Song song, Playlist playlist);
    public Playlist createPlaylist(String title, Song[] canciones);

}
