import java.util.Date;

public class Projector implements Device
{

	public String name;
	public StateEnum state = StateEnum.OFF;
	
	// Constructor taking a String name and a StateEnum state as parameters.
	public Projector (String name, StateEnum state)
	{
		this.name = name;
		this.state = state;
	}

	// Sets Projector to ON if it is not already and calls a ping.
	public void powerOn() 
	{	
		if (state != StateEnum.ON) {
			state = StateEnum.ON;
			ping(state);
		}
	}

	// Sets Projector to OFF if it is not already and calls a ping.
	public void powerOff()
	{
		if (state != StateEnum.OFF) {
			state = StateEnum.OFF;
			ping(state);
		}
	}
	
	// Sets Projector to SLEEP if it is not already and calls a ping.
	public void sleep()
	{
		if (state != StateEnum.SLEEP) {
			state = StateEnum.SLEEP;
			ping(state);
		}
	}

	// Prints the current state of the Projector.
	public void state()
	{
		System.out.println("The state of projector " + name + " is currently: " + state);
	}

	// Prints the new state of the Projector and the time at which it changed.
	public void ping(StateEnum newState)
	{
		Date date = new Date();
		System.out.println("The state of projector " + name + " has changed to " + newState
							+ " @ " + date.toString());
	}
		
}