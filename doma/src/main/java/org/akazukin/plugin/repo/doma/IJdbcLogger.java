package org.akazukin.plugin.repo.doma;

import lombok.extern.slf4j.Slf4j;
import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.Sql;
import org.seasar.doma.jdbc.SqlExecutionSkipCause;

import java.sql.SQLException;

@Slf4j
public final class IJdbcLogger implements JdbcLogger {
    @Override
    public void logDaoMethodEntering(final String callerClassName, final String callerMethodName, final Object... parameters) {
        log.trace("Start dao  | Class:" + callerClassName + "  | Method:" + callerMethodName);
    }

    @Override
    public void logDaoMethodExiting(final String callerClassName, final String callerMethodName, final Object result) {
        log.trace("Ended dao  | Class:" + callerClassName + "  | Method:" + callerMethodName);
    }

    @Override
    public void logDaoMethodThrowing(final String callerClassName, final String callerMethodName, final RuntimeException e) {
        log.debug("Throwing from dao  | Class:" + callerClassName + "  | Method:" + callerMethodName, e);
    }

    @Override
    public void logSqlExecutionSkipping(final String callerClassName, final String callerMethodName, final SqlExecutionSkipCause cause) {
        log.debug("Skipped sql  | Cause:" + cause.name() + "  | Class:" + callerClassName + "  | Method:" + callerMethodName);
    }

    @Override
    public void logSql(final String callerClassName, final String callerMethodName, final Sql<?> sql) {
        log.debug("Execute the sql  | Class:" + callerClassName + "  | Method:" + callerMethodName + "  | SQL:\n" + sql.getRawSql());
    }

    @Override
    public void logTransactionBegun(final String callerClassName, final String callerMethodName, final String transactionId) {
        log.trace("Begun transaction  | TransID:" + transactionId + "  | Class:" + callerClassName + "  | Method:" + callerMethodName);
    }

    @Override
    public void logTransactionEnded(final String callerClassName, final String callerMethodName, final String transactionId) {
        log.trace("Ended transaction  | TransID:" + transactionId + "  | Class:" + callerClassName + "  | Method:" + callerMethodName);
    }

    @Override
    public void logTransactionCommitted(final String callerClassName, final String callerMethodName, final String transactionId) {
        log.trace("Committed transaction  | TransID:" + transactionId + "  | Class:" + callerClassName + "  | Method:" + callerMethodName);
    }

    @Override
    public void logTransactionSavepointCreated(final String callerClassName, final String callerMethodName, final String transactionId, final String savepointName) {
        log.debug("Created transaction save point  | TransID:" + transactionId + "  | Point:" + savepointName + "  | Class:" + callerClassName + "  | Method:" + callerMethodName);
    }

    @Override
    public void logTransactionRolledback(final String callerClassName, final String callerMethodName, final String transactionId) {
        log.warn("Rolled back transaction  | TransID:" + transactionId + "  | Class:" + callerClassName + "  | Method:" + callerMethodName);
    }

    @Override
    public void logTransactionSavepointRolledback(final String callerClassName, final String callerMethodName, final String transactionId, final String savepointName) {
        log.debug("Rolled back transaction to save point  | TransID:" + transactionId + "  | Point:" + savepointName + "  | Class:" + callerClassName + "  | Method:" + callerMethodName);
    }

    @Override
    public void logTransactionSavepointReleased(final String callerClassName, final String callerMethodName, final String transactionId, final String savepointName) {
        log.trace("Released transaction save point  | TransID:" + transactionId + "  | Point:" + savepointName + "  | Class:" + callerClassName + "  | Method:" + callerMethodName);
    }

    @Override
    public void logTransactionRollbackFailure(final String callerClassName, final String callerMethodName, final String transactionId, final SQLException e) {
        log.error("Failed rolling back transaction  | TransID:" + transactionId + "  | Class:" + callerClassName + "  | Method:" + callerMethodName, e);
    }

    @Override
    public void logAutoCommitEnablingFailure(final String callerClassName, final String callerMethodName, final SQLException e) {
        log.warn("Failed enabling auto commit  | Class:" + callerClassName + "  | Method:" + callerMethodName, e);
    }

    @Override
    public void logTransactionIsolationSettingFailure(final String callerClassName, final String callerMethodName, final int isolationLevel, final SQLException e) {
        log.warn("Failed setting transaction isolation  | IsolationLv:" + isolationLevel + "  | Class:" + callerClassName + "  | Method:" + callerMethodName, e);
    }

    @Override
    public void logConnectionClosingFailure(final String callerClassName, final String callerMethodName, final SQLException e) {
        log.error("Failed closing connection  | Class:" + callerClassName + "  | Method:" + callerMethodName, e);
    }

    @Override
    public void logStatementClosingFailure(final String callerClassName, final String callerMethodName, final SQLException e) {
        log.warn("Failed closing statement  | Class:" + callerClassName + "  | Method:" + callerMethodName, e);
    }

    @Override
    public void logResultSetClosingFailure(final String callerClassName, final String callerMethodName, final SQLException e) {
        log.warn("Failed closing result set  | Class:" + callerClassName + "  | Method:" + callerMethodName, e);
    }
}
