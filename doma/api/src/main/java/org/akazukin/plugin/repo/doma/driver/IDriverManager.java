package org.akazukin.plugin.repo.doma.driver;

import org.akazukin.service.manager.IServiceStore;

public interface IDriverManager {
    void registerRegistry(IServiceStore<IDriver> reg);

    void unregisterRegistry(IServiceStore<IDriver> reg);

    IDriver getDriver(String id);
}
