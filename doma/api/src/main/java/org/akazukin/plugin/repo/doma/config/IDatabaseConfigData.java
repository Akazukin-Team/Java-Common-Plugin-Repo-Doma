package org.akazukin.plugin.repo.doma.config;

import org.akazukin.plugin.config.config.data.IConfigData;

public interface IDatabaseConfigData extends IConfigData {
    org.akazukin.plugin.repo.doma.driver.IDriver getDriver();

    void setDriver(org.akazukin.plugin.repo.doma.driver.IDriver driver);

    String getAuthority();

    void setAuthority(String authority);

    String getPath();

    void setPath(String path);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    String getArgs();

    void setArgs(String args);
}
