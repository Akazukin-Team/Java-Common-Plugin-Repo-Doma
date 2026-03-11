package org.akazukin.plugin.repo.doma;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.akazukin.plugin.repo.doma.config.DatabaseConfigData;
import org.jetbrains.annotations.UnknownNullability;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class DomaConfig implements Config {
    private static final int BATCH_SIZE = 500;
    DatabaseConfigData config;
    Dialect dialect;
    TransactionManager transactionManager;
    LocalTransactionDataSource dataSource;
    JdbcLoggerImpl jdbcLogger;
    UnknownColumnHandlerImpl unknownColumnHandler;

    public DomaConfig(final @UnknownNullability DatabaseConfigData config) {
        this.config = config;

        try {
            log.info("Loading JDBC Driver: " + config.getDriver().getName() + " (" + config.getDriver().getDriverClassName() + ")");
            Class.forName(config.getDriver().getDriverClassName());
            log.info("Loaded JDBC Driver: " + config.getDriver().getName());
        } catch (final ClassNotFoundException e) {
            log.error("Failed to load JDBC Driver: " + config.getDriver().getName(), e);
            throw new RuntimeException(e);
        }

        this.dialect = config.getDriver().getDialect();
        this.dataSource = new LocalTransactionDataSource(
                "jdbc:" + config.getDriver().getDriverProtocol() + "://" + config.getAuthority() + "/" + config.getPath()
                        + (config.getArgs() != null ? "?" + config.getArgs() : ""),
                config.getUsername(),
                config.getPassword()
        );
        this.jdbcLogger = new JdbcLoggerImpl();
        this.transactionManager = new LocalTransactionManager(this.dataSource.getLocalTransaction(this.getJdbcLogger()));
        this.unknownColumnHandler = new UnknownColumnHandlerImpl();
    }

    @Override
    public int getBatchSize() {
        return BATCH_SIZE;
    }
}
