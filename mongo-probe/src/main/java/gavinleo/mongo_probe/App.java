package gavinleo.mongo_probe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		String mongoClusterName = "mongodb://mongo-node1:27017,mongo-node2:27017,mongo-node3:27017/?replicaSet=gavMongoReplicaSet0";
		System.out.println("Connecting to " + mongoClusterName);
		String probeName = "Probe" + System.getenv( "PROBENAME");
		ProbeWriterPeriodic periodicProbe = new ProbeWriterPeriodic(mongoClusterName, probeName);

		String logFileName = "/logs/mongoProbe_" + probeName + ".log";

		for (int i = 0; i < 500; i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.printf("Writing probe entry %d\n", i);
			periodicProbe.runProbeTestWriteSingleDocSmall();
			periodicProbe.runProbeTestWriteMultipleDocSmall();

			if ( ( i % 50 == 0) && i > 0 ) {
				
				String logString = periodicProbe.getProbeElapsedTimeStatsAllLabels().toString();
				
				BufferedWriter writer;
				try {
					writer = new BufferedWriter(new FileWriter(logFileName, true));
					writer.append(logString);
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();			
				}

			}
		}

	}
}
