
import java.io.*;
import java.net.*;
import java.util.Date;

public class EchoClient {

    static boolean onoff = true;
    // static String SERVER_ADDRESS = "192.168.23.135";
    static String SERVER_ADDRESS = "192.168.23.135";
    static Integer TCP_SERVER_PORT = 1234;
    static Socket socket;

    public static void main(String[] args) throws Exception {
        String sentence = "Enviar mensaje al movil";
        String modifiedSentence;
        System.out.println("start....   " + (new Date()));
        if (hostAvailabilityCheck()) {

            socket = new Socket(SERVER_ADDRESS, TCP_SERVER_PORT);
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            outToServer.writeBytes(sentence + '\n');
            modifiedSentence = inFromServer.readLine();
            System.out.println(modifiedSentence);
            socket.close();
        } else {
            System.out.println("stop..." + (new Date()));
        }

    }

    public static boolean hostAvailabilityCheck()throws Exception{


       try {
       InetAddress address = InetAddress.getByName("localhost");
       System.out.println("Name: " + address.getHostName());
       System.out.println("Addr: " + address.getHostAddress());
       System.out.println("Reach: " + address.isReachable(3000));
     }
     catch (UnknownHostException e) {
       System.err.println("Unable to lookup web.mit.edu");
     }
     catch (IOException e) {
       System.err.println("Unable to reach web.mit.edu");
     }



        boolean available = true;
        try {
            socket = new Socket(SERVER_ADDRESS, TCP_SERVER_PORT);
            if (!socket.isClosed()) {
                return false;
            }
            if (socket.isConnected()) {
                socket.close();
            }
        } catch (UnknownHostException e) { // unknown host 
            e.printStackTrace();
            available = false;
            socket = null;
        } catch (IOException e) { // io exception, service probably not running 
            e.printStackTrace();
            available = false;
            socket = null;
        } catch (NullPointerException e) {
            e.printStackTrace();
            available = false;
            socket = null;
        }
        return available;
    }
}
