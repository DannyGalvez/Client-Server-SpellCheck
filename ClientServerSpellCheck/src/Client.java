import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {


        String word;
        int len;
        byte code = 0x0;
        byte disconnect = 0x1;


        Scanner scan = new Scanner(System.in);
        Socket sock = new Socket("127.0.0.1", 6969);

        System.out.println("What word would you like to send? To end the connection enter QUIT:");
        word = scan.nextLine();
        len = word.length();


        DataOutputStream dout = new DataOutputStream(sock.getOutputStream());
        DataInputStream dint = new DataInputStream(sock.getInputStream());

        while(!word.equals("QUIT")){

            dout.writeByte(code);
            dout.writeInt(len);
            dout.writeBytes(word);

            byte inputmessage = dint.readByte();
            int inputLen = dint.readInt();


            byte[] spell = new byte[inputLen];
            dint.readFully(spell);
            String single = "";
            for (byte c: spell){
                char let = (char)c;
                single = single + let;
            }

            System.out.println("Message Code: " + inputmessage);
            System.out.println("Length of Message: " + inputLen);
            System.out.println(single);

            System.out.println("What word would you like to send? To end the connection enter QUIT:");
            word = scan.nextLine();
            len = word.length();

        }

        dout.writeByte(disconnect);

        sock.close();

    }



}
