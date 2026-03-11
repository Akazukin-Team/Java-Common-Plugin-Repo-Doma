package org.akazukin.plugin.repo.doma.driver;

import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;

public class MysqlDriver implements IDriver {
    @Override
    public String getName() {
        return "MySQL";
    }

    @Override
    public String getId() {
        return "mysql";
    }

    @Override
    public String getDriverProtocol() {
        return "mysql";
    }

    @Override
    public String getDriverClassName() {
        return "com.mysql.cj.jdbc.Driver";
    }

    @Override
    public Dialect getDialect() {
        return new MysqlDialect();
    }
}
