package org.akazukin.plugin.repo.doma.driver;

import org.akazukin.service.manager.holder.ServiceManagerHolder;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class DriverManager extends ServiceManagerHolder<IDriver> implements IDriverManager {
    public DriverManager() {
        super(IDriver.class);
    }

    @Override
    public void registerRegistry(final IDriverRegistry reg) {
        if (!(reg instanceof DriverRegistry)) {
            throw new IllegalArgumentException("The registry must be an instance: " + DriverRegistry.class.getName());
        }

        super.registerStore((DriverRegistry) reg);
    }

    @Override
    public void unregisterRegistry(final IDriverRegistry reg) {
        if (!(reg instanceof DriverRegistry)) {
            throw new IllegalArgumentException("The registry must be an instance: " + DriverRegistry.class.getName());
        }

        super.unregisterStore((DriverRegistry) reg);
    }

    @Override
    @Nullable
    public IDriver getDriver(final String id) {
        return Arrays.stream(this.getAllServices())
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
