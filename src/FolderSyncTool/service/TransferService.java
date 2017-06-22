package FolderSyncTool.service;

import java.io.*;

/**
 * Created by akshaysaxena on 22/06/17.
 */
public class TransferService {

    private OutputStream OS = null;

    public TransferService(OutputStream OS) {
        this.OS = OS;
    }

}
