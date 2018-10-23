import org.json.JSONException;
import org.json.JSONObject;

public class JSONParseClass {


    public JSONParseClass(String JSONCommand)
    {
      objectIncoming(JSONCommand);
    }


    private static void objectIncoming(String JSON)
    {
        System.out.println("---------------");
        System.out.println("JSON Objects parsing");
        System.out.println("---------------");



        String jsonObjectString = JSON;
        System.out.println("Parsing json string: " + jsonObjectString);


        try
        {
         JSONObject jsonObject = new JSONObject(jsonObjectString);

         if(jsonObject.has("a"))
         {
            int a = jsonObject.getInt("a");
             System.out.println("The object contains field 'a' with value " +  a);
         }


         }
        catch(JSONException e)
        {
            System.out.println("Got exception in JSON parsin: " + e.getMessage());
        }


    }



}

