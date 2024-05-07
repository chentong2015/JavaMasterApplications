package common_config_xml;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.util.List;

// 支持对XML配置文件的加载, 根据路径获取标签信息
public class ApacheCommonConfigXml {

    public static void main(String[] args) {
        Configurations configs = new Configurations();
        try {
            XMLConfiguration config = configs.xml("config.xml");
            String stage = config.getString("processing[@stage]");
            List<String> paths = config.getList(String.class, "processing.paths.path");
            String secondPath = config.getString("processing.paths.path(1)");

            System.out.println(stage);
            System.out.println(secondPath);
        } catch (ConfigurationException cex) {
            // Something went wrong
        }
    }
}
