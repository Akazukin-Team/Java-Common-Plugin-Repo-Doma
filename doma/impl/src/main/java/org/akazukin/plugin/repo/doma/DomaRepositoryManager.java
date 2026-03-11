package org.akazukin.plugin.repo.doma;

import org.akazukin.plugin.repo.common.RepositoryManager;
import org.akazukin.plugin.repo.common.repo.IRepository;
import org.seasar.doma.jdbc.Config;

public class DomaRepositoryManager extends RepositoryManager<IRepository<?>> {
    public DomaRepositoryManager(final Config config) {
        super((Class<IRepository<?>>) (Object) IRepository.class);
    }
}
