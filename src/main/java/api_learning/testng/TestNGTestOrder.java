package api_learning.testng;

import org.testng.annotations.Test;

public class TestNGTestOrder {

    @Test(priority = 1, dependsOnGroups = {"test01"})

    public void test02(){

        System.out.println("test02");
    }
    @Test(priority = 2)
    public void test01(){

        System.out.println("test01");
    }

}
