package utils;



import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class FileUtil {

    public static List<File> getFileListForGivenPlace(String place){
    List<File> toReturn = new ArrayList<>();
    File folder = new File(place);
    File[] listOfFiles = folder.listFiles();

    for(File file : listOfFiles){
        if(file.isFile()){
            toReturn.add(file);
        }
    }

    return toReturn;
    }
}
