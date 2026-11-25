package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionProperty {
    public static final String CONFIG_NAME = "config.properties";
    public static final Properties PROPERTY_CONFIG = new Properties();

    public ConnectionProperty() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        // Пытаемся загрузить файл
        InputStream is = classLoader.getResourceAsStream("config/" + CONFIG_NAME);

        // Если файл не найден, выбрасываем понятную ошибку
        if (is == null) {
            throw new IOException("Файл " + CONFIG_NAME + " не найден в classpath! Проверьте, лежит ли он в папке src/main/resources/config/");
        }

        PROPERTY_CONFIG.load(is);
    }

    public static String getProperty(String property) {
        return PROPERTY_CONFIG.getProperty(property);
    }
}