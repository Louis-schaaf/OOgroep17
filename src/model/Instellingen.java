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
            if (properties == null) {
                result = createProperties().getProperty("load");
            } else {
                result = properties.getProperty("load");
            }
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
            if (properties == null) {
                result = createProperties().getProperty("korting");
            } else {
                result = properties.getProperty("korting");
            }
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void setLoad(String string) {
        try {
            if (properties == null) {
                createProperties().setProperty("load", string);
            } else {
                properties.setProperty("load", string);
            }
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setKorting(String string) {
        try {
            if (properties == null) {
                createProperties().setProperty("korting", string);
            } else {
                properties.setProperty("korting", string);
            }
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write() {
        try {
            if (properties == null) {
                createProperties();
            }
            FileOutputStream os = new FileOutputStream(file);
            properties.storeToXML(os, null);
            os.close();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
