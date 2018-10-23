import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class postClass {

    private String BASE_URL;

    public postClass(String host, int port)
    {
        BASE_URL = "http://" + host + ":" + port + "/";
    }

    public void postRandomNumbers()
    {
        int a = (int) Math.round(Math.random()*100);
        int b = (int) Math.round(Math.random()*100);

        JSONObject json = new JSONObject();
        json.put("a", a);
        json.put("b", b);

        System.out.println("Posting this JSON data to server");
        System.out.println(json.toString());

        sendPost("dkrest/test/post", json);

    }

    private int sessionID;
    public int getSessionID()
    {
        return this.sessionID;
    }

    public void authorize()
    {
        String email = "klausdy@stud.ntnu.no";
        String mobNumb = "47273049";

        JSONObject json = new JSONObject();
        json.put("email", email);
        json.put("phone", mobNumb);

        JSONObject temp;
        temp = sendPost("dkrest/auth",json);
        sessionID = temp.getInt("sessionId");

    }





    private JSONObject sendPost(String path, JSONObject jsonData) {
        try {
            String url = BASE_URL + path;
            URL urlObj = new URL(url);
            System.out.println("Sending HTTP POST to " + url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            os.write(jsonData.toString().getBytes());
            os.flush();

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Server reached");

                // Response was OK, read the body (data)
                InputStream stream = con.getInputStream();
                String responseBody = convertStreamToString(stream);
                stream.close();
                System.out.println("Response from the server:");
                System.out.println(responseBody);
                JSONParseClass jsonClass = new JSONParseClass();
                JSONObject jsonObject = jsonClass.objectIncoming(responseBody);
                return jsonObject;
            } else {
                String responseDescription = con.getResponseMessage();
                System.out.println("Request failed, response code: " + responseCode + " (" + responseDescription + ")");

            }
        } catch (ProtocolException e) {
            System.out.println("Protocol not supported by the server");

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                response.append('\n');
            }
        } catch (IOException ex) {
            System.out.println("Could not read the data from HTTP response: " + ex.getMessage());
        }
        return response.toString();
    }



}
