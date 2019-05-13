package com.zversal.api.database;

import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;
/**
 * This class implements the interface {@link DataAccessor}. It is used to Access Data from MongoDb database.
 * 
 * @author Bhupinder
 *
 */
public class DataAccessorImpl implements DataAccessor {
	private DatabaseConnection databaseConnection = new DatabaseConnection();
	private MongoCollection<Document> collection = databaseConnection.getCollections();

	/**
	 * This method is used to find Document from MongoDB Collection with given Ticker(Unique Id) in parameter 
	 * @param ticker the unique id of the Document 
	 * @return Iterable Documents
	 */
	@Override
	public FindIterable<Document> findDocwithTicker(String ticker) {
		FindIterable<Document> doc = collection.find(new Document("Ticker", ticker));
		return doc;
	}

	/**
	 * This method is used to find All Documents from MongoDB Collection with given Channel in parameter but contains only that fields specifed in parameter include .
	 * @param ticker the unique id of the Document 
	 * @param include is a variable of type String used to include only specific field performed by Projection
	 * @return Iterable Documents
	 */
	@Override
	public FindIterable<Document> findDocWithChannelAndProjection(String channel, String include) {
		FindIterable<Document> doc = collection.find(new Document("Channel", channel))
				.projection(Projections.fields(Projections.include(include), Projections.excludeId()));
		return doc;
	}
	/**
	 * This method is used to find All Documents from MongoDB Collection with given Ticker in parameter but contains only that fields specifed in parameter include .
	 * @param ticker the unique id of the Document 
	 * @param include is an array of String used to include only specific field performed by Projection
	 * @return Iterable Documents
	 */
	@Override
	public Document FindDocWithTickerAndProjection(String ticker, String[] include) {
		Document doc = collection.find(new Document("Ticker", ticker))
				.projection(Projections.fields(Projections.include(include), Projections.excludeId())).first();
		return doc;
	}

}
