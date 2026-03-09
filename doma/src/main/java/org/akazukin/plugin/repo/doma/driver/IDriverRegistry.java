package org.akazukin.plugin.repo.doma.driver;

public interface IDriverRegistry {
    void registerDriver(IDriver driver);

    void unregisterDriver(IDriver driver);
}
