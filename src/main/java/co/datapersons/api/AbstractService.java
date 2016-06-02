package co.datapersons.api;

public abstract class AbstractService implements Service
{
	private boolean isStop = true;

	public AbstractService(){
		init();
	}
	
	public void init()
	{
		this.start();
	}

	public void start()
	{
		isStop = false;
	}

	public boolean isStop()
	{
		return this.isStop;

	}

	public abstract Object doService(Object params);

	public void stop()
	{
		this.isStop = true;
	}

	public void destory()
	{

	}

}
