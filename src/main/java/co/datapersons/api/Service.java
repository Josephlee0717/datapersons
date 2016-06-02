package co.datapersons.api;

public interface Service extends LifeCycle
{
	void start();
	boolean isStop();
	Object doService(Object params);
	void stop();
}
