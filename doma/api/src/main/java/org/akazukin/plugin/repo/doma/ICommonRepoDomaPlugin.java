package org.akazukin.plugin.repo.doma;

import org.akazukin.loader.api.context.IPlugin;
import org.akazukin.plugin.repo.doma.driver.IDriverManager;
import org.seasar.doma.jdbc.Config;

public interface ICommonRepoDomaPlugin extends IPlugin {
    Config getConfig();

    IDriverManager getDriverMgr();
}
