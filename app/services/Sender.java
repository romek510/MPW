package services;


import org.apache.commons.io.FileUtils;
import play.Logger;
import utils.CommonUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by roman.rosiak on 24.06.2017.
 */
public class Sender implements Runnable {

    public int dest;
    private File fileToSend;
    private File destinationFile;



    public Sender(File fileToSend , int destinationFile){
        dest = destinationFile;
        this.fileToSend = fileToSend;
        this.destinationFile = new File(CommonUtil.getBasicStoragePlace()+ destinationFile+"/"+this.fileToSend.getName());
        Logger.info("sending file " + fileToSend.getPath() + " to the place: " + this.destinationFile.getPath());

    }


    @Override
    public void run() {
        try {
            FileUtils.copyFile(this.fileToSend,this.destinationFile);
        } catch (IOException e) {
            Logger.error("Unable to copy file due to following error: " + e.getMessage());
        }
    }
}
