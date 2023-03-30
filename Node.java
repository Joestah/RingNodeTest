import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Node {

    private String ipAddress;
    private int port;
    private int neighbourPort;
    private boolean hasToken;

    public Node(String ipAddress, int port, int neighbourPort) {
        this.port = port;
        this.neighbourPort = neighbourPort;
        this.ipAddress = ipAddress;

        try {
            Socket s = new Socket(ipAddress, port);
            if (s.isConnected()) {
                System.out.println("Node on port " + port + "is connected" + neighbourPort);
            } else {
                System.out.println("Node on port " + port + "failed to connect" + neighbourPort);


            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}

