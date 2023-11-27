package model.dto.contents;

public class Book {
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
    
    public Book(String writer, String summary) {
        super();
        this.writer = writer;
        this.summary = summary;
    }
    
    @Override
    public String toString() {
        return "Book [writer=" + writer + ", summary=" + summary + "]";
    }

    
}


