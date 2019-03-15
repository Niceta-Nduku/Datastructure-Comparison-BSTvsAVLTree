/**
*	PowerData is an object class.
*	The PowerData Object has a Date/Time, power and voltage
*	@author Niceta Nduku 
*/
public class PowerData {
	public String dateTime;
	public String power;
	public String voltage;
	
	public PowerData( String dateTime, String power, String voltage) { 
		this.dateTime = dateTime;
		this.power = power;
		this.voltage= voltage;
	}

	public String getDateTime() {
		/**
		*	@return dateTime
		*/
		return dateTime;
	}

	public String toString() {
		/**
		*	@Override
		*/
		return dateTime+ " " +power+ " " +voltage;
	}
}