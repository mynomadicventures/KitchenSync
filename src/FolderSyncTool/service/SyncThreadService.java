package FolderSyncTool.service;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by akshay on 22/06/17.
 */
public class SyncThreadService {

    public void executeSyncProcess(Path source, int PORT) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new WatchService(source, PORT).processEvents();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
