package org.akazukin.plugin.repo.doma.driver;

import org.seasar.doma.jdbc.dialect.Dialect;

public interface IDriver {
    String getName();

    String getId();

    String getDriverProtocol();

    String getDriverClassName();

    Dialect getDialect();
}
