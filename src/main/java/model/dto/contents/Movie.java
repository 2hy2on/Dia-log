package model.dto.contents;

import java.time.LocalDate;
import java.util.Date;

public class Movie extends Contents {
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

    public void setTitle(String title) {
        super.setTitle(title);
    }

    public void setPublishDate(Date publishDate) {
        super.setPublishDate(publishDate);
    }

    public void setGenre(String genre) {
        super.setGenre(genre);
    }

    public String getTitle() {
        return super.getTitle();
    }

    public Date getPublishDate() {
        return super.getPublishDate();
    }

    public String getGenre() {
        return super.getGenre();
    }

    public Movie() {
        // 기본 생성자
    }

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
