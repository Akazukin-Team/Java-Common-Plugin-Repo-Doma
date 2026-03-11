package org.akazukin.plugin.repo.doma;

import lombok.extern.slf4j.Slf4j;
import org.seasar.doma.jdbc.UnknownColumnHandler;
import org.seasar.doma.jdbc.entity.EntityType;
import org.seasar.doma.jdbc.query.Query;

import java.util.function.Supplier;

@Slf4j
public class UnknownColumnHandlerImpl implements UnknownColumnHandler {
    @Override
    public void handle(final Query query, final EntityType<?> entityType, final String unknownColumnName, final Supplier<String> informationSupplier) {
        log.error("Unknown column was found  | Class:" + query.getClassName() + "  | Method:" + query.getMethodName() + "  | Column:" + unknownColumnName);
    }
}
