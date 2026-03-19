package org.akazukin.plugin.repo.doma;

import org.akazukin.plugin.repo.common.IRepositoryRegistry;
import org.akazukin.plugin.repo.common.repo.IRepository;
import org.seasar.doma.jdbc.Config;

import java.util.function.Function;

public interface IDomaRepositoryRegistry<T extends IRepository<?>> extends IRepositoryRegistry<T> {
    <U extends T> void registerRepositoryLambda(Class<U> repoType, Function<Config, U> supplier);
}
