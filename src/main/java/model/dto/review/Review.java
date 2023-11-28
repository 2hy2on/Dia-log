package model.dto.review;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Review {
private String title;
	
	private String content;
	
	private Float rate;
	
	private LocalDateTime updatedAt;
	
	private LocalDateTime createdAt;

	private java.sql.Date watchedAt;

	public java.sql.Date getWatchedAt() {
		return watchedAt;
	}

	public void setWatchedAt(Date date) {
		this.watchedAt = (java.sql.Date) date;
	}

	private boolean isPrivate;
	
	private String mediaImg;
	
	private int contentId;
	 
	private int writerId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public String getMediaImg() {
		return mediaImg;
	}

	public void setMediaImg(String mediaImg) {
		this.mediaImg = mediaImg;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getWriterId() {
		return writerId;
	}

	public void setWriterId(int writerId) {
		this.writerId = writerId;
	}
	
	@Override
	public String toString() {
	    return "Review{" +
	            "title='" + title + '\'' +
	            ", content='" + content + '\'' +
	            ", rate=" + rate +
	            ", createdAt=" + createdAt +
	            ", updatedAt=" + updatedAt +
	            ", watchedAt=" + watchedAt +
	            ", isPrivate=" + isPrivate +
	            ", contentId=" + contentId +
	            ", writerId=" + writerId +
	            '}';
	}
}
