package common_config;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApacheCommonConfig {

    public static void main(String[] args) {
        List<String> oneDuplicatedKey = getDuplicatedParameterList("oneDuplicatedParameter.cfg");
        for (String key: oneDuplicatedKey) {
            System.out.println(key);
        }

        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File("config.properties"));
            String dbHost = config.getString("database.host");
            int dbPort = config.getInt("database.port");
            System.out.println(dbHost);
            System.out.println(dbPort);
        } catch (ConfigurationException cex) {
            // Something went wrong
        }
    }

    // TODO. 支持获取同一个key的多个value值
    public static List<String> getDuplicatedParameterList(String fileLocation) {
        Configuration config = loadConfiguration(fileLocation);
        Iterator<String> keyIterator = config.getKeys();
        List<String> duplicatedParameterList = new ArrayList<>();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            String[] stringArray = config.getStringArray(key);
            if (stringArray.length > 1) {
                duplicatedParameterList.add(key);
            }
        }
        return duplicatedParameterList;
    }

    // 基于文件的Config配置, 通过文件路径进行加载
    // PropertiesConfiguration supports loading a properties file with multiple entries with the same key
    public static Configuration loadConfiguration(String fileLocation) {
        PropertiesBuilderParameters parameters = new Parameters().properties();
        parameters.setFileName(fileLocation);
        try {
            return new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
                    .configure(parameters)
                    .getConfiguration();
        } catch (ConfigurationException ex) {
            throw new RuntimeException(ex);
        }
    }
}
