package org.luncert.mbean;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.luncert.ExceptionUtil;

public class DBSource implements DBSourceMBean {

    private DataSource ds;
    
    private boolean autoCommit = false;

    private void setDataSource(String name) {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(name);
        } catch(Exception e) { ExceptionUtil.handle(e); }
    }

    public DBSource(String jndiName) { setDataSource(jndiName); }

    @Override
    public void resetDataSource(String name) { setDataSource(name); }

    @Override
    public Connection requireConnection() {
        Connection conn = null;
        try {
            conn = ds.getConnection();
            conn.setAutoCommit(autoCommit);
            return conn;
        } catch (Exception e) {
            ExceptionUtil.handle(e);
            return null;
        }
    }

    @Override
    public boolean getAutoCommit() { return autoCommit; }

    @Override
    public void setAutoCommit(boolean autoCommit) { this.autoCommit = autoCommit; }
    
}