/*
 * Board class to maintain the boards that are created by the user and to upload the images into the boards
 */

// import the arraylist class
import java.util.ArrayList;

public class Board {
    private String boardId;
    private String userId;
    private final ArrayList<String> Links = new ArrayList<>();

    //Create the board object
    public Board(String bId, String uId) {
        this.boardId = bId;
        this.userId = uId;
    }

    // Add the image links to the board
    public void addLinks(String url) {
        Links.add(url);
    }

    // Get the board with the urls
    public ArrayList<String> getBoard() {
        return Links;
    }

    

}
