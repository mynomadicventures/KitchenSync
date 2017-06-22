package FolderSyncTool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.LogManager;

/**
 * Created by akshaysaxena on 22/06/17.
 */
public class Logger {
    private static java.util.logging.Logger logger;

    public Logger() throws IOException {
        LogManager.getLogManager().readConfiguration(new FileInputStream("conf/application.properties"));
    }
}
