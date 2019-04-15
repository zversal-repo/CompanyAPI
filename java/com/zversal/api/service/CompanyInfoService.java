package com.zversal.api.service;

import org.bson.Document;

public interface CompanyInfoService {
	Document getData(String ticker);
	Document getTicker(String channel);
	Document getEarningData(String ticker);
	Document getSnapshot(String ticker);
	Document getAnalystCoverage(String ticker);
	Document getKeyStatsAndFinancials(String ticker);
}
