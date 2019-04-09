package com.zversal.api.database;

import org.bson.Document;
import com.mongodb.client.FindIterable;

public interface DataAccessor {
	FindIterable<Document> findDocwithTicker(String ticker);
	FindIterable<Document> findDocWithChannel(String channel);
	FindIterable<Document> FindDocWithTickerAndProjection(String ticker,String[] include);
}
