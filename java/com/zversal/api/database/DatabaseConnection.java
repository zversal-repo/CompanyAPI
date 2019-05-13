package com.zversal.api.database;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.zversal.api.configuration.ConfigProperties;

/**
 * This class is used to make Mongodb Connection and for database configuration
 * 
 * @author bhupinder
 */
public class DatabaseConnection {
	private ConfigProperties configproperties = new ConfigProperties();
	private MongoCollection<Document> collection = null;
	private MongoCollection<Document> userCollection = null;
	private MongoClient mongoClient = null;

	/**
	 * This method is used to create MongoClient instance and set Database and
	 * Collection.
	 */
	public void connection() {
		System.out.println("CONNECTING MONGODB");
		String databaseName = configproperties.getDatabaseName();
		// String connectionString = configproperties.getUri();
		String collectionName = configproperties.getCollection();
		try {
			
			/*
			 * ConnectionString connectionString = new
			 * ConnectionString("mongodb://localhost:27017"); ConnectionPoolListener
			 * poolListener = new MongodbConnectionPoolListener(); CommandListener
			 * commandListener = new MongodbCommandListener(); MongoClientSettings settings
			 * = MongoClientSettings.builder() .applyToConnectionPoolSettings(builder ->
			 * builder.addConnectionPoolListener(poolListener))
			 * .addCommandListener(commandListener) .applyToServerSettings(builder ->
			 * builder.addServerListener(new MongodbServerListener())
			 * .addServerMonitorListener(new MongodbServerMonitorListener()))
			 * .applyToClusterSettings( builder -> builder.addClusterListener(new
			 * MongodbClusterListener(ReadPreference.secondary())))
			 * .applyConnectionString(connectionString).build(); mongoclient =
			 * MongoClients.create(settings);
			 */
			
			MongoClientOptions.Builder options = new MongoClientOptions.Builder();
			options.addCommandListener(new TestCommandListener());
			options.addClusterListener(new TestClusterListener(ReadPreference.secondary()));
			// options.maxConnectionLifeTime(1000);
			mongoClient = new MongoClient(new ServerAddress("localhost", 27017), options.build());
			// mongoClient = MongoClients.create(connectionString);
			MongoDatabase database = mongoClient.getDatabase(databaseName);
			// checkDatabase(mongoClient, databaseName);
			collection = database.getCollection(collectionName);
			userCollection = database.getCollection("Users");
			// checkCollection(database, collectionName);
		} catch (IllegalArgumentException iae) {
			System.out.println("Database name is invalid");
			iae.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * This method is used to get Collection
	 * 
	 * @return Collection
	 */
	public MongoCollection<Document> getCollections() {
		connection();
		return collection;
	}

	public MongoCollection<Document> getUsersCollections() {
		connection();
		return userCollection;
	}

	/**
	 * This method is used to check if Collection is available in database or not.
	 * 
	 * @param database       - MongoDatabase
	 * @param collectionName - Collection Name of type String
	 *
	 */
	private void checkCollection(MongoDatabase database, String collectionName) {
		boolean checkcol = false;
		MongoIterable<String> collections = database.listCollectionNames();
		for (String collectionname : collections) {
			if (collectionname.equals(collectionName)) {
				checkcol = true;
			}
		}
		if (checkcol == false) {
			System.out.println("Collection Not Found");
			System.exit(1);
		}
	}

	/**
	 * This method is used to check if Database with name given in parameter is
	 * available or not.
	 * 
	 * @param mongoClient   - Instance of MongoClient
	 * @param databaseName- Database name of type String
	 *
	 */
	private void checkDatabase(MongoClient mongoClient, String databaseName) {
		boolean checkDB = false;
		MongoIterable<String> databases = mongoClient.listDatabaseNames();
		for (String databasename : databases) {
			if (databasename.equals(databaseName)) {
				checkDB = true;
			}
		}
		if (checkDB == false) {
			System.out.println("Database Not Found");
			System.exit(1);
		}
	}
}
