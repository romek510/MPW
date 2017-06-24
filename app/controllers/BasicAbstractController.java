package controllers;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.commons.lang3.StringUtils;
import play.mvc.Controller;

/**
 * Created by mbajdek on 29.05.2017.
 */
public abstract class BasicAbstractController extends Controller{

    Config config;

    public BasicAbstractController(){
        config = ConfigFactory.load();
    }

    public String getValueForConfigurationKey(String key){
        String toReturn = config.getString(key);
        if(StringUtils.isBlank(toReturn)){
            toReturn = StringUtils.EMPTY;
        }
        return toReturn;
    }
}
