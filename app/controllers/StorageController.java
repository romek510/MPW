package controllers;

import org.h2.store.fs.FileUtils;
import play.Logger;
import play.mvc.Result;
import utils.CommonUtil;
import utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by mbajdek on 29.05.2017.
 */
public class StorageController extends BasicAbstractController {

    public StorageController(){
        Logger.error("elo");
    }

    public Result showFilesForCurrentUser(String user){
        String rootPlace = getValueForConfigurationKey(CommonUtil.ROOT_PLACE) + "/" + user;
        List<String> fileNames = new ArrayList<>();

        if(!FileUtils.exists(rootPlace)){
            Logger.error("directory does not exist, creating directory " + user);
            FileUtils.createDirectory(rootPlace);
        }
        List<File> filesList = FileUtil.getFileListForGivenPlace(rootPlace);
        for (File file : filesList) {
            fileNames.add(file.getName());
            Logger.info("File: " + file.getName());
        }
        return ok(views.html.users_filelist.render(fileNames,user));
    }
}
