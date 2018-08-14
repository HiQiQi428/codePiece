package org.luncert.modelMethod;

public abstract class HummerModel {

    public abstract void start();

    public abstract void stop();

    public abstract void alarm();
    
    public abstract void engineBoom();

    /**
     * 模板方法
     */
	public void run() {
        start();
        engineBoom();
        alarm();
        stop();
	}
    
}