import java.util.Date;

public class Computer implements Device
{

	public String name;
	public StateEnum state = StateEnum.OFF;
	
	// Constructor taking a String name and a StateEnum state as parameters.
	public Computer (String name, StateEnum state)
	{
		this.name = name;
		this.state = state;
	}

	// Sets Computer to ON if it is not already and calls a ping.
	public void powerOn() 
	{	
		if (state != StateEnum.ON) {
			state = StateEnum.ON;
			ping(state);
		}
	}

	// Sets Computer to OFF if it is not already and calls a ping.
	public void powerOff()
	{
		if (state != StateEnum.OFF) {
			state = StateEnum.OFF;
			ping(state);
		}
	}
	
	// Sets Computer to SLEEP if it is not already and calls a ping.
	public void sleep()
	{
		if (state != StateEnum.SLEEP) {
			state = StateEnum.SLEEP;
			ping(state);
		}
	}

	// Prints the current state of the Computer.
	public void state()
	{
		System.out.println("The state of computer " + name + " is currently: " + state);
	}

	// Prints the new state of the Computer and the time at which it changed.
	public void ping(StateEnum newState)
	{
		Date date = new Date();
		System.out.println("The state of computer " + name + " has changed to " + newState
							+ " @ " + date.toString());
	}
		
}