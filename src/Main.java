public class Main {

    public static void main(String [] args)
    {
        connectionClass connection = new connectionClass("104.248.47.74", 80);
        String path = "dkrest/test/get2";
        connection.sendGetCommand(path);
        postClass pc = new postClass("104.248.47.74", 80);

        pc.authorize();
        JSONParseClass jC = new JSONParseClass();

    }
}
