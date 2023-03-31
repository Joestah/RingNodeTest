import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class Injector {

    public static void main(String args[]){

        Random rand = new Random();
        int portInjection = rand.nextInt(7000,7003);
        System.out.println("Injecting Token into port:" + portInjection);
        try {
            Socket socket = new Socket("127.0.0.1", portInjection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
