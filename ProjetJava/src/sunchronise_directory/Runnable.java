package sunchronise_directory;

public class Timer implements Runnable{
	private int delay =1000;
	private boolean alive = true;
    public void start() { new Thread(this).start(); }
	public void stop()  { alive = false; }
	public void run() {
	  while (alive ) {
	    try {
	      System.out.println("Hello");
	      Thread.sleep( delay );
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    } } }
}

