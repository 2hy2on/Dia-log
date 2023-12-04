package model.dto.board;

public class Board {
    private int boardID;
    private String boardTitle;
    private String ID;
    private String boardDate;
    private String boardContent;
    private int boardAvailable;
    public int getBoardID() {
        return boardID;
    }
    public void setBoardID(int boardID) {
        this.boardID = boardID;
    }
    public String getBoardTitle() {
        return boardTitle;
    }
    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public String getBoardDate() {
        return boardDate;
    }
    public void setBoardDate(String boardDate) {
        this.boardDate = boardDate;
    }
    public String getBoardContent() {
        return boardContent;
    }
    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }
    public int getBoardAvailable() {
        return boardAvailable;
    }
    public void setBoardAvailable(int boardAvailable) {
        this.boardAvailable = boardAvailable;
    }
}