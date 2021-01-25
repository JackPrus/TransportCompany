package by.prus.finalproject.dao.mysql;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestTest {


    @BeforeClass
    public static void setUpClass() {
        println("ДО КЛАСССА!!!");
    }

    @AfterClass
    public static void tearDownClass() throws IOException {
        println("ПОСЛЕ КЛАССА!!!");
    }

    private static void println(String string) {
        System.out.println( ": " + string);
    }


    @Test
    public void test_1() {
        this.println("ТЕСТ 1");
    }

    @Test
    public void test_2() {
        this.println("ТЕСТ 2");
    }

}
