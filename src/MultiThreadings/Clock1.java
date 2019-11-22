package MultiThreadings;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Executes Every 1000 Milliseconds.
 * Alert : It Will Run Infinitly.
 */
class Clock1
{
	public static void main (String [] args)
	{
		Timer t = new Timer ();
		t.schedule (new TimerTask()
					{
						public void run ()
						{
							System.out.println (new Date().toString ());
						}
					},
				0,
				1000);
	}
}
