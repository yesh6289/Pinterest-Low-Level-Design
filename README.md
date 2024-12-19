Problem Statement
The objective of this document is to create a low-level design for a Pinterest-like application utilizing Object-Oriented Programming (OOP) concepts and Java classes. The design will focus on encapsulating the core functionalities of the application, which include image uploading, feed generation, and social interactions such as following and unfollowing users.
This document will detail the class structure, relationships between classes, and interactions among various components of the application. By implementing a robust low-level design, we aim to facilitate scalability and performance while adhering to best practices in software design.
1. Introduction
The main approach to solve the problem is as follows:
Assume that the user authentication is taken care of along with the security of the user data and their profiles. Now, the user can create boards that is in other words can be said like this boards can be made by the user itself by uploading the urls of the images into the board and the pinterest can render these urls into images by itself and there can be many links in one board. Also we have to give flexibility to the users like whom they share their boards to such that they can allow others to watch their images. Along with that there should be flexibility for the users to request others to see their image boards. This is the main theme of the LLD.
Scope:
This low-level design (LLD) document outlines the architecture and components necessary to implement the core functionalities of a Pinterest-like application, focusing on user-generated boards and image sharing. The scope of this document includes:
User Boards Creation: 
Users will have the ability to create multiple boards.
Each board can contain multiple image URLs uploaded by the user.
Sharing Functionality: 
Users will have options to share their boards with specific individuals or groups.
The system will provide mechanisms for users to request access for others to view their image boards.
Profile management: 
The design will allow for flexible sharing settings, enabling users to control who can view or interact with their boards.
Users can send requests for others to view their boards, fostering social interaction within the application.
Technical Implementation: 
The LLD will detail class structures, relationships, and interactions based on Object-Oriented Programming (OOP) principles using Java.
2. Requirements Specification:
Functional Requirements:
Profile managing: Users can share their boards to other profiles and also manage the permission and seek permission to see other user profiles
Board Managing: Creating the board and uploading the image urls and make new board ids , make them visible to other followers
Sharing : Giving and asking the permission to share the boards of other users by asking them to share it with us .
Non-Functional Requirements:
Scalability: The system must handle millions of users and images efficiently. To do so Dynamo DB is used as there is more read operations than the write operations.
Performance: Fast response times for image uploads and feed retrieval.
Availability: High uptime with minimal downtime for maintenance.
3. Explanation of Classes of LLD
Explanation of the Board Class
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

This class is a fundamental component of the Pinterest-like application outlined in the low-level design document. It encapsulates the concept of a "board," which serves as a collection for images represented by URLs. Below is a detailed explanation of the class, its attributes, and methods, along with its relevance to the overall design.
Class Attributes
boardId: 
Type: String
Description: This attribute uniquely identifies each board. It is essential for distinguishing between different boards created by users.
userId: 
Type: String
Description: This attribute stores the ID of the user who created the board. It establishes ownership and allows for tracking which user is associated with which board.
Links: 
Type: ArrayList<String>
Description: This is a dynamic array that holds the URLs of images associated with the board. The use of an ArrayList allows for flexible management of image links, including adding or removing links as needed.
Constructor
public Board(String var1, String var2): 
Purpose: This constructor initializes a new instance of the Board class with a specified boardId and userId. It sets up the initial state of the board, allowing users to create their boards effectively.
Methods
public void addLinks(String var1): 
Purpose: This method allows users to add image URLs (links) to the board. By accepting a string parameter representing an image URL, it appends this URL to the Links list.
Relevance: This method is crucial for enabling users to populate their boards with images, which aligns with the application's core functionality of image sharing and organization.
public ArrayList<String> getBoard(): 
Purpose: This method retrieves the list of image URLs stored in the board. It returns the Links array, allowing other components or services (e.g., user interface) to access and display these images.
Relevance: Providing access to the list of links is essential for rendering images in the user interface and for any functionality that involves displaying or sharing board contents.
Usage in Context
In the context of the Pinterest-like application:
The Board class plays a pivotal role in managing user-generated content by encapsulating all relevant data and behaviors associated with a user's board.
It supports key functionalities such as adding images and retrieving them for display, which are central to user interaction within the application.
By using OOP principles, such as encapsulation (hiding data within classes) and modularity (separating concerns), this class contributes to a clean and maintainable codebase that can be easily extended or modified in future iterations.
Overall, the class serves as a foundational building block for implementing boards within the Pinterest-like application, facilitating user engagement through image collection and sharing functionalities.
Explanation of the Profile Class
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

