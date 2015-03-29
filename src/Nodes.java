import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by priyadarshini on 3/29/15.
 */
public class Nodes {
    public static final int TOTAL_SERVERS = 7;
    protected int id;
    protected int leftChild, rightChild;
    protected int parent;
    private Map<Integer, Socket> connectedSockets;
    private ServerTree rootNode;
    HashMap<Integer, ServerTree> serverMap;

    public Nodes() {
        this.connectedSockets = new HashMap<Integer, Socket>();
        this.serverMap = new HashMap<Integer, ServerTree>();
        ;
    }

    void create(String[] hostDetails) throws IOException {
        id=Integer.parseInt(hostDetails[1]);
        if (hostDetails[0].equals("server")) {
            ServerSocket serverSocket = null;
            int clientId = 1;
            try {
                serverSocket = new ServerSocket(Integer.valueOf(hostDetails[2]));
                while (true) {
                    System.out.println("Waiting to accept connections & clientID is " + clientId);
                    Socket connectedSocket = serverSocket.accept();
                    AcceptClient obClient = new AcceptClient(clientId, connectedSocket);
                    connectedSockets.put(clientId, connectedSocket);
                    System.out.println("Connected to client id " + clientId);
                    clientId++;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if(hostDetails[0].equals("client")) {
            List<String> serverDetails = Files.readAllLines(Paths.get("resources/serverAddress.txt"), StandardCharsets.UTF_8);
            for (String serverDetail : serverDetails) {
                String[] splitServerDetails = serverDetail.split(" ");
                Socket socket = new Socket(splitServerDetails[1], Integer.parseInt(splitServerDetails[2]));
                connectedSockets.put(Integer.parseInt(splitServerDetails[0]), socket);
            }
            createServerTree();
        } else {
            System.out.println("Invalid input data.. Please correct it");
        }
    }

    private void createServerTree() {
        for (int nodeNumber = 1; nodeNumber <= TOTAL_SERVERS; nodeNumber++) {
            ServerTree serverTree = new ServerTree(nodeNumber);
            if(rootNode==null){
                rootNode= serverTree;
            }
            serverMap.put(nodeNumber, serverTree);
        }
    }


    public Map<Integer, Socket> getConnectedSockets() {
        return connectedSockets;
    }


}
