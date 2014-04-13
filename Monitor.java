import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Monitor 
{
	public HashMap<String,Device> deviceMap;
	public Scanner input = new Scanner(System.in);
	public InputThread inputThread = new InputThread();
	public boolean empty = false;
	StateEnum stateEnum;
	

	// Constructor for the Monitor class. Takes the HashMap of devices as its sole parameter,
	// creates a new thread so that the monitor may be stopped to add/check devices, and ensures
	// that monitoring is stopped if the client enters "stop".
	public Monitor(HashMap<String,Device> deviceMap)
	{
		this.deviceMap = deviceMap;
		Thread t = new Thread(inputThread);
		t.start();
		while (!input.next().equals("stop") && !empty);
		inputThread.keepRunning = false;
		t.interrupt();
	}

	// Changes the state of a single device, where int numStates is the total number of states
	// that device has minus one. TODO: remove numStates and just check the type of device?
	public void changeState(Device device, int numStates) 
	{
		int nextState;
		nextState = randInt(0, numStates);
		    
		if(nextState == StateEnum.OFF.ordinal()) {
			device.powerOff();
		} else if (nextState == StateEnum.ON.ordinal()) {
			device.powerOn();
		} else if (nextState == StateEnum.SLEEP.ordinal()) {
			if (device instanceof Computer) {
				((Computer) device).sleep();			
			} else if (device instanceof Projector) {
				((Projector) device).sleep();
			}
		}
		
	}
	
	// Returns a random integer from min to max, inclusive. We use this for both the sleep timers
	// and the nextState int within changeState().
	public static int randInt(int min, int max) 
	{
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value, so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	
	
	// Nested inner class InputThread. Allows for input to be taken and interpreted while monitor
	// mode is running. Note that the name "InputThread" is technically a misnomer, because it
	// itself is not a thread, but rather code that a thread can execute.
	class InputThread implements Runnable 
	{
	    volatile boolean keepRunning = true;

	    public void run() {
	        System.out.println("----------------- ENTERING MONITOR MODE ----------------\n");
	        
	        while (keepRunning) {
	            try {
		            try {
		            	Random generator = new Random();
		    			Object[] values = deviceMap.values().toArray();
		    			Device randomDevice = (Device) values[generator.nextInt(values.length)];
		    			
		    			if (randomDevice instanceof Computer || randomDevice instanceof Projector) {
		    				changeState(randomDevice, 2);
		    			} else if (randomDevice instanceof DVDPlayer){
		    				changeState(randomDevice, 1);
		    			}
		            	
				        // Sending the actual thread of execution to sleep x milliseconds.
				        Thread.sleep(randInt(1 * 1000, 3 * 1000));
				    } catch (InterruptedException ie) {}
	            } catch (IllegalArgumentException iae) {
	            	System.err.println("Error: devices must be added before the monitor can be run. Enter"
	            			+ " anything to continue.");
	            	empty = true;
	            	return;
	            }
	        }
	    }
	    
	}

}