This class is a crucial component of the Pinterest-like application, designed to manage user profiles and their associated permissions. This class encapsulates user information and provides methods for managing access to boards and images. Below is a detailed breakdown of the class, its attributes, methods, and its relevance to the overall design.
Class Attributes
username: 
Type: String
Description: This attribute stores the username of the user. It is essential for identifying users within the application and personalizing their experience.
user_id: 
Type: String
Description: This attribute uniquely identifies each user in the system. It is critical for associating users with their boards and permissions.
Permitted_Profiles: 
Type: ArrayList<String>
Description: This dynamic array holds the IDs of users who have been granted permission to view the user's boards. It allows users to manage who can access their shared content.
Permission_Taken_Profiles: 
Type: ArrayList<String>
Description: This array stores the IDs of users whose boards the current user has permission to view. It helps track which profiles have granted access to their content.
Constructor
public Profile(): 
Purpose: This default constructor initializes a new instance of the Profile class without setting any initial values. It allows for creating profile objects that can be configured later.
Methods
public void setUsername(String username, String user_id): 
Purpose: This method sets both the username and user ID for the profile. It allows users to establish their identity within the application.
Relevance: Setting these attributes is fundamental for profile creation and management, enabling personalized interactions.
public String getUsername(): 
Purpose: This method retrieves the username associated with the profile.
Relevance: Providing access to the username is important for displaying user information in the application interface.
public String getUserId(): 
Purpose: This method returns the unique user ID of the profile.
Relevance: Accessing the user ID is necessary for backend operations, such as managing permissions and associating boards with users.
public void GrantPermission(String client_id): 
Purpose: This method allows a user to grant access to another user (identified by client_id) to view their boards.
Relevance: This functionality supports one of the core features of the application—sharing content with others while maintaining control over who can access it.
public ArrayList<String> getAllowedProfiles(): 
Purpose: This method returns a list of profiles that have been granted permission to view this user's boards.
Relevance: It provides transparency regarding who can access a user's shared content, enhancing user control over privacy settings.
public void setPermissionTakes(String user_id): 
Purpose: This method records that a specific user (identified by user_id) has granted permission for this user to view their boards.
Relevance: Tracking permissions taken from other users is essential for managing reciprocal sharing relationships within the application.
public ArrayList<String> getAllowedProfilesByOthers(): 
Purpose: This method retrieves a list of profiles that have allowed this user to view their boards.
Relevance: It enables users to see which boards they can access based on permissions granted by others, fostering community interaction.
Usage in Context
In the context of the Pinterest-like application:
The Profile class serves as a foundational element for managing user identities and permissions, which are critical for enabling social interactions within the platform.
By encapsulating user data and permission management methods, this class enhances modularity and maintainability of the codebase, adhering to OOP principles.
The ability to grant and track permissions aligns with the application's goal of providing users with control over their content while facilitating sharing and collaboration among users.
Overall, the Profile class contributes significantly to creating a personalized and secure environment where users can manage their boards and share images effectively with others.
Explanation of the Share Class
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

