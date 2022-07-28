package models.components;

public class AnotationTest {

    public <T> void getComponentCssSelector(Class<T> component){


        try{
            String cssSelector = component.getAnnotation(ComponentCssSelector.class).value();
            System.out.println(cssSelector);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        new AnotationTest().getComponentCssSelector(LoginFormComponent.class);

    }
}
