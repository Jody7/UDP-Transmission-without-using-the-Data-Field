
//lets use some udp!
import java.io.*;
import java.net.*;

class Handler extends Thread{
    //extends thread for multi-threading purposes

    //KEY SET HERE
    String key = "a4655a86357b63c3d690c84142612c8f";

    
    public void run(){

        while (true){
            try{

                if(MainInterclass.stop == true){
                    System.out.println("Finished... ");
                    System.out.println("Decoded Message: \n\n\n\n---------\n\n");
                    System.out.println(MainInterclass.convert(MainInterclass.total));
                    System.out.println("\nDecrypted with Key: \n\n---------\n\n");

                    System.out.println(EncryptUtils.xorMessage(EncryptUtils.base64decode(MainInterclass.convert(MainInterclass.total)),key));

                    Server.Shutdown();

                }

                Thread.sleep(10);
            }catch (Exception e){}
        }
    }
}

class Server1 extends Thread{
    //more multi-threading!!!
    public void run(){
        DatagramSocket serverSocket1 = null;
        try {
            serverSocket1 = new DatagramSocket(7701);
            
        } catch(Exception e){}
        byte[] receiveData1 = new byte[0];





        while(true){

            DatagramPacket receivePacket1 = new DatagramPacket(receiveData1, receiveData1.length);
            try {
                serverSocket1.receive(receivePacket1);
                //when receive thread unlocks and continues on its way

                //SoundUtils.tone(500, 10, 0.1);
                System.out.println("1");
                MainInterclass.add1();
                Thread.sleep(10);
            }catch (Exception e){}



        }

    }
}

class ServerStop extends Thread {
    public void run() {
        DatagramSocket serverSocketStop = null;
        try {
            serverSocketStop = new DatagramSocket(7702);
        } catch (Exception e) {
        }
        byte[] receiveDataStop = new byte[0];
        while (true) {
            DatagramPacket receivePacketStop = new DatagramPacket(receiveDataStop, receiveDataStop.length);
            try {
                serverSocketStop.receive(receivePacketStop);
                MainInterclass.stop();
                System.out.println("End Packet Received.");
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }
    }
}
public class Server{
    //main thread
    public static Server1 t = null;
    public static ServerStop n = null;
    public static Handler f = null;

    static boolean on = true;

    public static void Shutdown(){
        //System.out.println("SHUTDOWN!");
        t.stop();
        n.stop();
        f.stop();
        on = false;
        //depcrated stops, but not going to restart the threads anyways..
    }

    public static void main(String args[]) throws Exception{
        DatagramSocket serverSocket0 = new DatagramSocket(7700);

        byte[] receiveData0 = new byte[0];

        t = new Server1();
        t.start();
        n = new ServerStop();
        n.start();
        f = new Handler();
        f.start();
        //start ALL the threads!!!
        
        while(on){
            
            DatagramPacket receivePacket0 = new DatagramPacket(receiveData0, receiveData0.length);

            serverSocket0.receive(receivePacket0);

            System.out.println("0");
            MainInterclass.add0();

            //System.out.println(MainInterclass.total);

            Thread.sleep(10);
        }


    }


}
