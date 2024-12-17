

/*
 * Main class for the lld
 */

public class Main {
    public static void main(String[] args) {
        
        String user = "Yesh";
        String client = "Subhash";
        String user_id = "22PA1A4278";
        String client_id = "22PA1A4279";

        // Set the profile for the user
        Profile p1 = new Profile();
        p1.setUsername(user, user_id);

        System.out.println("user name: " + p1.getUsername());
        System.out.println("user id: "+p1.getUserId());

        // Create a new board for the user
        Board Board1 = new Board("1001", "22PA1A4278");
        Board1.addLinks("http://localhost");
        Board1.addLinks("http://localhost2");
        System.out.println(Board1.getBoard());

        // Set the profile for the client
        Profile p2 = new Profile();
        p2.setUsername(client, client_id);

        System.out.println("user name: " + p2.getUsername());
        System.out.println("user id: "+p2.getUserId());

        // Create a new board for the user
        Board client_Board = new Board("1002", "22PA1A4279");
        client_Board.addLinks("http://localhost3");
        client_Board.addLinks("http://localhost4");
        System.out.println(client_Board.getBoard());

        Share share1 = new Share();
        share1.sahreWith(p1, p2);
        share1.sahreWith(p1, p1);
        share1.sahreWith(p2, p2);

        System.out.println("Profiles allowed by p1");
        System.out.println(p1.getAllowedProfiles());

        System.out.println("Profiles shared to p1");
        System.out.println(p1.getAllowedProfilesByOthers());

        System.out.println("Profiles allowed by p2");
        System.out.println(p1.getAllowedProfiles());

        System.out.println("Profiles shared to p2");
        System.out.println(p1.getAllowedProfilesByOthers());        

        
}

}
