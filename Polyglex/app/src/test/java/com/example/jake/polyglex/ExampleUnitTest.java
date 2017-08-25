package com.example.jake.polyglex;

import org.junit.Test;

import static org.junit.Assert.*;

import storeLocalData.User;


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

    @Test
    public void listArray() throws Exception {

        User test1 = new User("test", "test", "test@test.com", "2345");
        test1.addLexicon("Spanish");
        test1.getLexicon("Spanish").addWord("perro");
        test1.getLexicon("Spanish").addWord("pepa");
        test1.getLexicon("Spanish").addWord("joputa");
        String[] words = test1.getLexicon("Spanish").getOrthographyArray();
        for(int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }
        assertEquals(4, 2 + 2);
    }
}