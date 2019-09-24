package gavinleo.mongo_probe;

import java.time.Instant;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProbeStatsCollector {

	private String instanceKey;
	private HashMap<String, ArrayList<ProbeElapsedTimeObservation>> observedElapsedTimes;

	public ProbeStatsCollector(String instanceKeyInput) {
		instanceKey = instanceKeyInput;

		observedElapsedTimes = new HashMap<String, ArrayList<ProbeElapsedTimeObservation>>();
	}

	public ProbeElapsedTimeObservation getNewObservation(String label) {
		ProbeElapsedTimeObservation newObs = new ProbeElapsedTimeObservation(label);

		return newObs;
	}

	public void addObservation(ProbeElapsedTimeObservation observation) {
		ArrayList<ProbeElapsedTimeObservation> obsList;

		if (observedElapsedTimes.containsKey(observation.getLabel())) {
			obsList = observedElapsedTimes.get(observation.getLabel());
			obsList.add(observation);
			observedElapsedTimes.put(observation.getLabel(), obsList);
		} else {
			obsList = new ArrayList<ProbeElapsedTimeObservation>();
			obsList.add(observation);
			observedElapsedTimes.put(observation.getLabel(), obsList);
		}
	}

	public JSONObject getElapsedTimeStatsForLabelAsJSON(String label) {

		Instant currInstant = Instant.now();
		String currTimestamp = currInstant.toString();

		JSONObject resultAsJSON = new JSONObject();

		resultAsJSON.put("instanceKey", this.instanceKey);
		resultAsJSON.put("label", label);
		resultAsJSON.put("calculationTimestamp", currTimestamp);

		ArrayList<Long> elapsedTimesForLabel = this.getElapsedTimesAsArrayList(label);

		HashMap<String, Double> stats = this.arrayListGetStats(elapsedTimesForLabel);

		JSONObject elapsedTimeStats = new JSONObject();
		for (String statName : stats.keySet()) {
			elapsedTimeStats.put(statName, stats.get(statName));
		}
		
		resultAsJSON.put("Elapsed Time Stats", elapsedTimeStats);

		return resultAsJSON;
	}
	

	private HashMap<String, Double> arrayListGetStats(ArrayList<Long> list) {
		
		
		ObservationStatsCalculator calc = new ObservationStatsCalculator( list );
		
		return calc.arrayListGetStats();
	}

	private JSONArray getObservationsAsJSON(String label) {

		JSONArray obsArray = new JSONArray();
		ArrayList<ProbeElapsedTimeObservation> obsForLabel = this.observedElapsedTimes.get(label);

		for (ProbeElapsedTimeObservation obs : obsForLabel) {

			obsArray.put(obs.getObservationAsJSON());
		}

		return obsArray;
	}

	private ArrayList<Long> getElapsedTimesAsArrayList(String label) {
		ArrayList<ProbeElapsedTimeObservation> obsList = this.observedElapsedTimes.get(label);

		ArrayList<Long> listAsLong = new ArrayList<Long>();

		for (ProbeElapsedTimeObservation obs : obsList) {
			listAsLong.add(obs.getElapsedTimeInMicroSeconds());
		}

		return listAsLong;
	}

	public JSONObject getStatsAndObservations() {
		JSONObject combinedResultAsJSON = new JSONObject();

		JSONArray labelsJSON = new JSONArray();

		for (String label : this.observedElapsedTimes.keySet()) {
			JSONObject labelInfo = new JSONObject();

			labelInfo.put("Label", label);

			JSONObject performanceForLabelAsJSON = this.getElapsedTimeStatsForLabelAsJSON(label);
			JSONArray observationsForLabelAsJSON = this.getObservationsAsJSON(label);
			labelInfo.put("Elapsed Time Performance", performanceForLabelAsJSON);
			labelInfo.put("Observations", observationsForLabelAsJSON);

			labelsJSON.put(labelInfo);

		}

		combinedResultAsJSON.append("Results By Label", labelsJSON);

		return combinedResultAsJSON;
	}
}
