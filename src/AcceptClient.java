import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

/**
 * Created by priyadarshini on 3/29/15.
 */
public class AcceptClient extends Thread {
    Socket clientSocket;
    BufferedReader reader;
    BlockingQueue messageQueue;

    AcceptClient(int clientId, Socket CSoc) throws Exception {
        System.out.println("Connected to client with ID " + clientId);
        try {
            clientSocket = CSoc;

            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            start();
        } catch (Exception e) {
            System.out.println("Something went wrong in AcceptClient: " + e.getMessage());
        }
    }

    public void run() {
        try {
            String msgFromClient;
            while ((msgFromClient = reader.readLine()) != null) {
                messageQueue.put(msgFromClient.trim());
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong in run of AcceptClient: " + ex.getMessage());
        }
    }
}
