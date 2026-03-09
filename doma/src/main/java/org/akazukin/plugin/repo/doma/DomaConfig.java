package org.akazukin.plugin.repo.doma;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource;
import org.seasar.doma.jdbc.tx.LocalTransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class DomaConfig implements Config {
    private static final int BATCH_SIZE = 500;
    DBConfig config;
    Dialect dialect;
    TransactionManager transactionManager;
    LocalTransactionDataSource dataSource;
    IJdbcLogger jdbcLogger;
    IUnknownColumnHandler unknownColumnHandler;

    public DomaConfig(final DBConfig config) {
        this.config = config;

        try {
            log.info("Loading JDBC Driver: " + config.getDBDriver().getName() + " (" + config.getDBDriver().getDriverClassName() + ")");
            Class.forName(config.getDBDriver().getDriverClassName());
            log.info("Loaded JDBC Driver: " + config.getDBDriver().getName());
        } catch (final ClassNotFoundException e) {
            log.error("Failed to load JDBC Driver: " + config.getDBDriver().getName(), e);
            throw new RuntimeException(e);
        }

        this.dialect = new MysqlDialect();
        this.dataSource = new LocalTransactionDataSource(
                "jdbc:" + config.getDBDriver().getDriverProtocol() + "://" + config.getDBAuthority() + "/" + config.getDBPath()
                        + (config.getDBArgs() != null ? "?" + config.getDBArgs() : ""),
                config.getDBUsername(),
                config.getDBPassword()
        );
        this.jdbcLogger = new IJdbcLogger();
        this.transactionManager = new LocalTransactionManager(this.dataSource.getLocalTransaction(this.getJdbcLogger()));
        this.unknownColumnHandler = new IUnknownColumnHandler();
    }

    @Override
    public int getBatchSize() {
        return BATCH_SIZE;
    }
}
