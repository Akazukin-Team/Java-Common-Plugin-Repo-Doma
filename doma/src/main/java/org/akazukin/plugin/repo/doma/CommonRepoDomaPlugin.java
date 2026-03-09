package org.akazukin.plugin.repo.doma;

import org.akazukin.loader.api.ILoader;
import org.akazukin.loader.api.context.IPlugin;
import org.akazukin.plugin.repo.common.IRepositoryRegistry;
import org.akazukin.plugin.repo.common.RepositoryRegistry;

public class CommonRepoDomaPlugin implements IPlugin {
    final ILoader loader;
    IRepositoryRegistry<?> repoMgr;

    public CommonRepoDomaPlugin(final ILoader loader) {
        this.loader = loader;
    }

    @Override
    public void onLoad() {
        this.repoMgr = new RepositoryRegistry<>();
    }
}
