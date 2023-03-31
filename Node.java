import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Node extends Thread {

    private String ipAddress = "127.0.0.1";
    private int port;
    private int neighbourPort;
    private boolean hasToken;
    private ServerSocket serverSocket;
    private Socket s;

    public Node(int port, int neighbourPort) {
        this.port = port;
        this.neighbourPort = neighbourPort;

    }
    public static void main(String args[]){
        if ((args.length < 2) || (args.length > 2)) {
            System.out.println("Usage: [this port][next port]");
            System.out.println("Only "+args.length+" parameters entered");
            System.exit (1) ;
        }
        int tempPort = Integer.parseInt(args[0]);
        int neighbourPort = Integer.parseInt(args[1]);

        Node node = new Node(tempPort,neighbourPort);
        node.start();

    }

    public void start() {
        System.out.println("This is Port: " + port + "  Neighbour: " + neighbourPort);
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Ring Complete");
        }
        while (true) {
            while(hasToken){
                try {
                    s = new Socket(ipAddress, neighbourPort);
                } catch (IOException e) {
                    System.out.println("Ring Complete");
                }
                    if (s.isConnected()) {
                        System.out.println("Node on port " + port + "is connected" + neighbourPort);
                        this.hasToken = false;
                    } else {
                        System.out.println("Node on port " + port + "failed to connect" + neighbourPort);
                        try {
                            Thread.sleep(3000);
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
                System.out.println("Awaiting Connnection to Ring Node");
                if(serverSocket.isClosed()){
                    System.out.println("Server Socket Hosting on:" + port+"Terminated");
                    break;
                }

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

