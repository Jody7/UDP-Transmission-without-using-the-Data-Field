
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import  java.io.*;
import java.net.*;
import java.security.AlgorithmParameters;
import java.security.spec.KeySpec;

public class Client {
    public static void main(String args[]) throws Exception{

        String key = "a4655a86357b63c3d690c84142612c8f";


        BufferedReader inFromUser =          new BufferedReader(new InputStreamReader(System.in));
        String eax = inFromUser.readLine();

        eax = EncryptUtils.xorMessage(eax, key );
        eax = EncryptUtils.base64encode(eax);

        String msg = stringToBinary(eax,false);

        String destination = "127.0.0.1";
        System.out.println("Transmitting Data");


        for (int i = 0; i < msg.length(); i++){
            char ch = msg.charAt(i);


            if(ch=='0'){
                send0(destination);
            }
            else if (ch=='1'){
                send1(destination);
            }
            else{
            System.out.println("Error Transmitting, Char = " + ch);
            }
        }
        sendEnd(destination);
        System.out.println("Sent Encrypted String: " + eax+ "with key: " + key);



    }
    public static void send0(String dest) throws Exception{
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(dest);
        byte[] sendData = new byte[0];
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 7700);
        clientSocket.send(sendPacket);

        System.out.println("Sent Packet 0");
        clientSocket.close();
        Thread.sleep(10);
    }
    public static void send1(String dest) throws Exception{
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(dest);
        byte[] sendData = new byte[0];
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 7701);
        clientSocket.send(sendPacket);
        System.out.println("Sent Packet 1");
        clientSocket.close();
        Thread.sleep(10);
    }
    public static void sendEnd(String dest) throws Exception{
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(dest);
        byte[] sendData = new byte[0];
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 7702);
        clientSocket.send(sendPacket);
        System.out.println("Sent End Packet");
        clientSocket.close();
        Thread.sleep(10);
    }

    public static String stringToBinary(String str, boolean pad ) throws Exception{
        byte[] bytes = str.getBytes("US-ASCII");
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }

        }
        return binary.toString();
    }



}
