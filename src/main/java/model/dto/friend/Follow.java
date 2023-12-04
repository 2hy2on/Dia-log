package model.dto.friend;

public class Follow {
	private int followId;
	private int followeeId;
	private String followeeName;
	private String followerName;
	private String status;
	private int followerId;

	public Follow() {}
	
	public Follow(int followId, int followeeId, String followeeName, String followerName, String status,
			int followerId) {
		super();
		this.followId = followId;
		this.followeeId = followeeId;
		this.followeeName = followeeName;
		this.followerName = followerName;
		this.status = status;
		this.followerId = followerId;
	}

	public String getFollowerName() {
		return followerName;
	}

	public void setFollowerName(String followerName) {
		this.followerName = followerName;
	}

	public int getFollowId() {
		return followId;
	}

	public void setFollowId(int followId) {
		this.followId = followId;
	}

	public int getFolloweeId() {
		return followeeId;
	}

	public void setFolloweeId(int followeeId) {
		this.followeeId = followeeId;
	}

	public String getFolloweeName() {
		return followeeName;
	}

	public void setFolloweeName(String followeeName) {
		this.followeeName = followeeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getFollowerId() {
		return followerId;
	}

	public void setFollowerId(int followerId) {
		this.followerId = followerId;
	}

	@Override
	public String toString() {
		return "Follow [followId=" + followId + ", followeeId=" + followeeId + ", followeeName=" + followeeName
				+ ", followerName=" + followerName + ", status=" + status + ", followerId=" + followerId + "]";
	}
}
