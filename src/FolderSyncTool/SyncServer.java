package FolderSyncTool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;

/**
 * Created by akshaysaxena on 22/06/17.
 */
public class SyncServer {
    private int SOCKET_PORT;
    private Socket SOCK = null;
    private ServerSocket SERVER_SOCK = null;
    private OutputStream OS = null;

    public SyncServer(int SOCKET_PORT) {
        this.SOCKET_PORT = SOCKET_PORT;
    }

    public void init() throws IOException {
        SERVER_SOCK = new ServerSocket(this.SOCKET_PORT);
        System.out.println("Server Socket Initialized");

        System.out.println("Waiting for the other device to connect");
        this.SOCK = SERVER_SOCK.accept();
        System.out.println("Devices connected | " + SOCK);

        this.OS = this.SOCK.getOutputStream();
    }

    public void fileToSend(Path file_to_send) throws IOException {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataOutputStream dos = null;

        File myFile = new File (file_to_send.toAbsolutePath().toString());
        if(myFile.isFile()){
            byte [] mybytearray  = new byte [(int)myFile.length()];
            fis = new FileInputStream(myFile);
            bis = new BufferedInputStream(fis);
            bis.read(mybytearray,0,mybytearray.length);

            dos = new DataOutputStream(this.OS);
            dos.writeUTF(myFile.getName() + " CREATE");
            dos.writeLong(mybytearray.length);
            dos.write(mybytearray, 0, mybytearray.length);
        }else if(myFile.isDirectory()){
            System.out.println("Scope to Sync Directories in next version !!");
        }
    }

    public void fileToRemove(String fileName) throws IOException {
        DataOutputStream dos = null;

        dos = new DataOutputStream(this.OS);
        dos.writeUTF(fileName + " DELETE");
    }
}
