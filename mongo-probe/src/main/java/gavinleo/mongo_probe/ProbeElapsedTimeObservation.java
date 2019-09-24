package gavinleo.mongo_probe;

import java.sql.Timestamp;
import org.json.JSONObject;

public class ProbeElapsedTimeObservation {

	private long observationStartTime;
	private long nanoTimeStart;
	private long nanoTimeEnd;
	private String label;

	public ProbeElapsedTimeObservation(String labelInput) {
		observationStartTime = nanoTimeStart = nanoTimeEnd = 0L;

		this.label = labelInput;
	}

	public void startTimer() {
		observationStartTime = System.currentTimeMillis();
		nanoTimeStart = System.nanoTime();
	}

	public void stopTimer() {
		nanoTimeEnd = System.nanoTime();
	}

	private long getObservationStartTime() {
		return this.observationStartTime;
	}

	public long getElapsedTimeInMicroSeconds() {

		if (this.nanoTimeEnd == 0L || this.nanoTimeStart == 0L)
			return 0L;

		return ( this.nanoTimeEnd - this.nanoTimeStart ) / 1000;
	}

	public String getLabel() {
		return label;
	}

	public JSONObject getObservationAsJSON() {
		JSONObject resultAsJSON = new JSONObject();
		
		Timestamp obsTimestamp = new Timestamp( this.getObservationStartTime() );
		
		resultAsJSON.append( "Observation Time", obsTimestamp.toString());
		resultAsJSON.append( "Elapsed Time in Microseconds", this.getElapsedTimeInMicroSeconds() );
		return resultAsJSON;
	}
	
	/*
	 * Comparator for sorting by elapsed time
	 *
	public static Comparator<ProbeElapsedTimeObservation> ObservationElapsedTimeComparator = new Comparator<ProbeElapsedTimeObservation>() {
		public int compare(ProbeElapsedTimeObservation o1, ProbeElapsedTimeObservation o2) {
			long e1 = o1.getElapsedTimeInMicroSeconds();
			long e2 = o2.getElapsedTimeInMicroSeconds();
			
			// ascending order
			if( e2 > e1 ) 
				return -1;
						
			if( e2 < e1 )
				return 1;
			
			return 0;
		}
	};
	
	*/
}
