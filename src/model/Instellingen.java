package model;

import jxl.read.biff.BiffException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Instellingen extends Properties {
    private final static String file = System.getProperty("user.dir") + "/src/bestanden/settings.xml";
    private volatile static Properties properties;

    public static Properties Properties() throws IOException, BiffException {
        return createProperties();
    }

    public static Properties createProperties() throws IOException, BiffException {
        if (properties == null) {
            synchronized (Properties.class) {
                properties = new Properties();
                InputStream inputStream = new FileInputStream(file);
                properties.loadFromXML(inputStream);
                inputStream.close();
            }
        }
        return properties;
    }

    public static String getLoad() {
        String result = "";
        try {
            result = createProperties().getProperty("load");
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toUpperCase();
    }

    public static String getKorting() {
        String result = "";
        try {
            result = createProperties().getProperty("korting");
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Properties getProperties() throws IOException, BiffException {
        return createProperties();
    }

    public static void updateSettings() throws IOException, BiffException {
        FileOutputStream outputStream = new FileOutputStream(file);
        getProperties().storeToXML(outputStream, null);
        outputStream.close();
    }
}
