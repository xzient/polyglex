package com.example.jake.polyglex;

import org.junit.Test;

import static org.junit.Assert.*;
import userList.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        System.out.println("______tes1__________");
        assertEquals(4, 2 + 2);
    }

    /**
     * This will test if UserList return the expected value.
     * @throws Exception
     */
    @Test
    public void userList_getRightUser() throws Exception {
        System.out.println("_______tes2____________");
        UserList users = UserList.getInstance();
        users.addUser("John", "Proctor", "john@hi.com");
        users.addUser("Juan", "Pepe", "juan@bye.com");

        String user1 = users.getUser("Juan").getUsername();

        assertEquals(true, user1.equals("Juan") );
    }

    @Test
    public void userList_getRightInfo() throws Exception {
        UserList users = UserList.getInstance();
        users.addUser("Valerie", "Proctor", "valerie@hi.com");
        users.addUser("Javiera", "Parra", "javiera@bye.com");

        String user1 = users.getUser("Javiera").getUsername();

        users.displayUsernames();
        assertEquals(true, user1.equals("Javiera") );
    }

    @Test
    public void userList_getRightInfo2() throws Exception {
        UserList users = UserList.getInstance();
        System.out.println("______________________");
        users.displayUsernames();
        System.out.println("______________________");
        System.out.println("Size: " + users.getNumOfUsers());

        assertEquals(false, false );
    }
}