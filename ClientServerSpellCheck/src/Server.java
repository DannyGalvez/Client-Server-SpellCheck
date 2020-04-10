import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;

public class Server {

    static class Handle extends Thread {
        private SpellCheck checker = new SpellCheck();
        private Socket conn;
        private DataInputStream dint;
        private DataOutputStream dout;
        private byte code = 0x02;

        private Handle(DataOutputStream o, DataInputStream i, Socket s) {
            conn = s;
            dint = i;
            dout = o;
        }

        @Override
        public void run() {
            try {

                while (true) {
                    byte inputMessage = dint.readByte();
                    if (inputMessage == 0x0) {
                        dout.writeByte(code);
                    } else {
                        break;
                    }
                    int len = dint.readInt();

                    byte[] spell = new byte[len];
                    dint.readFully(spell);
                    String single = "";
                    for (byte c : spell) {
                        char let = (char) c;
                        single = single + let;
                    }

                    if (checker.SpellCheck(single)) {
                        dout.writeInt(7);
                        dout.writeBytes("Correct");
                    } else {
                        dout.writeInt(10);
                        dout.writeBytes("Misspelled");
                    }

                    if (false) {
                        break;
                    }

                }

            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket sock = new ServerSocket(6969);

        while (true) {
            System.out.println("Listening..");
            Socket conn = sock.accept();

            DataOutputStream dout = new DataOutputStream(conn.getOutputStream());
            DataInputStream dint = new DataInputStream(conn.getInputStream());

            if (conn.isConnected()) {
                System.out.println("Connected");
            }

            Handle hand = new Handle(dout, dint, conn);

            hand.start();

        }


    }
}
