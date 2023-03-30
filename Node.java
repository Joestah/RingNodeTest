import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Node {

    private String ipAddress;
    private int port;
    private int neighbourPort;
    private boolean hasToken;
    private ServerSocket serverSocket;
    private Socket s;

    public Node(String ipAddress, int port, int neighbourPort) {
        this.port = port;
        this.neighbourPort = neighbourPort;
        this.ipAddress = ipAddress;
    }

    public void start() {
        try {
            s = new Socket(ipAddress, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            while(hasToken){

                    if (s.isConnected()) {
                        System.out.println("Node on port " + port + "is connected" + neighbourPort);
                        this.hasToken = false;
                    } else {
                        System.out.println("Node on port " + port + "failed to connect" + neighbourPort);
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }


                    }
                try {
                    s.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if (s.isClosed() )
                    System.
                            out.println("Socket to next ring node (" +neighbourPort+ ": "
                                    +ipAddress+ ") is now closed") ;
                else
                    System.
                            out.println("** Socket to next ring node (" +ipAddress+ ":"
                                    +neighbourPort+ ") is still open!!") ;
            }
            try {

                Socket tempSocket = serverSocket.accept();
                System.out.println("TOKEN RECEIVED ON PORT" + port);
                Thread.sleep(3000);
                this.hasToken = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }



        }




    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getNeighbourPort() {
        return neighbourPort;
    }

    public void setNeighbourPort(int neighbourPort) {
        this.neighbourPort = neighbourPort;
    }

    public boolean isHasToken() {
        return hasToken;
    }

    public void setHasToken(boolean hasToken) {
        this.hasToken = hasToken;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }
}

