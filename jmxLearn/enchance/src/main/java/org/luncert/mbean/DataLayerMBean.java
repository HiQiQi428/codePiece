package org.luncert.mbean;

public interface DataLayerMBean {

    public boolean insertData( Object data );

    public boolean updateData( Object data );

    public boolean deleteData( Object data );

    public boolean retrieveData( Object data );

}