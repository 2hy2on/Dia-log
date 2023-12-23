package model.dto.review;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Review {

	private int reviewId;
	private int contentId;
	private int writerId;
	private boolean isPrivate;
	private Float rate;
	private LocalDateTime updatedAt;
	private LocalDateTime createdAt;
	private Date watchedAt;
	private String mediaImg;
	private String title;
	private String detail;

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public Date getWatchedAt() {
		return watchedAt;
	}

	public void setWatchedAt(Date date) {
		this.watchedAt = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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

}
