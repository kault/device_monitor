import java.util.*;


public class DVDPlayer implements Device
{
	public String name;
	public StateEnum state = StateEnum.OFF;
	
	// Constructor taking a String name and a StateEnum state as parameters.
	public DVDPlayer(String name, StateEnum state)
	{
		this.name = name;
		this.state = state;
	}

	// Sets DVDPlayer to ON if it is not already and calls a ping.
	public void powerOn() 
	{	
		if (state != StateEnum.ON) {
			state = StateEnum.ON;
			ping(state);
		}
	}

	// Sets DVDPlayer to OFF if it is not already and calls a ping.
	public void powerOff()
	{
		if (state != StateEnum.OFF) {
			state = StateEnum.OFF;
			ping(state);
		}
	}

	// Prints the current state of the DVDPlayer.
	public void state()
	{
		System.out.println("The state of dvdplayer " + name + " is currently: " + state);
	}

	// Prints the new state of the DVDPlayer and the time at which it changed.
	public void ping(StateEnum newState)
	{
		Date date = new Date();
		System.out.println("The state of dvdplayer " + name + " has changed to " + newState
							+ " @ " + date.toString());
	}

}