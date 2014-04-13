import java.util.HashMap;

public class DeviceLoader {
	
	public HashMap<String, Device> deviceMap = new HashMap<String, Device>();
	public StateEnum stateEnum = StateEnum.OFF;
	
	// Constructor for the DeviceLoader class, taking the familiar HashMap and a stateEnum, set to OFF 
	// by default, as its parameters.
	public DeviceLoader(HashMap<String, Device> deviceMap, StateEnum stateEnum)
	{
		this.deviceMap = deviceMap;
		this.stateEnum = stateEnum;
	}
	
	// The "big loop" of the program within which virtually all other operations occur. 
	public void initLoop()
	{
		do {
			String action = System.console().readLine("\nWhat would you like to do? Enter \"help\" to "
					+ "review your command options:\n");
			
			if (action.equals("add")) {
				add();
				
			} else if (action.equals("check")) {
				check();
			
			} else if (action.equals("remove")) {
				remove();
				
			} else if (action.equals("monitor")) {
				monitor();
				
			} else if (action.equals("list")) {
				list();
				
			} else if (action.equals("help")) {
				help();
				
			} else if (action.equals("quit")) {
				System.out.println("\nGoodbye!\n");
				break;	
				
			} else {
				System.out.println("\nCommand unrecognized. Try \"help\" to view you command options.\n");
			}
		} while (true);
	}
	
	// Requests a device type and name for said device from the client, then adds the device and its accompanying 
	// name to the HashMap of devices.
	public void add()
	{
		String deviceType = System.console().readLine("What type of device would you like to add to monitor? "
				+ "Enter \"dvdplayer\", \"computer\",  or \"projector\":\n");
		String shortType = deviceType.substring(0, 3).toLowerCase();
		
		if (!shortType.equals("dvd") && !shortType.equals("com") && !shortType.equals("pro")) {
			System.out.println("Error: Invalid device type.\n");
			return;
		}
		
		String name = System.console().readLine("What would you like to name this " + deviceType + "?:\n");
		
		if(shortType.equals("dvd")) {
			DVDPlayer newDevice = new DVDPlayer(name, stateEnum);
			deviceMap.put(name, newDevice);	
		} else if (shortType.equals("com")) {
			Computer newDevice = new Computer(name, stateEnum);
			deviceMap.put(name, newDevice);
		} else if (shortType.equals("pro")) {
			Projector newDevice = new Projector(name, stateEnum);
			deviceMap.put(name, newDevice);
		}
		
		System.out.println(deviceType + " " + name + " will now be monitored.\n");
	}
	
	// Removes a device from the HashMap of devices.
	public void remove()
	{
		String name = System.console().readLine("Name of the device to remove?:\n");
		int initialHashMapSize = deviceMap.size();
		deviceMap.remove(name);
		int newHashMapSize = deviceMap.size();
		
		if (newHashMapSize == initialHashMapSize) {
			System.out.println("No removal occured. Perhaps you typed the name incorrectly?"
					+ " Note that device names are case sensitive!");			
		} else {
			System.out.println("The device " + name + " has been successfully removed.\n");
		}
		
	}
	
	// Checks the current state of a device for which the client supplies the name.
	public void check()
	{	
		String name = System.console().readLine("Name of device to check?:\n"); 
		try {
			deviceMap.get(name).state();
		} catch (NullPointerException np) { 
			System.err.println("Invalid name. Note that device names are case "
					+ "sensitive!\n");
			return;
		}
	}
	
	// Lists the command options for the client.
	public void help()
	{
		System.out.println("\nEnter:\n"
				+ "\"add\" to add a device,\n"
				+ "\"remove\" to remove a device,\n"
				+ "\"check\" to check a device's state,\n"
				+ "\"list\" to list the devices to be monitored,\n"
				+ "\"monitor\" to enter monitor mode, or\n"
				+ "\"quit\" to exit the program.\n");
	}
	
	// Lists the devices which populate the HashMap, specifically their particular device type and 
	// their corresponding key.
	public void list()
	{
		if(deviceMap.size() == 0) {
			System.out.println("There are currently no devices.\n");
		} else {
			System.out.println("\nList of devices\n(Device tape --- name):\n");
			for (String name: deviceMap.keySet())
			{
	            String key = name.toString();
	            String value = deviceMap.get(name).toString();
	            value = value.substring(0,value.indexOf('@'));  
	            System.out.println(value + " --- " + key);
			}
			System.out.println();
		}
		
	}

	// Instantiates a new Monitor, which runs until "stop" is called by the client.
	public void monitor()
	{
		System.out.println("\nNow entering monitor mode. Type \"stop\" at any time to stop"
				+ " the monitor and return to the previous mode. \n");
		Monitor m = new Monitor(deviceMap);
		System.out.println("\n----------------- EXITING MONITOR MODE ----------------");
	}
	
	// An ASCII header for the Monitor program. Formatting will be faulty if terminal width < 74 char.
	public void asciiTitle() 
	{
		try {
			// "Clears" the terminal w/ ASCII escape sequence.
	        final String os = System.getProperty("os.name");
	        if (! os.contains("Windows")) {
	        	System.out.print("\033[H\033[2J");
	        }
	    } catch (final Exception e) {}
		
		System.out.println(
			   "\n\n\n<::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::>\n"
			    +  "<:::: _______.._______.._.......__________________._______.._______ :::::>\n"
				+  "<::::(       )(  ___  )( \\::::/|\\__   __/\\__   __/(  ___  )|  ____  )::::>\n"
				+  "<::::| || || || (:::) ||  \\::( |:::) (::::::) (:::| (:::) || (::::) |::::>\n"
				+  "<::::| || || || |:::| ||   \\:| |:::| |::::::| |:::| |:::| || (____) |::::>\n"   
				+  "<::::| |:V:| || |:::| || (\\ \\) |:::| |::::::| |:::| |:::| ||     ___/::::>\n"   
				+  "<::::| |:::| || |:::| || |:\\   |:::| |::::::| |:::| |:::| || (\\ (::::::::>\n"
				+  "<::::| ):::( || (___) || )::\\  |___) (___:::| |:::| (___) || ) \\ \\__..:::>\n"
				+  "<::::|/:::::\\|(_______)|/::::)_)\\_______/:::)_(:::(_______)|/:::\\___/::::>\n"
				+  "<::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::>\n\n");
	}

}
