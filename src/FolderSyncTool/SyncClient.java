package FolderSyncTool;

import FolderSyncTool.validator.IPValidator;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static FolderSyncTool.constants.Strings.*;

/**
 * Created by akshaysaxena on 22/06/17.
 */
public class SyncClient {
    private static final String LOG = SyncClient.class.getName();
    private static final Logger logger = Logger.getLogger(LOG);
    public static int PORT;
    public static String PATH_TO_FILE;
    private static Path dir1 = null;
    private static IPValidator validator = new IPValidator();
    private static String IPA = null;

    public final static int FILE_SIZE = 6022386;

    public static void main (String [] args ) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(REMOTE_IP_ADDRESS_MSG);
        IPA = scanner.nextLine();
        if(!validator.validate(IPA)) {
            System.out.printf(IF_NOT_VALID_IP_MSG);
            return;
        }

        System.out.println(FOLDER_SOURCE_MSG);
        dir1 = Paths.get(scanner.nextLine());
        PATH_TO_FILE = dir1.toAbsolutePath().toString() + "/";

        System.out.println(REMOTE_PORT_TO_SYNC);
        PORT = scanner.nextInt();

        int bytesRead;
        FileOutputStream fos = null;
        Socket sock = null;
        DataInputStream din = null;

        sock = new Socket(IPA, PORT);
        System.out.println("Connecting...");
        logger.log(Level.FINE, "Connecting to IP : "+IPA + "PORT : "+PORT);


        try {
            InputStream is = sock.getInputStream();
            din = new DataInputStream(is);
            while(true) {
                String fileNameAndOp = din.readUTF();
                String[] splitStr = fileNameAndOp.split("\\s+");
                String fileName = "";
                for (int i=0; i < splitStr.length-1; i++) {
                    fileName += splitStr[i];
                    if (i == splitStr.length-2) {
                        continue;
                    }
                    else {
                        fileName += " ";
                    }
                }
                String operation = splitStr[splitStr.length-1];

                if(operation.equals(new String("CREATE"))) {
                    fos = new FileOutputStream(PATH_TO_FILE + fileName);
                    long size = din.readLong();
                    byte[] buffer = new byte[1024];

                    while (size > 0 && (bytesRead = din.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1)
                    {
                        fos.write(buffer, 0, bytesRead);
                        size -= bytesRead;
                    }
                    System.out.println(SYNC_INPROGRESS_MESSAGE);
                    logger.log(Level.FINE, "ENTRY_CREATE : " + PATH_TO_FILE + fileName
                            + " downloaded");
                    System.out.println(SYNC_SUCCESS_MESSAGE);
                }

                else if(operation.equals(new String("DELETE"))){
                    System.out.println(SYNC_INPROGRESS_MESSAGE);
                    File myFile = new File (PATH_TO_FILE + fileName);
                    if(myFile.delete()) {
                        logger.log(Level.FINE, "ENTRY_DELETE : " + PATH_TO_FILE + fileName);
                        System.out.println(SYNC_SUCCESS_MESSAGE);
                    } else {
                        logger.log(Level.FINE, "ENTRY_DELETE : " + PATH_TO_FILE + fileName);
                        System.out.println("Error while deleting the file, refer logs for more info.");
                        System.out.println(SYNC_SUCCESS_MESSAGE);
                    }
                }

            }
        }
        finally {
            if (sock != null) sock.close();
        }

    }

}
