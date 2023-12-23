package model.dto.review;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class ReviewDiary {

	private String contentType;
	private int reviewId;
	private int contentId;
	private String title;
	private Date watchedAt;
	private String start;

	public Date getWatchedAt() {
		return watchedAt;
	}

	public void setWatchedAt(Date watchedAt) {
		this.watchedAt = watchedAt;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFormattedStart() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(watchedAt);
	}

}
