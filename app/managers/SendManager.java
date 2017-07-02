package managers;

import play.Logger;
import utils.CommonUtil;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by mbajdek on 11.06.2017.
 */
public class SendManager implements Runnable{

    private BalanceManager balanceManagerInstance = BalanceManager.getBalanceManagerInstance();
    private Queue<File> newFiles = new LinkedList<>();
    private ExecutorService service = Executors.newFixedThreadPool(5);
    private int currentNumberOfFiles = 0;


    @Override
    public void run() {



        while(true){
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            newFiles = balanceManagerInstance.getCurrentFileQueue();
//            Logger.info("currentFileQueue: " + newFiles + CommonUtil.getBasicStoragePlace());
//            balanceManagerInstance.cleanCurrentFileQueue();
//            Logger.info("cleanedList: " + newFiles);
//            Logger.info("hello from the other side" + balanceManagerInstance.getCurrentAvailableStorageNumber());
            balanceManagerInstance.sendFilesToStorages();
            balanceManagerInstance.printFileQueue();
            balanceManagerInstance.cleanCurrentFileQueue();
        }

    }




}
