package org.akazukin.plugin.repo.doma.driver;

import org.akazukin.service.manager.holder.ServiceManagerHolder;
import org.akazukin.service.registry.IServiceRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class DriverManager extends ServiceManagerHolder<IDriver> implements IDriverManager {
    public DriverManager() {
        super(IDriver.class);
    }

    @Override
    public void registerRegistry(final IServiceRegistry<IDriver> reg) {
        super.registerStore(reg);
    }

    @Override
    public void unregisterRegistry(final IServiceRegistry<IDriver> reg) {
        super.unregisterStore(reg);
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
