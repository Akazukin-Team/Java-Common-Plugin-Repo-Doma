package org.akazukin.plugin.repo.doma;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.akazukin.plugin.repo.common.ITransactionManager;
import org.seasar.doma.jdbc.tx.TransactionManager;

import java.util.function.Supplier;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DomaTransactionManager implements ITransactionManager {
    TransactionManager transactionMgr;

    public DomaTransactionManager(final TransactionManager transactionMgr) {
        this.transactionMgr = transactionMgr;
    }

    @Override
    public void requiresNew(final Runnable runnable) {
        this.transactionMgr.requiresNew(runnable);
    }

    @Override
    public <T> T requiresNew(final Supplier<T> supplier) {
        return this.transactionMgr.requiresNew(supplier);
    }
}
