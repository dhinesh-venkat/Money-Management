package Main;

import java.util.*;

import Main.Models.User;

public class Main {

    public static List<User> userData = new ArrayList<User>();

    // check the list and return true if it exists in userData
    public static boolean handleLogin(User user) {
        for(User u: userData) {
            if(u.userName.equals(user.userName)){
                return true;
            }
        }

        return false;
    }

    // adds the user to the userData
    public static void handleSignup(User user) {
        userData.add(user);
    }

    public static void main(String[] args) {

        // Store some data
        User testUser = new User("Test");
        userData.add(testUser);

        Scanner in = new Scanner(System.in);
        System.out.println("Please enter username :");
        String userName = in.nextLine();
        User user = new User(userName);
    
        // Login / sign up
        boolean loginSuccess =  handleLogin(user);

        if(!loginSuccess){
            System.out.println("Signing up!!");
            handleSignup(user);
            
            Home home = new Home(user);
            home.display();
        } else {
            System.out.println("Successfully logged in!");

            // once authenticated, go to home
            Home home = new Home(user);
            home.display();
        }

        // sign out logic here


        in.close();
     }
    
}
