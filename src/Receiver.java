/**
 * Created by priyadarshini on 3/29/15.
 */
//Receiver class will run a thread which continuously looks into the blocking linked Q and whenever
//a message come for processing from any of the sockets
public class Receiver extends Thread {
    public Receiver() {
        start();
    }

    @Override
    public void run() {
        System.out.println("enteringggg");
        String receivedMessage;
        try {
            System.out.println("in receiver");
            while (true) {
                receivedMessage = (String) MessageReader.messageQueue.take();
                System.out.println(receivedMessage);
                String[] keyWords = receivedMessage.split(" ");
                if (keyWords[0].equals("REQUEST")) {
                    System.out.println("Request received from node " + keyWords[2]);
//                    receiveRequest(Integer.parseInt(keyWords[2]), Integer.parseInt(keyWords[1]));
                } else if (keyWords[0].equals("REPLY")) {
                    System.out.println("Reply received from node " + keyWords[2].trim());
//                    receiveReply(Integer.parseInt(keyWords[2]));
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Something went wrong in the receiver");
        }
    }
}
