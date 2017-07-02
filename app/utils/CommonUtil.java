package utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import managers.BalanceManager;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * Created by mbajdek on 29.05.2017.
 */
public class CommonUtil {

    private static BalanceManager balanceManagerInstance = BalanceManager.getBalanceManagerInstance();

    public static final String ROOT_PLACE= "root.place";
    public static final String NUMBER_OF_STORAGES="root.numberOfPlaces";
    private static final String BASIC_STORAGE_PLACE="root.basicPlace";

    static Config config = ConfigFactory.load();


    private static String getValueForConfigurationKey(String key){
        String toReturn = config.getString(key);
        if(StringUtils.isBlank(toReturn)){
            toReturn = StringUtils.EMPTY;
        }
        return toReturn;
    }
    
        public static String getPlaceToObservePatch(){
        return getValueForConfigurationKey(ROOT_PLACE);
    }

    public static String getNumberOfStorages(){
        return getValueForConfigurationKey(NUMBER_OF_STORAGES);
    }

    public static String getBasicStoragePlace(){
        return getValueForConfigurationKey(BASIC_STORAGE_PLACE);
    }

    public static String getRootPlace(){
        return getValueForConfigurationKey(ROOT_PLACE);
    }

    public static File getCurentAvailableDirectory(){
        return new File(getBasicStoragePlace() + balanceManagerInstance.getCurrentAvailableStorageNumber());
    }
    
    }