The class is designed to facilitate the sharing of boards between users in the Pinterest-like application. This class encapsulates the functionality required to manage permissions related to board access, enabling users to collaborate and interact with each other's content. Below is a detailed breakdown of the class, its methods, and its relevance to the overall design.
Constructors
public Share(): 
Purpose: This default constructor initializes a new instance of the Share class. It does not require any parameters and allows for the creation of a Share object that can be used to manage sharing operations.
Methods
public void sahreWith(Profile user, Profile client): 
Purpose: This method facilitates the sharing of board access between two profiles: the user (the profile sharing their boards) and the client (the profile receiving access).
Functionality: 
It retrieves the userId of the client profile and grants permission to the user by calling the GrantPermission method on the user object.
It also retrieves the userId of the user profile and records that this user has permission to view the boards of the client by calling setPermissionTakes.
Relevance: This method is essential for implementing one of the core functionalities of the application—allowing users to share their boards with others. By managing permissions in both directions (granting and receiving), it fosters collaboration and social interaction within the platform.
Usage in Context
In the context of the Pinterest-like application:
The Share class serves as a bridge between users, enabling them to share their boards seamlessly while maintaining control over who can view their content.
By encapsulating sharing logic within its own class, it adheres to principles of modularity and separation of concerns, making it easier to manage and extend sharing functionalities in future iterations.
The method's implementation reflects a user-centric approach, allowing for reciprocal sharing relationships where both users can access each other's boards, thus enhancing community engagement.
Overall, the Share class plays a crucial role in enhancing user experience by enabling collaborative features that are central to a social media platform focused on visual discovery and sharing.
Explanation of the Main Class
Main class serves as the entry point for the Pinterest-like application, demonstrating the functionality of the previously defined classes ( Profile, Board, and Share). This class includes the main method where various operations related to user profiles, boards, and sharing permissions are executed. Below is a detailed breakdown of the class, its operations, and its relevance to the overall design.
Main Method Breakdown
User and Client Initialization:
The user and client variables are initialized with sample usernames, and user_id and client_id variables are assigned unique identifiers.
These variables represent two users in the system, "Yesh" and "Subhash," each with their respective IDs.
Profile Creation for User:
javaProfile p1 = new Profile(); p1.setUsername(user, user_id);
A new instance of the Profile class (p1) is created for the user "Yesh."
The setUsername method is called to assign the username and user ID.
The program prints out the username and user ID to confirm they have been set correctly.
Board Creation for User:
javaBoard Board1 = new Board("1001", "22PA1A4278"); Board1.addLinks("<http://localhost>"); Board1.addLinks("<http://localhost2>");
A new instance of the Board class (Board1) is created for "Yesh" with a unique board ID.
Two image URLs are added to this board using the addLinks method.
The contents of the board (the list of URLs) are printed to confirm they have been added successfully.
Profile Creation for Client:
javaProfile p2 = new Profile(); p2.setUsername(client, client_id);
Similar to the previous steps, a new profile (p2) is created for the client "Subhash."
The username and user ID are set, followed by printing these details.
Board Creation for Client:
javaBoard client_Board = new Board("1002", "22PA1A4279"); client_Board.addLinks("<http://localhost3>"); client_Board.addLinks("<http://localhost4>");
A new board (client_Board) is created for "Subhash" with its own unique ID.
Two more image URLs are added to this board.
Sharing Boards Between Users:
javaShare share1 = new Share(); share1.sahreWith(p1, p2); share1.sahreWith(p1, p1); share1.sahreWith(p2, p2);
An instance of the Share class (share1) is created.
The sahreWith method is called multiple times to share permissions between users: 
"Yesh" grants permission to "Subhash."
"Yesh" shares access to their own profile (self-sharing).
"Subhash" shares access to their own profile (self-sharing).
Displaying Permissions:
`javaSystem.out.println("Profiles allowed by p1"); System.out.println(p1.getAllowedProfiles());
System.out.println("Profiles shared to p1"); System.out.println(p1.getAllowedProfilesByOthers());
System.out.println("Profiles allowed by p2"); System.out.println(p2.getAllowedProfiles());
System.out.println("Profiles shared to p2"); System.out.println(p2.getAllowedProfilesByOthers());`
The program prints out lists of profiles that have been granted permission by each user and those that have been shared with them. 
It calls getAllowedProfiles() on both profiles to show who can view their boards.
It calls getAllowedProfilesByOthers() on both profiles to show which profiles have granted them access.
Usage in Context
In the context of the Pinterest-like application:
The Main class serves as a demonstration of how users can create profiles, manage boards, and share access with one another.
It showcases how different components of the application interact, providing a clear example of how users can engage with each other through sharing functionalities.
This class acts as a testing ground for verifying that all parts of the application work together as intended, allowing developers to validate functionality before integrating additional features or deploying the application.
Overall, the Main class encapsulates essential use cases that highlight user interactions within the application, reinforcing its purpose as a social platform for visual discovery and sharing.
Conclusion
The low-level design (LLD) for the Pinterest-like application provides a comprehensive framework for implementing core functionalities related to user profiles, boards, and sharing mechanisms. By leveraging Object-Oriented Programming (OOP) principles, the design promotes modularity, reusability, and maintainability, ensuring that the application can evolve over time while meeting user needs.The key components of this design include:
Profile Management: The Profile class encapsulates user information and permission management, allowing users to control who can access their boards. This feature enhances user engagement and fosters a sense of community.
Board Functionality: The Board class serves as a container for images, enabling users to create and manage collections of visual content. The ability to add image URLs dynamically supports a rich user experience centered around visual discovery.
Sharing Mechanisms: The Share class facilitates the sharing of boards between users, allowing for reciprocal permissions that enhance collaboration. This functionality is crucial for creating a social environment where users can interact and explore each other's content.
The Main class effectively demonstrates how these components work together, providing a clear example of user interactions within the application. Through the creation of profiles, boards, and sharing permissions, it illustrates the seamless user experience intended for the platform. Overall, this LLD serves as a solid foundation for developing a robust Pinterest-like application that prioritizes user engagement, content sharing, and social interaction. By adhering to best practices in software design and development, this architecture not only addresses current requirements but also positions the application for future enhancements and scalability.
