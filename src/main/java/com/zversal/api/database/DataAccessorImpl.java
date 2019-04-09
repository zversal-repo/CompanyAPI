package com.zversal.api.database;

import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;

public class DataAccessorImpl implements DataAccessor {
	private DatabaseConnection databaseConnection = new DatabaseConnection();
	private MongoCollection<Document> collection = databaseConnection.getCollections();

	@Override
	public FindIterable<Document> findDocwithTicker(String ticker) {
		FindIterable<Document> doc = collection.find(new Document("Ticker", ticker));
		return doc;
	}

	@Override
	public FindIterable<Document> findDocWithChannel(String channel) {
		FindIterable<Document> doc = collection.find(new Document("Channel", channel));
		return doc;
	}

	@Override
	public FindIterable<Document> FindDocWithProjection(String ticker, String[] include) {
		FindIterable<Document> doc = collection.find(new Document("Ticker", ticker))
				.projection(Projections.fields(Projections.include(include), Projections.excludeId()));
		return doc;
	}

}
