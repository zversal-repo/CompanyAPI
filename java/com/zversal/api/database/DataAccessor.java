package com.zversal.api.database;

import org.bson.Document;
import com.mongodb.client.FindIterable;

public interface DataAccessor {
	FindIterable<Document> findDocwithTicker(String ticker);
	FindIterable<Document> findDocWithChannelAndProjection(String channel, String include);
	Document FindDocWithTickerAndProjection(String ticker,String[] include);
}
