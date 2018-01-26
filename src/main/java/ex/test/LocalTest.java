package ex.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class LocalTest {

    public static void main(String[] args) {


        test_java_class_path();

        testClassLoad();

        test_get_resource_bundle();

    }

    public static void test_get_resource_bundle() {

        ResourceBundle defaultResourceBundle = ResourceBundle.getBundle("org.apache.catalina.connector.http.LocalStrings", Locale.US);

        String value = defaultResourceBundle.getString("httpConnector.alreadyInitialized");

        System.out.println(value);
    }

    public static void testClassLoad() {

        String fileName = "org/apache/catalina/connector/http/LocalStrings.properties";

//        String fileName = "aaa";

        URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);

        InputStream inputStream = null;
        try {
            inputStream = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            System.out.println(properties.getProperty("httpConnector.alreadyInitialized"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("over");

    }

    public static void test_java_class_path() {

        String classPath = System.getProperty("java.class.path");

        String[] classPaths = classPath.split(":");

        for (String path : classPaths) {
            System.out.println(path);
        }

    }

}
