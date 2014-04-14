import java.util.HashMap;

/*
 * DeviceMonitor is a program used to monitor devices of three types (DVD player, computer, and projector),
 * which are all implementations of the same Device interface. Clients have the ability to add, remove, list, 
 * check, and monitor the state of devices. State changes in devices are pseudorandom, and the states which
 * a device can transition to may type to type. The DeviceMonitor has two basic modes, input mode and monitor
 * mode; the client can transition from one to the other at will. This program is best viewed in a UNIX
 * terminal longer than it is wide, with a width of at least 74 characters.
 * 
 */

public class DeviceMonitor 
{

	public static void main (String[] args) throws InterruptedException
	{
		
		StateEnum stateEnum = StateEnum.OFF;	// By default, all added devices begin in OFF state
		HashMap<String, Device> deviceMap = new HashMap<String, Device>(); // Storage for all devices
		DeviceLoader loader = new DeviceLoader(deviceMap, stateEnum); // Console input/output
		
		// ASCII header to the program
		loader.asciiTitle();
		
		// The main user input/output loop
		loader.initLoop();
		
	}

}

