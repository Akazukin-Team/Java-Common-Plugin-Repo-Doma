package org.akazukin.plugin.repo.doma.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.akazukin.plugin.config.config.data.IDataManager;
import org.akazukin.plugin.repo.doma.driver.IDriverManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DatabaseDataManager implements IDataManager<DatabaseConfigData> {
    IDriverManager driverMgr;
    Path path;

    public DatabaseDataManager(final IDriverManager driverMgr, final Path path) {
        this.driverMgr = driverMgr;
        this.path = path;
    }

    @Override
    public void loadConfig(final DatabaseConfigData data) throws IOException {
        final Properties props = new Properties();
        try (final InputStream fis = Files.newInputStream(this.path)) {
            props.load(fis);
        }

        data.setDriver(this.driverMgr.getDriver(props.getProperty("driver")));
        data.setAuthority(props.getProperty("authority"));
        data.setPath(props.getProperty("path"));
        data.setUsername(props.getProperty("username"));
        data.setPassword(props.getProperty("password"));
        data.setArgs(props.getProperty("args"));
    }

    @Override
    public void saveConfig(final DatabaseConfigData data) throws IOException {
        final Properties props = new Properties();
        props.setProperty("driver", data.getDriver().getId());
        props.setProperty("authority", data.getAuthority());
        props.setProperty("path", data.getPath());
        props.setProperty("username", data.getUsername());
        props.setProperty("password", data.getPassword());
        props.setProperty("args", data.getArgs());

        try (final OutputStream os = Files.newOutputStream(this.path)) {
            props.store(os, null);
        }
    }
}
