package model.dto.contents;

import java.time.LocalDate;

public class Contents {
    private int contentId;
    private Enum<?> contentType;
    private String contentImg;
    private String reviews; // 추후 List<Review>로 수정
    private LocalDate publishDate;
    private String title;
    private String genre;
    
    public int getContentId() {
        return contentId;
    }
    public void setContentId(int contentId) {
        this.contentId = contentId;
    }
    public Enum<?> getContentType() {
        return contentType;
    }
    public void setContentType(Enum<?> contentType) {
        this.contentType = contentType;
    }
    public String getContentImg() {
        return contentImg;
    }
    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }
    public String getReviews() {
        return reviews;
    }
    public void setReviews(String reviews) {
        this.reviews = reviews;
    }
    public LocalDate getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(LocalDate publishDate) {
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
    
    public Contents(int contentId, Enum<?> contentType, String contentImg, String reviews, LocalDate publishDate,
            String title, String genre) {
        super();
        this.contentId = contentId;
        this.contentType = contentType;
        this.contentImg = contentImg;
        this.reviews = reviews;
        this.publishDate = publishDate;
        this.title = title;
        this.genre = genre;
    }
    
    @Override
    public String toString() {
        return "Contents [contentId=" + contentId + ", contentType=" + contentType + ", contentImg=" + contentImg
                + ", reviews=" + reviews + ", publishDate=" + publishDate + ", title=" + title + ", genre=" + genre
                + "]";
    }
}


