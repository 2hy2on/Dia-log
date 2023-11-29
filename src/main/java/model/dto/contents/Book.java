package model.dto.contents;

import java.time.LocalDate;

public class Book extends Contents {
    private String writer;
    private String summary;

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setTitle(String title) {
        super.setTitle(title);
    }

    public void setPublishDate(LocalDate publishDate) {
        super.setPublishDate(publishDate);
    }

    public void setGenre(String genre) {
        super.setGenre(genre);
    }

    public String getTitle() {
        return super.getTitle();
    }

    public LocalDate getPublishDate() {
        return super.getPublishDate();
    }

    public String getGenre() {
        return super.getGenre();
    }

    public Book() {
    }

    public Book(String writer, String summary) {
        super();
        this.writer = writer;
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Book [title= " + getTitle() + ", writer=" + writer + ", summary=" + summary + "]";
    }
}
