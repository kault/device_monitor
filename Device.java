public interface Device
{

	// Tells the device to turn on, possibly changing its state and calling a
	// ping() accordingly.
	public void powerOn();

	// Tells the Device to turn off, possibly changing its state and calling a
	// ping() accordingly.
	public void powerOff();

	// Prints a short sentence with the current state of the Device.
	public void state();

	// Prints the new state of the Device and the time at which it changed.
	public void ping(StateEnum newState);
	
}