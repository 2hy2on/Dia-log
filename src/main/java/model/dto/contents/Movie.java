package model.dto.contents;

public class Movie {
    private String actors;
    private String summary;
    private int audienceNum;
    private String director;
    
    public String getActors() {
        return actors;
    }
    public void setActors(String actors) {
        this.actors = actors;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public int getAudienceNum() {
        return audienceNum;
    }
    public void setAudienceNum(int audienceNum) {
        this.audienceNum = audienceNum;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    
    public Movie() { }
    
    public Movie(String actors, String summary, int audienceNum, String director) {
        super();
        this.actors = actors;
        this.summary = summary;
        this.audienceNum = audienceNum;
        this.director = director;
    }

    @Override
    public String toString() {
        return "Moive [actors=" + actors + ", summary=" + summary + ", audienceNum=" + audienceNum + ", director="
                + director + "]";
    }
}
