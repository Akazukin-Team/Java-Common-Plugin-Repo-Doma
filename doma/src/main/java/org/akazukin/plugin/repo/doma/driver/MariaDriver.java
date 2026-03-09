package org.akazukin.plugin.repo.doma.driver;

import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;

public class MariaDriver implements IDriver {
    @Override
    public String getName() {
        return "MariaDB";
    }

    @Override
    public String getId() {
        return "mariadb";
    }

    @Override
    public String getDriverProtocol() {
        return "mariadb";
    }

    @Override
    public String getDriverClassName() {
        return "org.mariadb.jdbc.Driver";
    }

    @Override
    public Dialect getDialect() {
        return new MysqlDialect();
    }
}
