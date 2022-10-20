package test_data.computer;

import com.google.gson.Gson;

public class Delete {
    public static void main(String[] args) {
        String JSONdata = "{\n" +
                "    \"processorType\": \"2.2GHz\",\n" +
                "    \"ram\": \"8GB\",\n" +
                "    \"os\": \"Ubuntu\",\n" +
                "    \"hdd\": \"400 GB\",\n" +
                "    \"software\": \"Microsoft Office\"\n" +
                "  }";
        Gson gson = new Gson();
        ComputerData computerData = gson.fromJson(JSONdata,ComputerData.class);
        System.out.println(computerData);
        System.out.println(gson.toJson(computerData));
    }
}
