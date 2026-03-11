package org.akazukin.plugin.repo.doma.driver;

import org.akazukin.service.registry.IServiceRegistry;

public interface IDriverManager {
    void registerRegistry(IServiceRegistry<IDriver> reg);

    void unregisterRegistry(IServiceRegistry<IDriver> reg);

    IDriver getDriver(String id);
}
