package com.zversal.api.service;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.springframework.stereotype.Service;
import com.mongodb.client.MongoCursor;
import com.zversal.api.database.DataAccessorImpl;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {
	private DataAccessorImpl dataAccess = new DataAccessorImpl();

	public Document getData(String ticker) {
		Document doc = dataAccess.findDocwithTicker(ticker).first();
		return doc;
	}

	public Document getTicker(String channel) {
		MongoCursor<Document> itr = dataAccess.findDocWithChannel(channel).iterator();
		List<Object> listDoc = new ArrayList<>();
		Document doc = new Document();
		while (itr.hasNext()) {
			Document mdoc = itr.next();
			listDoc.add(mdoc.get("Ticker"));
		}
		doc.put("Ticker", listDoc);
		return doc;
	}

	public Document getEarningData(String ticker) {
		// final String[] keys = { "ZN3", "ZN1", "Z2B" };
		final String[] include = { "ZN3.mostRecentQTRSurprise%", "ZN3.threeQTRPriorEndDate", "ZN3.actualEPSRecentQTR",
				"ZN3.mostRecentQTRDifference", "ZN3.oneQTRPriorEndDate", "ZN3.twoQTRPriorEPS",
				"ZN3.threeQTRPriorSurprise%", "ZN3.twoQTRPriorSurprise%", "ZN3.actualEPSThreeQTR", "ZN3.oneQTRPriorEPS",
				"ZN3.oneQTRPriorSurprise%", "ZN3.company", "ZN3.actualEPSOneQTR", "ZN3.threeQTR_EPS",
				"ZN3.mostRecentQTREndDate", "ZN3.twoQTRPriorEndDate", "ZN3.twoQTRPriorDiffernce",
				"ZN3.mostRecentQTR_EPS", "ZN3.oneQTRPriorDiffernce", "ZN3.actualEPSTwoQTR",
				"ZN3.threeQTRPriorDiffernce", "ZN3.lastModified", "ZN1.nextFYEndDate", "ZN1.nextFYLowEPS",
				"ZN1.currentQTRHighEPS", "ZN1.noOfNextFY", "ZN1.currentFYHighEPS", "ZN1.currentFY_EPS1",
				"ZN1.currentFY_EPS", "ZN1.nextQTREndDate", "ZN1.currentQTR_EPS", "ZN1.currentQTREndDate",
				"ZN1.twoQTRActualEPS", "ZN1.nextFYHighEPS", "ZN1.noOfNextQTR", "ZN1.lastFYActualEPS",
				"ZN1.currentFYEndDate", "ZN1.currentQTRLowEPS", "ZN1.longTermGrowthLow", "ZN1.nextQTRHighEPS",
				"ZN1.currentFYLowEPS", "ZN1.noOfCurrentQTR", "ZN1.noOfCurrentFY", "ZN1.nextQTRLowEPS",
				"ZN1.lastModified", "ZN1.nextFY_EPS", "ZN1.nextQTR_EPS", "Z2B.KEY 1", "Z2B.KEY 2", "Z2B.KEY 3",
				"Z2B.KEY 4", "Z2B.KEY 5", "Z2B.KEY 6", "Z2B.KEY 7", "Z2B.KEY 8", "Z2B.KEY 9", "Z2B.KEY 10",
				"Z2B.KEY 11", "Z2B.KEY 12", "Z2B.KEY 13", "Z2B.KEY 14", "Z2B.KEY 15", "Z2B.KEY 16", "Z2B.KEY 17",
				"Z2B.KEY 18", "Z2B.KEY 19", "Z2B.KEY 20", "Z2B.KEY 21", "Z2B.KEY 22", "Z2B.KEY 23", "Z2B.KEY 24" };

		Document doc = dataAccess.FindDocWithProjection(ticker, include).first();
		return doc;
	}

	public Document getSnapshot(String ticker) {
		final String[] include = { "CZ3", "ZK3.marketCap", "CZ1.totalEmployees", "CZ2.country", "CZ2.zipCode",
				"CZ2.city", "CZ2.fiscalYearEnd", "CZ2.expandedIndustry", "CZ2.company", "CZ2.state", "CZ2.fax",
				"CZ2.email", "CZ2.address2", "CZ2.address1", "CZ2.titleOfOfficer1", "CZ2.titleOfOfficer2",
				"CZ2.titleOfOfficer5", "CZ2.phone", "CZ2.titleOfOfficer3", "CZ2.titleOfOfficer4",
				"CZ2.executiveOfficer5", "CZ2.executiveOfficer4", "CZ2.executiveOfficer3", "CZ2.executiveOfficer2",
				"CZ2.lastModified", "CZ2.executiveOfficer1" };
		Document doc = dataAccess.FindDocWithProjection(ticker, include).first();
		return doc;
	}

	public Document getAnalystCoverage(String ticker) {
		final String[] include = { "ZN5.meanRating1Month", "ZN5.noOfStrongSellRatings3Month",
				"ZN5.noOfModerateSell3Month", "ZN5.meanRating2Month", "ZN5.meanRating3Month",
				"ZN5.noOfHoldRatings2Month", "ZN5.noOfModerateSell2Month", "ZN5.noOfModerateBuyRatings1Month",
				"ZN5.noOfModerateBuyRatings3Month", "ZN5.noOfStrongBuyRatings3Month",
				"ZN5.noOfStrongSellRatingsCurrent", "ZN5.noOfStrongBuyRatingsCurrent", "ZN5.noOfHoldRatings1Month",
				"ZN5.noOfModerateBuyRatings2Month", "ZN5.noOfHoldRatings3Month", "ZN5.noOfStrongSellRatings1Month",
				"ZN5.noOfStrongBuyRatings1Month", "ZN5.noOfModerateBuyRatingsCurrent", "ZN5.noOfModerateSellCurrent",
				"ZN5.noOfModerateSell1Month", "ZN5.noOfStrongBuyRatings2Month", "ZN5.noOfHoldRatingsCurrent",
				"ZN5.noOfStrongSellRatings2Month", "ZN5.meanRatingCurrent" };
		Document doc = dataAccess.FindDocWithProjection(ticker, include).first();
		return doc;
	}

}