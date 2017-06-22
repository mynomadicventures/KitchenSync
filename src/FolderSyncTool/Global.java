package FolderSyncTool;
import FolderSyncTool.service.SyncThreadService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static FolderSyncTool.constants.Strings.*;

/**
 * Created by akshay on 22/06/17.
 */
public class Global {
    private static Path DIRECTORY = null;
    private static int PORT;
    private static int EXECUTE_FLAG = 0;

    public static void main(String args[]) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (EXECUTE_FLAG == 0){

            System.out.println(FOLDER_SOURCE_MSG);
            DIRECTORY = Paths.get(scanner.nextLine());

            System.out.println(REMOTE_PORT_TO_SYNC);
            PORT = scanner.nextInt();
            System.out.format(CONNECTION_EST_MSG_FORMATTER, PORT);

            EXECUTE_FLAG = 1;
        }

        // register directory and process its events
        if(EXECUTE_FLAG == 1){
            SyncThreadService service = new SyncThreadService();
            service.executeSyncProcess(DIRECTORY, PORT);
        }

    }
}
