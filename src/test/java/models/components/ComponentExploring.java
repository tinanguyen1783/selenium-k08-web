package models.components;

import java.lang.reflect.Constructor;

public class ComponentExploring {

    public <T extends LoginPage> void login1(Class<T> loginPageClass) {
        Class<?>[] parameter = new Class[]{};

        try {

            Constructor<T> constructor = loginPageClass.getConstructor(parameter);
            T loginPageObj = constructor.newInstance();
            loginPageObj.login();

        } catch (Exception e) {


            e.printStackTrace();
        }
    }


    public static void main(String[] args) {




    }
}
