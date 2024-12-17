/*
 * Profile class for the users to create and manage the profiles.
 */
import java.util.ArrayList;


public class Profile {
    // Declare the required variables
    private String username;
    private String user_id;
    private ArrayList<String> Permitted_Profiles = new ArrayList<>();
    private ArrayList<String> Permission_Taken_Profiles = new ArrayList<>();

    // Constructor 
    public Profile() {
    }

    // Set the username and user id
    public void setUsername(String username, String user_id) {
        this.username = username;
        this.user_id = user_id;
    }
   

    // Get the username
    public String getUsername() {
        return username;
    }

    // Get the user_id
    public String getUserId() {
        return user_id;
    }

    // Give premission to other users
    public void GrantPermission(String client_id) {
        Permitted_Profiles.add(client_id);
    }

    // Get the permitted profiles that are allowed by you to access your board
    public ArrayList<String> getAllowedProfiles() {
        return Permitted_Profiles;
    }

    // Set the profiles allowed by other users
    public void setPermissionTakes(String user_id) {
        Permission_Taken_Profiles.add(user_id);
    }

    // the profiles that are allowed by other to the user to see their board
    public ArrayList<String> getAllowedProfilesByOthers() {
        return Permission_Taken_Profiles;
    }




}
