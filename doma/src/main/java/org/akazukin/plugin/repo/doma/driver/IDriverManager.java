package org.akazukin.plugin.repo.doma.driver;

public interface IDriverManager {
    void registerRegistry(IDriverRegistry reg);

    void unregisterRegistry(IDriverRegistry reg);

    IDriver getDriver(String id);
}
