import org.json.JSONException;
import org.json.JSONObject;

public class JSONParseClass {


    public JSONParseClass()
    {

    }


    public JSONObject objectIncoming(String body)
    {
        System.out.println("---------------");
        System.out.println("JSON Objects parsing");
        System.out.println("---------------");


        try {
            JSONObject jsonObject = new JSONObject(body);
            return jsonObject;
        }
        catch(JSONException e)
        {
            System.out.println("Got exception in JSON parsin: " + e.getMessage());
            return null;
        }

    }



}

