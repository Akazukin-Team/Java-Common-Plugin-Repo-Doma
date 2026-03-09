package org.akazukin.plugin.repo.doma;

import org.akazukin.plugin.repo.doma.driver.IDriver;

public interface DBConfig {
    IDriver getDBDriver();

    String getDBAuthority();

    String getDBPath();

    String getDBUsername();

    String getDBPassword();

    String getDBArgs();
}
