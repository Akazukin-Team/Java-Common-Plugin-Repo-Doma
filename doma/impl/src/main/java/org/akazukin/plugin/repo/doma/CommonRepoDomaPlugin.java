package org.akazukin.plugin.repo.doma;

import lombok.Getter;
import lombok.SneakyThrows;
import org.akazukin.loader.api.ILoader;
import org.akazukin.loader.api.context.IPluginContext;
import org.akazukin.plugin.config.IConfigPlugin;
import org.akazukin.plugin.config.config.PersistableConfigDataManager;
import org.akazukin.plugin.config.config.data.IConfigDataManager;
import org.akazukin.plugin.config.config.data.IPersistableConfigDataManager;
import org.akazukin.plugin.repo.common.ICommonRepoPlugin;
import org.akazukin.plugin.repo.common.RepositoryManager;
import org.akazukin.plugin.repo.common.repo.IRepository;
import org.akazukin.plugin.repo.doma.config.DatabaseConfigData;
import org.akazukin.plugin.repo.doma.config.DatabaseConfigStorageManager;
import org.akazukin.plugin.repo.doma.driver.DriverManager;
import org.akazukin.plugin.repo.doma.driver.DriverRegistry;
import org.akazukin.plugin.repo.doma.driver.IDriver;
import org.akazukin.plugin.repo.doma.driver.MariaDriver;
import org.akazukin.plugin.repo.doma.driver.MysqlDriver;
import org.akazukin.service.registry.IServiceRegistry;
import org.akazukin.service.registry.SingleServiceRegistry;
import org.jetbrains.annotations.NotNull;

public class CommonRepoDomaPlugin implements ICommonRepoDomaPlugin {
    final ILoader loader;
    @Getter
    DomaConfig config;
    IServiceRegistry<IConfigDataManager<?>> cfgStore;
    IServiceRegistry<IDriver> driverStore;
    @Getter
    DriverManager driverMgr;

    public CommonRepoDomaPlugin(final ILoader loader) {
        this.loader = loader;
    }

    @Override
    @SneakyThrows
    public void onLoad() {
        this.driverMgr = new DriverManager();
        this.driverStore = new DriverRegistry();
        this.driverMgr.registerRegistry(this.driverStore);

        this.driverStore.registerService(new MariaDriver());
        this.driverStore.registerService(new MysqlDriver());

        {
            this.cfgStore = new SingleServiceRegistry<>((Class<IConfigDataManager<?>>) (Object) IConfigDataManager.class);

            @NotNull final IPluginContext comRepoDomaCtx = this.loader.getPluginResolver().findById("common-repo-doma");

            final DatabaseConfigStorageManager dataMgr = new DatabaseConfigStorageManager(this.driverMgr, comRepoDomaCtx.getPluginDir().resolve("database.properties"));
            final IPersistableConfigDataManager<DatabaseConfigData> cfgDataMgr = new PersistableConfigDataManager<>(DatabaseConfigData.class, DatabaseConfigData::new, dataMgr);
            this.cfgStore.registerService(cfgDataMgr);

            cfgDataMgr.loadConfig();

            this.config = new DomaConfig(cfgDataMgr.getConfig());
        }
    }

    @Override
    public void onEnable() {
        {
            @NotNull final IPluginContext comCfgCtx = this.loader.getPluginResolver().findById("common-config");
            @NotNull final IConfigPlugin comCfg = (IConfigPlugin) comCfgCtx.getPlugin();

            comCfg.getCfgMgr().registerStore(this.cfgStore);
        }

        {
            @NotNull final IPluginContext comRepoCtx = this.loader.getPluginResolver().findById("common-repo");
            @NotNull final ICommonRepoPlugin comRepo = (ICommonRepoPlugin) comRepoCtx.getPlugin();

            comRepo.setRepoMgr(new RepositoryManager<>((Class<IRepository<?>>) (Object) IRepository.class));
            comRepo.setTxMgr(new DomaTransactionManager(this.config.getTransactionManager()));
        }
    }

    @Override
    public void onDisable() {
        {
            @NotNull final IPluginContext comCfgCtx = this.loader.getPluginResolver().findById("common-config");
            @NotNull final IConfigPlugin comCfg = (IConfigPlugin) comCfgCtx.getPlugin();

            comCfg.getCfgMgr().unregisterStore(this.cfgStore);
        }

        {
            @NotNull final IPluginContext comRepoCtx = this.loader.getPluginResolver().findById("common-repo");
            @NotNull final ICommonRepoPlugin comRepo = (ICommonRepoPlugin) comRepoCtx.getPlugin();

            comRepo.setRepoMgr(null);
            comRepo.setTxMgr(null);
        }
    }
}
