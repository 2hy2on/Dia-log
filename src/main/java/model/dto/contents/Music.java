package model.dto.contents;

public class Music {
    private String Singer;
    private String Album;
    
    public String getSinger() {
        return Singer;
    }
    public void setSinger(String singer) {
        Singer = singer;
    }
    public String getAlbum() {
        return Album;
    }
    public void setAlbum(String album) {
        Album = album;
    }
    
    public Music() { }
    
    public Music(String singer, String album) {
        super();
        Singer = singer;
        Album = album;
    }
    
    @Override
    public String toString() {
        return "Music [Singer=" + Singer + ", Album=" + Album + "]";
    }
    
}
