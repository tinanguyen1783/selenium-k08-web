package models.redundant;

import models.components.ComponentCssSelector;

public class AnotationTest {

    public <T> void getComponentCssSelector(Class<T> component){


        try{

           /* Class[] aarg = new Class[1];
            aarg[0] = Integer.class;*/
           // Method method = component.getMethod("setAnotation",aarg);

            //System.out.println(method.getAnnotation(ComponentCssSelector.class).value());


             String cssSelector = component.getAnnotation(ComponentCssSelector.class).value();
            System.out.println(component.getAnnotation(ComponentCssSelector.class));
            System.out.println(cssSelector);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        new AnotationTest().getComponentCssSelector(LoginFormComponent.class);

    }
}
