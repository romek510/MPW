package managers;

import play.Logger;
import utils.CommonUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;


public class FileObserver implements Runnable {

    WatchService watcher = null;
    protected static FileObserver fileObserverInstance = null;
    protected static BalanceManager balanceManagerInstance = null;

    protected FileObserver() {
        try {
            watcher = FileSystems.getDefault().newWatchService();
            //Path dir = Paths.get(CommonUtil.getPlaceToObservePatch());
            Path dir = Paths.get("/Users/mbajdek/Desktop/place");
            dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        } catch (IOException ioe) {
            Logger.error("IOException something went wrong");
        }
        balanceManagerInstance = BalanceManager.getBalanceManagerInstance();
    }

    public synchronized static FileObserver getFileObserverInstance() {
        if (fileObserverInstance == null) {
            fileObserverInstance = new FileObserver();
        }
        return fileObserverInstance;
    }

    @Override
    public void run() {
        while (true) {
            WatchKey key;
            try {
                // wait for a key to be available
                key = watcher.take();
            } catch (InterruptedException ex) {
                return;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                // get event type
                WatchEvent.Kind<?> kind = event.kind();

                // get file name
                @SuppressWarnings("unchecked")
                WatchEvent<Path> ev = (WatchEvent<Path>) event;

                if (kind == OVERFLOW) {
                    continue;
                } else if (kind == ENTRY_CREATE) {
                    Path fileName = ev.context();

                    System.out.println(kind.name() + ": " + fileName.toString());
                    Logger.info(">>>>>>>>>> " + fileName);
                    balanceManagerInstance.addFileToQueue(new File(CommonUtil.getRootPlace() + "/" + fileName.getFileName()));

                    // process create event

                } else if (kind == ENTRY_DELETE) {

                    // process delete event

                } else if (kind == ENTRY_MODIFY) {

                    // process modify event

                }
            }

            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }

    }
}
