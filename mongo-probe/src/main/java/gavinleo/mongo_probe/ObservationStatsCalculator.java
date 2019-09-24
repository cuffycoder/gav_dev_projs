package gavinleo.mongo_probe;

import java.util.ArrayList;
import java.util.HashMap;


public class ObservationStatsCalculator {

	private ArrayList<Long> listOfLongs;
	
	public ObservationStatsCalculator( ArrayList<Long> list ) {
		listOfLongs = list;
	}

	public HashMap<String, Double> arrayListGetStats() {
		HashMap<String, Double> stats = new HashMap<String, Double>();

		double min = 0;
		double max = 0;
		double mean = 0;
		double p95 = 0;
		double p99 = 0;

		double sum = 0.0;
		int numObs = listOfLongs.size();

		// First sort the list
		listOfLongs.sort(null);

		if (numObs > 0) {
			min = listOfLongs.get(0);
			max = listOfLongs.get(listOfLongs.size() - 1);

			// figure out how many observations we need to get to p95 and p99
			int p95Index = (int) Math.ceil(numObs * (95.0 / 100.0));
			p95 = listOfLongs.get(p95Index - 1);

			int p99Index = (int) Math.ceil(numObs * (99.0 / 100.0));
			p99 = listOfLongs.get(p99Index - 1);

			for (Long entry : listOfLongs)
				sum += entry;

			mean = sum / numObs;
		}

		stats.put("Min", min);
		stats.put("Max", max);
		stats.put("Number of Observations", (double) numObs);
		stats.put("Mean", mean);
		stats.put("95 Percentile", p95);
		stats.put("99 Percentile", p99);

		return stats;
	}
}
