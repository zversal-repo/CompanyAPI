package com.zversal.api.service;

import org.bson.Document;

public interface CompanyInfoService {
	Document getData(String ticker);
	Document getTicker(String channel);
	Document getEarningData(String ticker);
	Object getSnapshot(String ticker);
	Document getAnalystCoverage(String ticker);
}
