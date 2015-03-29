import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by priyadarshini on 3/29/15.
 */
public class Maekawa {
    public static void main(String[] args) throws IOException {
        List<String> nodeDetails = Files.readAllLines(Paths.get("resources/thisNode.txt"), StandardCharsets.UTF_8);
        String hostNode = nodeDetails.get(0);
        System.out.println("Host node details " + hostNode);
        String[] hostDetails = hostNode.split(" ");
        Nodes nodes = new Nodes();
        nodes.create(hostDetails);

        System.out.println("all nodes connected");
    }
}
