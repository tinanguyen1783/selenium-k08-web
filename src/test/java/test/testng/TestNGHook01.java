package test.testng;

import org.testng.annotations.*;

public class TestNGHook01 {
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before Suite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("\tBefore Test");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("\t\tBefore Class");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("\t\t\tBefore Method");
    }

    @Test
    public void testSth(){

        System.out.println("\t\t\t\tTest something");
    }

    public void testSth2(){

        System.out.println("\t\t\t\tTest something2");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("\t\t\tAfter Method");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("\t\tAfter Class");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("\tAfter Test");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("\tAfter Suite");
    }


}
