public class Main {

    public static void main(String [] args)
    {
        connectionClass connection = new connectionClass("104.248.47.74", 80);
        JSONParseClass jsonParse = new JSONParseClass();
        postClass postC = new postClass("104.248.47.74", 80);

        String path = "dkrest/test/get2";
        connection.sendGetCommand(path);



        postC.authorize();
        int currentSessionId = postC.getSessionID();
        System.out.println(currentSessionId);
        connection.getTask1(currentSessionId);

    }




}
