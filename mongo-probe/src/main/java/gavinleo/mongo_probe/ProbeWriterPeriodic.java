package gavinleo.mongo_probe;

import java.util.ArrayList;
import java.util.Calendar;
import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ProbeWriterPeriodic {

	private String mongoURI;
	private static final String PROBE_DATABASE_NAME = "mongoProbeDb";
	private static final String PROBE_COLLECTION_NAME = "probeObservations";
	private int counter = 0;
	private Calendar cal;
	private String probeName;

	private ProbeStatsCollector statsCollector;

	public ProbeWriterPeriodic(String in_mongoURI, String in_probeName) {

		this.probeName = in_probeName;
		this.mongoURI = in_mongoURI;
		cal = Calendar.getInstance();

		statsCollector = new ProbeStatsCollector(this.getProbeName());
	}

	public String getProbeName() {
		return this.probeName;
	}

	private int getNextCounter() {
		this.counter++;
		return this.counter;
	}

	private ArrayList<Document> generateWriteProbeDocuments(String probeTestType, int numberOfDocuments) {
		ArrayList<Document> probeDocs = new ArrayList<Document>();

		int transactionID = 0;

		for (int i = 0; i < numberOfDocuments; i++) {

			int counter = this.getNextCounter();

			if (transactionID == 0)
				transactionID = counter;

			Document docForProbe = new Document();
			docForProbe.append("counter", this.getNextCounter());
			docForProbe.append("commitTime", cal.getTime());
			docForProbe.append("probeName", this.getProbeName());
			docForProbe.append("probeTransaction", transactionID);
			docForProbe.append("numDocsInTransaction", numberOfDocuments);
			docForProbe.append("probeTestType", probeTestType );
			probeDocs.add(docForProbe);
		}
		return probeDocs;
	}

	public JSONObject getProbeElapsedTimeStatsAllLabels() {
		return this.statsCollector.getStatsAndObservations();	
	}
	
	private void writeProbeDocuments( String probeTestType, int numberOfDocuments ) {

		MongoClientURI clientURI = new MongoClientURI(this.mongoURI);

		MongoClient mongoClient = new MongoClient(clientURI);
		MongoDatabase database = mongoClient.getDatabase(PROBE_DATABASE_NAME);
		MongoCollection<Document> collection = null;

		collection = database.getCollection(PROBE_COLLECTION_NAME);

		ArrayList<Document> documentsToCommit = this.generateWriteProbeDocuments(probeTestType, numberOfDocuments);

		// Allocate a new observation
		ProbeElapsedTimeObservation timerObs = statsCollector.getNewObservation( probeTestType );

		timerObs.startTimer();
		try (ClientSession clientSession = mongoClient.startSession()) {
			clientSession.startTransaction();
			collection.insertMany(clientSession, documentsToCommit);
			clientSession.commitTransaction();
		} catch (Exception e) {
			System.out.println(e);
		}

		timerObs.stopTimer();

		statsCollector.addObservation(timerObs);

		mongoClient.close();
	}
	
	private void readProbeDocuments( String probeTestType, int numberOfDocuments ) {
		MongoClientURI clientURI = new MongoClientURI(this.mongoURI);

		MongoClient mongoClient = new MongoClient(clientURI);
		MongoDatabase database = mongoClient.getDatabase(PROBE_DATABASE_NAME);
		MongoCollection<Document> collection = null;

		collection = database.getCollection(PROBE_COLLECTION_NAME);

		
	}
	
	public void runProbeTestWriteSingleDocSmall() {
		this.writeProbeDocuments( "WRITE_SINGLE_DOC_SMALL", 1 );
	}

	public void runProbeTestWriteMultipleDocSmall() {
		this.writeProbeDocuments( "WRITE_MULTIPLE_DOCS_SMALL", 10 );
	}

}
