package model.dto.contents;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import model.dto.review.Review;

public class Contents {
    private int contentId;
    private ContentType contentType;
    private String contentImg;
    private List<Review> reviews; // 추후 로 수정
    private String title;
    private String genre;
    private Date publishDate;
    
    public int getContentId() {
        return contentId;
    }
    public void setContentId(int contentId) {
        this.contentId = contentId;
    }
    public Enum<?> getContentType() {
        return contentType;
    }
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
    public String getContentImg() {
        return contentImg;
    }
    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }
    public List<Review> getReviews() {
        return reviews;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    public Date getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public enum ContentType {
        Movie,
        Music,
        Book,
    }
    
    public Contents() {}
    
    public Contents(int contentId, ContentType contentType, String contentImg, List<Review> reviews, String title, String genre, Date publishDate) {
        super();
        this.contentId = contentId;
        this.contentType = contentType;
        this.contentImg = contentImg;
        this.reviews = reviews;
        this.title = title;
        this.genre = genre;
        this.publishDate = publishDate;
    }
    
    @Override
    public String toString() {
        return "Contents [contentId=" + contentId + ", contentType=" + contentType + ", contentImg=" + contentImg
                + ", reviews=" + reviews + ", publishDate=" + publishDate + ", title=" + title + ", genre=" + genre
                + "]";
    }
}


