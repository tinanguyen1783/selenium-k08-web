package test_data;

import com.google.gson.Gson;
import test_data.computer.ComputerData;

public class TestGSON {

    //Conver from Json to Java object
    // Conver from java object to GSOn

    public static void main(String[] args) {
        testDataBuilder();
    }

    private static void testDataBuilder() {


        String path = "/src/main/java/test_data/computer/CheapComputerData.json";
        ComputerData data = DataObjectBuilder.buildDataObjectFrom(path,ComputerData.class);
        System.out.println(data);

    }

    public static void exploreGsonFeature(){
        String JSONString = "{\n" +
                "    \"processorType\": \"Fast\",\n" +
                "    \"ram\": \"8 GB\",\n" +
                "    \"hdd\": \"320 GB\",\n" +
                "    \"software\": \"Image Viever\"\n" +
                "  }";
        Gson gson = new Gson();
        ComputerData computerData = gson.fromJson(JSONString, ComputerData.class);
        System.out.println(computerData);


    }

}
