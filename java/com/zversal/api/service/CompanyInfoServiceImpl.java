package com.zversal.api.service;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.springframework.stereotype.Service;
import com.mongodb.client.FindIterable;
import com.zversal.api.database.DataAccessorImpl;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {
	private DataAccessorImpl dataAccessor = new DataAccessorImpl();

	private final String[] includeEarning = { "ZN3.mostRecentQtrSurprisePerc", "ZN3.threeQtrPriorEndDate",
			"ZN3.actualEpsRecentQtr", "ZN3.mostRecentQtrDifference", "ZN3.oneQtrPriorEndDate", "ZN3.twoQtrPriorEPS",
			"ZN3.threeQtrPriorSurprisePerc", "ZN3.twoQtrPriorSurprisePerc", "ZN3.actualEps3QTR", "ZN3.oneQtrPriorEps",
			"ZN3.oneQtrPriorSurprisePerc", /* "ZN3.company", */ "ZN3.actualEps1Qtr", "ZN3.threeQtrEps",
			"ZN3.mostRecentQtrEndDate", "ZN3.twoQtrPriorEndDate", "ZN3.twoQtrPriorDiffernce", "ZN3.mostRecentQtrEps",
			"ZN3.oneQtrPriorDifference", "ZN3.actualEps2QTR", "ZN3.threeQtrPriorDiffernce", /* "ZN3.lastModified", */
			"ZN1.nextFYEndDate", "ZN1.nextFyLowEps", "ZN1.currentQtrHighEps", "ZN1.noOfNextFy", "ZN1.currentFyHighEps",
			/* "ZN1.currentFY_EPS1" */ "ZN1.currentFyEps", "ZN1.nextQtrEndDate", "ZN1.currentQtrEps",
			"ZN1.currentQtrEndDate", /* "ZN1.twoQTRActualEPS" */ "ZN1.nextFyHighEps", "ZN1.noOfNextQtr",
			/* "ZN1.lastFYActualEPS" */ "ZN1.currentFyEndDate", "ZN1.currentQtrLowEps", /* "ZN1.longTermGrowthLow", */
			"ZN1.nextQtrHighEps", "ZN1.currentFyLowEps", "ZN1.noOfCurrentQtr", "ZN1.noOfCurrentFy", "ZN1.nextQtrLowEps",
			/* "ZN1.lastModified", */ "ZN1.nextFyEps", "ZN1.nextQtrEps", "Z6C.currentQtrRevisionUp7Days",
			"Z6C.currentQtrRevisionDown7Days", "Z6C.nextQtrRevisionUp7Days", "Z6C.nextQtrRevisionDown7Days",
			"Z6C.currentFyRevisionUp7Days", "Z6C.currentFyRevisionDown7Days", "Z6C.nextFyRevisionUp7Days",
			"Z6C.currentQtrRevisionUp30Days", "Z6C.currentQtrRevisionDown30Days", "Z6C.nextQtrRevisionUp30Days",
			"Z6C.nextQtrRevisionDown30Days", "Z6C.currentFyRevisionUp30Days", "Z6C.currentFyRevisionDown30Days",
			"Z6C.nextFyRevisionUp30Days", "Z6C.nextFyRevisionDown30Days", "Z6C.nextFyRevisionDown7Days",
			"Z2B.currentQtrMeanEps", "Z2B.currentFyMeanEps", "Z2B.nextQtrMeanEps", "Z2B.nextFyrMeanEps",
			"Z2B.currentQtrMeanEps7Days", "Z2B.currentFyMeanEps7Days", "Z2B.nextQtrMeanEps7Days",
			"Z2B.nextFyMeanEps7Days", "Z2B.currentQtrMeanEps30Days", "Z2B.currentFyMeanEps30Days",
			"Z2B.nextFyMeanEps30Days", "Z2B.nextQtrMeanEps30Days", "Z2B.currentFyMeanEps60Days",
			"Z2B.currentQtrMeanEps60Days", "Z2B.nextFyMeanEps60Days", "Z2B.nextQtrMeanEps60Days",
			"Z2B.currentFyMeanEps90Days", "Z2B.currentQtrMeanEps90Days", "Z2B.nextQtrMeanEps90Days",
			"Z2B.nextFyMeanEps90Days", "Z2B.nextQtrEndDate", "Z2B.nextFyEndDate", "Z2B.currentQtrEndDate",
			"Z2B.currentFyEndDate", " CZ1.fiscalYearEndMonth", "CZ2.lastModified", "CZ1.nextExpectedReportDate" };

	private final String[] includeSnapshot = { "CZ3.companyDescription", "ZK3.marketCap", "CZ1.totalEmployees",
			"CZ2.country", "CZ2.zipCode", "CZ2.city",
			/* "CZ2.fiscalYearEnd", */ /* "CZ2.expandedIndustry", */ "CZ2.company", "CZ2.state",
			/* "CZ2.fax", */ /* "CZ2.email", */ "CZ2.address2", "CZ2.address", "CZ2.titleOfOfficer1",
			"CZ2.titleOfOfficer2", "CZ2.titleOfOfficer5", "CZ2.phone", "CZ2.titleOfOfficer3", "CZ2.titleOfOfficer4",
			"CZ2.executiveOfficer5", "CZ2.executiveOfficer4", "CZ2.executiveOfficer3", "CZ2.executiveOfficer2",
			"CZ2.lastModified", "CZ2.executiveOfficer1" };

	private final String[] includeAnalystCoverage = { "ZN5.meanRating1Month", "ZN5.noOfStrongSellRatings3Month",
			"ZN5.noOfModerateSell3Month", "ZN5.meanRating2Month", "ZN5.meanRating3Month", "ZN5.noOfHoldRatings2Month",
			"ZN5.noOfModerateSell2Month", "ZN5.noOfModerateBuyRatings1Month", "ZN5.noOfModerateBuyRatings3Month",
			"ZN5.noOfStrongBuyRatings3Month", "ZN5.noOfStrongSellRatingsCurrent", "ZN5.noOfStrongBuyRatingsCurrent",
			"ZN5.noOfHoldRatings1Month", "ZN5.noOfModerateBuyRatings2Month", "ZN5.noOfHoldRatings3Month",
			"ZN5.noOfStrongSellRatings1Month", "ZN5.noOfStrongBuyRatings1Month", "ZN5.noOfModerateBuyRatingsCurrent",
			"ZN5.noOfModerateSellCurrent", "ZN5.noOfModerateSell1Month", "ZN5.noOfStrongBuyRatings2Month",
			"ZN5.noOfHoldRatingsCurrent", "ZN5.noOfStrongSellRatings2Month", "ZN5.meanRatingCurrent" };

	private final String[] includeKeyStatsAndFinancials = { "ZK3.currentPrice", "ZK3.fiftyTwoWeekHigh",
			"ZK3.fiftyTwoWeekLow", "ZK3.avgDaily", "ZK3.beta", "ZK3.marketCap", "ZK3.sharesOutLatestQtr",
			"ZK15.float(millions of shares)", "ZK3.frwdDivYield", "ZK3.indicatedAnnualdividend",
			"DVR.dividendRecentExDate", "DVR.dividendRecentPayDate", "CZ1.recentQtrQuickRatio1",
			"CZ1.recentQtrCurrentRatioForPeriod1", "ZK15.longTermDebtToEquityForPriorQTR(Q-1)",
			"ZK15.totalDebtToEquityForPriorQTR(Q-1)", "CZ1.priceEarningsRatio2", "CZ1.currentPriceByTtmOfTotalRevenues",
			"CZ1.currentPriceByCurrentQtrBookValue", "CZ1.currentPriceByCurrentAnnualCashFlow", "ZK3.twelveMonthsSale",
			"ZK3.bookValuePerShare", "ZK3.cashFlow", "ZK3.returnOnEquityLatestQtr", "ZK3.returnOnAssetsLatestQtr",
			"ZK3.returnOnInvestmentLatestQtr", "ZK3.pretaxMargin12Months", "ZK3.operatingMargin12Months",
			"ZK3.netMargin12Months" };

	private final String[] includeFinancialHighlights = { "DVR.percentageRateOfDividendGrowth",
			"DVR.avgAnnualPercRateOfDividendGrowthInLast3Years", "DVR.avgAnnualPercRateOfDividendGrowthInLast5Years",
			"EPS PERCENT.f2Eps", "EPS PERCENT.f1Eps", "EPS PERCENT.f0Eps", "EPS PERCENT.f3Eps", "EPS PERCENT.f4Eps",
			"EPS PERCENT.f5Eps", "SALES PERC.f5Sales"," SALES PERC.f6Sales","SALES PERC.f2Sales", "SALES PERC.f1Sales", "SALES PERC.f3Sales",
			"SALES PERC.f4Sales", "SALES PERC.f0Sales", "SALES.q17Sales", "SALES.q13Sales", "SALES.q9Sales",
			"SALES.q5Sales", "SALES.q1Sales", "SALES.q16Sales", "SALES.q12Sales", "SALES.q8Sales", "SALES.q4Sales",
			"SALES.q15Sales", "SALES.q11Sales", "SALES.q7Sales", "SALES.q3Sales", "SALES.q14Sales", "SALES.q10Sales",
			"SALES.q8Sales", "SALES.q2Sales", "EPS.q13Eps", "EPS.q9Eps", "EPS.q5Eps", "EPS.q1Eps", "EPS.q12Eps",
			"EPS.q8Eps", "EPS.q4Eps", "EPS.q11Eps", "EPS.q7Eps", "EPS.q3Eps", "EPS.q10Eps", "EPS.q6Eps", "EPS.q2Eps",
			"CZ1.fiscalYearEndMonth", "Sales Perc.lastSalesYear", "EPS PERCENT.lastEpsYear",
			"EPS.fiscalQuaterEndMonth" };

	public Document modifyDoc(Document doc, String ticker) {
		Document modifiedDoc = new Document();
		modifiedDoc.putAll(new Document("Ticker", ticker));
		for (Object obj : doc.values()) {
			Document childdoc = (Document) obj;
			modifiedDoc.putAll(childdoc);
		}
		return modifiedDoc;
	}

	public Document getData(String ticker) {
		Document doc = dataAccessor.findDocwithTicker(ticker).first();
		return doc;
	}

	/**
	 * This method get Document from method
	 * {@link com.zversal.api.database.DataAccessorImpl#findDocWithChannelAndProjection(String, String)}
	 * This method is used to get the Tickers(Unique Id) of All Documents which
	 * contains Channel given in @param channel.
	 * 
	 * @param ticker- Unique Id of Document
	 * @return Document which contains Tickers of All Documents which contains
	 *         Channel given in @param channel
	 */
	public Document getTicker(String channel) {
		// MongoCursor<Document> itr =
		// dataAccessor.findDocWithChannelAndProjection(channel, "Ticker").iterator();
		FindIterable<Document> doc = dataAccessor.findDocWithChannelAndProjection(channel, "Ticker");
		List<Object> listDoc = new ArrayList<>();
		Document document = new Document();
		for (Document docs : doc) {
			// doc = itr.next();
			listDoc.add(docs.get("Ticker"));
		}
		System.out.println("ListDOC:::" + listDoc);
		document.put("Ticker", listDoc);
		return document;
	}

	/**
	 * This method get Documents from method
	 * {@link com.zversal.api.database.DataAccessorImpl#FindDocWithTickerAndProjection(String, String[])}
	 * This method is used to get the Documents with specified field given in array
	 * of type String include.
	 * 
	 * @param ticker - Unique Id of Document
	 * @return {@link org.bson.Document}
	 */
	public Document getEarningData(String ticker) {
		Document doc = dataAccessor.FindDocWithTickerAndProjection(ticker, includeEarning);
		/*
		 * Document doc= document.first(); Document doc2= document.first(); Document
		 * doc3 = document.first(); Document doc4 = document.first();
		 */
		if (doc != null) {
			return modifyDoc(doc, ticker);
		} else {
			return null;
		}
	}

	/**
	 * This method get Documents from method
	 * {@link com.zversal.api.database.DataAccessorImpl#FindDocWithTickerAndProjection(String, String[])}
	 * This method is used to get the Documents with specified field given in array
	 * of type String include.
	 * 
	 * @param ticker - Unique Id of Document
	 * @return {@link org.bson.Document}
	 */
	public Document getSnapshot(String ticker) {
		Document doc = dataAccessor.FindDocWithTickerAndProjection(ticker, includeSnapshot);
		/* Document doc= document.first(); */
		// Document doc = dataAccessor.FindDocWithTickerAndProjection(ticker,
		// includeSnapshot).first();
		/*
		 * Document doc= document.first(); Document doc2= document.first();
		 */
		if (doc != null) {
			return modifyDoc(doc, ticker);
		} else {
			return null;
		}
	}

	/**
	 * This method get Documents from method
	 * {@link com.zversal.api.database.DataAccessorImpl#FindDocWithTickerAndProjection(String, String[])}
	 * This method is used to get the Documents with specified field given in array
	 * of type String include.
	 * 
	 * @param ticker - Unique Id of Document
	 * @return {@link org.bson.Document}
	 */
	public Document getAnalystCoverage(String ticker) {

		Document doc = dataAccessor.FindDocWithTickerAndProjection(ticker, includeAnalystCoverage);/* .first(); */
		/* Document doc=document.first(); */
		if (doc != null) {
			return modifyDoc(doc, ticker);
		} else {
			return null;
		}
	}

	public Document getKeyStatsAndFinancials(String ticker) {
		Document doc = dataAccessor.FindDocWithTickerAndProjection(ticker, includeKeyStatsAndFinancials);/* .first(); */
		/* Document doc=document.first(); */
		if (doc != null) {
			return modifyDoc(doc, ticker);
		} else {
			return null;
		}

	}

	public Document getFinancialHighlights(String ticker) {
		Document doc = dataAccessor.FindDocWithTickerAndProjection(ticker, includeFinancialHighlights);/* .first(); */
		/* Document doc=document.first(); */
		if (doc != null) {
			Document document = modifyDoc(doc, ticker);
			String fiscalEndMonthS = (String) document.get("fiscalQuaterEndMonth");
			int fiscalEndMonth = Integer.parseInt(fiscalEndMonthS);
			if (fiscalEndMonth <= 3) {
				document.append("firstQuarter", fiscalEndMonth);
				document.append("secondQuarter", fiscalEndMonth + 3);
				document.append("thirdQuarter", fiscalEndMonth + 6);
				document.append("fourthQuarter", fiscalEndMonth + 9);
			} else if (fiscalEndMonth <= 6) {
				document.append("firstQuarter", fiscalEndMonth - 3);
				document.append("secondQuarter", fiscalEndMonth);
				document.append("thirdQuarter", fiscalEndMonth + 3);
				document.append("fourthQuarter", fiscalEndMonth + 6);
			} else if (fiscalEndMonth <= 9) {
				document.append("firstQuarter", fiscalEndMonth - 6);
				document.append("secondQuarter", fiscalEndMonth - 3);
				document.append("thirdQuarter", fiscalEndMonth);
				document.append("fourthQuarter", fiscalEndMonth + 3);
			} else {
				document.append("firstQuarter", fiscalEndMonth - 9);
				document.append("secondQuarter", fiscalEndMonth - 6);
				document.append("thirdQuarter", fiscalEndMonth - 3);
				document.append("fourthQuarter", fiscalEndMonth);
			}
              
			String S_f0 = (String) document.get("f0Eps");
			String S_f1 = (String) document.get("f1Eps");
			String S_f2 = (String) document.get("f2Eps");
			String S_f3 = (String) document.get("f3Eps");
			String S_f4 = (String) document.get("f4Eps");
			String S_f5 = (String) document.get("f5Eps");
			String S_f6 = (String) document.get("f6Eps");
			
			double L_f0=0;
			double L_f1=0;
			double L_f3=0;
			double L_f5=0;
			
			double annualEps=0;
			double threeYearEps=0;
			double fiveYearEps=0;
			
			if((S_f0!=null)&&(S_f0.length()>1)){
				 L_f0= Double.parseDouble(S_f0);
				if((S_f1!=null)&&(S_f1.length()>1)) {
				 L_f1= Double.parseDouble(S_f1);
				}
				if((S_f3!=null)&&(S_f3.length()>1)) {
					 L_f3= Double.parseDouble(S_f3);
				}
				if((S_f5!=null)&&(S_f5.length()>1)) {
					 L_f5= Double.parseDouble(S_f5);
				}
			}
				else if((S_f1!=null)&&(S_f1.length()>1)) {
					 L_f0= Double.parseDouble(S_f1);
					if((S_f2!=null)&&(S_f2.length()>1)) {
						 L_f1= Double.parseDouble(S_f2);
					}
					if((S_f4!=null)&&(S_f4.length()>1)) {
						 L_f3= Double.parseDouble(S_f4);
					}
					if((S_f6!=null)&&(S_f6.length()>1)) {
					 L_f5= Double.parseDouble(S_f6);
					}
				}
			if(L_f1!=0) {
			annualEps=Math.round((((L_f0/L_f1)-1)*100) * 10000.0) / 10000.0;
			}
			if(L_f3!=0) {
				double L_DIV1=L_f0/L_f3;
				threeYearEps=Math.round(((Math.pow(Math.abs(L_DIV1),0.33333333333)-1)*100)*10000.0)/10000.0;
			}
			if(L_f5!=0) {
				double L_DIV2=L_f0/L_f5;
				fiveYearEps=Math.round(((Math.pow(Math.abs(L_DIV2),0.2)-1)*100)*10000.0)/10000.0;
			}
			else {
				annualEps=999.0000;
				threeYearEps=999.0000;
				fiveYearEps=999.0000;
			}
			document.append("AnnualEps",annualEps);
			document.append("threeYearEps",threeYearEps);
			document.append("fiveYearEps",fiveYearEps);		
			
			
			String Sa_f0 = (String) document.get("f0Sales");
			String Sa_f1 = (String) document.get("f1Sales");
			String Sa_f2 = (String) document.get("f2Sales");
			String Sa_f3 = (String) document.get("f3Sales");
			String Sa_f4 = (String) document.get("f4Sales");
			String Sa_f5 = (String) document.get("f5Sales");
			String Sa_f6 = (String) document.get("f6Sales");
			
			double Ls_f0=0;
			double Ls_f1=0;
			double Ls_f3=0;
			double Ls_f5=0;
			
			double annualSales=0;
			double threeYearSales=0;
			double fiveYearSales=0;
			
			if((Sa_f0!=null)&&(Sa_f0.length()>1)){
				 Ls_f0= Double.parseDouble(Sa_f0);
				if((Sa_f1!=null)&&(Sa_f1.length()>1)) {
				 Ls_f1= Double.parseDouble(Sa_f1);
				}
				if((Sa_f3!=null)&&(Sa_f3.length()>1)) {
					 Ls_f3= Double.parseDouble(Sa_f3);
				}
				if((Sa_f5!=null)&&(Sa_f5.length()>1)) {
					 Ls_f5= Double.parseDouble(Sa_f5);
				}
			}
				else if((Sa_f1!=null)&&(Sa_f1.length()>1)) {
					 Ls_f0= Double.parseDouble(Sa_f1);
					if((Sa_f2!=null)&&(Sa_f2.length()>1)) {
						 Ls_f1= Double.parseDouble(Sa_f2);
					}
					if((Sa_f4!=null)&&(Sa_f4.length()>1)) {
						 Ls_f3= Double.parseDouble(Sa_f4);
					}
					if((Sa_f6!=null)&&(Sa_f6.length()>1)) {
					 Ls_f5= Double.parseDouble(Sa_f6);
					}
				}
			if(Ls_f1!=0) {
			annualSales=Math.round((((Ls_f0/Ls_f1)-1)*100) * 10000.0) / 10000.0;
			}
			if(Ls_f3!=0) {
				double L_DIV1=Ls_f0/Ls_f3;
				threeYearSales=Math.round(((Math.pow(Math.abs(L_DIV1),0.33333333333)-1)*100)*10000.0)/10000.0;
			}
			if(Ls_f5!=0) {
				double L_DIV2=Ls_f0/Ls_f5;
				fiveYearSales=Math.round(((Math.pow(Math.abs(L_DIV2),0.2)-1)*100)*10000.0)/10000.0;
			}
			else {
				annualSales=999.0000;
				threeYearSales=999.0000;
				fiveYearSales=999.0000;
			}
			document.append("AnnualSales",annualSales);
			document.append("threeYearSales",threeYearSales);
			document.append("fiveYearSales",fiveYearSales);			
			return document;
		} else {
			return null;
		}

	}

}
