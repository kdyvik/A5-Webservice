import org.json.JSONObject;

public class JSONParseClass {

    private static void objectIncoming(String JSON )
    {
        System.out.println("---------------");
        System.out.println("JSON Objects parsing");
        System.out.println("---------------");



        String jsonObjectString = JSON;
        System.out.println("Parsing json string: " + jsonObjectString);

     try
     {
         JSONObject jsonObject = new JSONObject(jsonObjectString);
     }

    }



}

