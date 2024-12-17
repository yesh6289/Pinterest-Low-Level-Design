/*
 * This class is used to share the boards with the other
 */

public class Share {

    // Constructors
    public Share() {
    }

    // share the access to the other profile boards
    public void sahreWith(Profile user, Profile client) {
        String client_id = client.getUserId();
        user.GrantPermission(client_id);
        String user_id = user.getUserId();
        client.setPermissionTakes(user_id);
    }
}
