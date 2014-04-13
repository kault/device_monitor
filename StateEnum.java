// An enum enumerating the possible states of the three devices. Note that SLEEP only
// applies to Computer and Projector, not DVDPlayer.
public enum StateEnum 
{
	OFF(0), ON(1), SLEEP(2);
	
	private final int state;
		
	// Constructor taking int state as sole parameter
	private StateEnum(int state) 
	{
		this.state = state;
	}
	
}
