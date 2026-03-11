package org.akazukin.plugin.repo.doma.driver;

import org.akazukin.service.registry.SingleServiceRegistry;

public class DriverRegistry extends SingleServiceRegistry<IDriver> implements IDriverRegistry {
    public DriverRegistry() {
        super(IDriver.class);
    }
}
