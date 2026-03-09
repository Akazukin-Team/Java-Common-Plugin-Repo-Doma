package org.akazukin.plugin.repo.doma.driver;

import org.akazukin.service.registry.SingleServiceRegistry;

public class DriverRegistry extends SingleServiceRegistry<IDriver> implements IDriverRegistry {
    public DriverRegistry() {
        super(IDriver.class);
    }

    @Override
    public void registerDriver(final IDriver driver) {
        super.registerService(driver);
    }

    @Override
    public void unregisterDriver(final IDriver driver) {
        super.unregisterService(driver);
    }
}
