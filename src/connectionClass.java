import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;


public class connectionClass {


    public static void main(String [] args)
    {
      connectionClass connection = new connectionClass("104.248.47.74", 80);
      String path = "dkrest/test/get2";
      connection.sendGetCommand(path);
    }

    private String BASE_URL;

    public connectionClass(String host, int port)
    {
       BASE_URL = "http://" + host + ":" + port + "/";
    }

    public void sendGetCommand(String path)
    {

        sendGet(path);
    }

    private void sendGet(String path)
    {
        try {
            String url = BASE_URL + path;
            URL urlObj = new URL(url);
            System.out.println("Sending HTTP GET to" + url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Server reached");

                InputStream stream = connection.getInputStream();
                String responseBody = convertStreamToString(stream);
                stream.close();
                System.out.println("Response from the server");
                System.out.println(responseBody);
            } else {
                String responseDescription = connection.getResponseMessage();
                System.out.println("Request failed, response code: " + responseCode + " (" + responseDescription + ")");
            }
        }

        catch(ProtocolException e)
        {
            System.out.println("Protocol not supported by the server");
        }
        catch (IOException e)
        {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private String convertStreamToString(InputStream is)
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        try
        {
            String inputLine;
            while((inputLine = in.readLine()) != null)
            {
                response.append(inputLine);
                response.append('\n');
            }
        }
        catch(IOException I)
        {
            System.out.println("Could not read the data from the HTTP response: " + I.getMessage());
        }
        return response.toString();
    }


}





