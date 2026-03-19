package org.akazukin.plugin.repo.doma;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.akazukin.plugin.repo.common.RepositoryRegistry;
import org.akazukin.plugin.repo.common.repo.IRepository;
import org.jetbrains.annotations.NotNull;
import org.seasar.doma.jdbc.Config;

import java.util.function.Function;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DomaRepositoryRegistry<T extends IRepository<?>> extends RepositoryRegistry<T> implements IDomaRepositoryRegistry<T> {
    @Getter
    Config config;

    public DomaRepositoryRegistry(@NotNull final Class<T> repoType, final Config config) {
        super(repoType);
        this.config = config;
    }

    @Override
    public <U extends T> void registerRepositoryLambda(final Class<U> repoType, final Function<Config, U> supplier) {
        super.registerService(repoType, supplier.apply(this.config));
    }
}
