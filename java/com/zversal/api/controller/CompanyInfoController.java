package com.zversal.api.controller;

import javax.servlet.http.HttpServletResponse;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.zversal.api.service.CompanyInfoServiceImpl;

/**
 * This class exposes the REST API for the system.
 * 
 * @author Bhupinder
 * @RestController takes care of mapping request data to the defined request
 *                 handler method. Once response body is generated from the
 *                 handler method, it converts it to JSON response.
 *
 */
@RestController
@RequestMapping("/companyinfo")
public class CompanyInfoController {
	@Autowired
	private CompanyInfoServiceImpl companyInfoService;

	/**
	 * This method will be used to get All Document which contains the Tickers given in parameter from method {@link com.zversal.api.service.CompanyInfoServiceImpl#getData(String)} .
	 * @param ticker the unique id of the Document
	 * @param response of Type HttpServletResponse provides HTTP-specific functionality in sending a response. 
	 * @return a BSON Document
	 */
	@RequestMapping(value = "/data/{Ticker}", method = RequestMethod.GET)
	public Document getDataApi(@PathVariable("Ticker") String ticker, HttpServletResponse response) {
		Document doc = companyInfoService.getData(ticker.toUpperCase());
		if (doc == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			response.addHeader("Error", "No Content - Invalid Ticker");
			return doc;
		} else {
			return doc;
		}
	}

	/**
	 * This method will be used to get Tickers of All the Document which contains Channel given in parameters from method {@link com.zversal.api.service.CompanyInfoServiceImpl#getTicker(String)}
	 * @param ticker the unique id of the Document
	 * @param response of Type HttpServletResponse provides HTTP-specific functionality in sending a response. 
	 * @return a BSON Document
	 */
	@RequestMapping(value = "/gettickers/{Channel}", method = RequestMethod.GET)
	public Document getTickerApi(@PathVariable("Channel") String channel, HttpServletResponse response) {
		Document doc = companyInfoService.getTicker(channel.toUpperCase());
		if (doc == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			response.addHeader("Error", "No Content - Invalid Channel");
			return doc;
		} else {
			return doc;
		}
	}

	/**
	 * This method will be used to get earnings Api from {@link com.zversal.api.service.CompanyInfoServiceImpl#getEarningData(String)}
	 * @param ticker the unique id of the Document
	 * @param response of Type HttpServletResponse provides HTTP-specific functionality in sending a response. 
	 * @return a BSON Document
	 */
	@RequestMapping(value = "/earnings/{Ticker}", method = RequestMethod.GET)
	public Document getEarningDataApi(@PathVariable("Ticker") String ticker, HttpServletResponse response) {
		Document doc = companyInfoService.getEarningData(ticker.toUpperCase());
		if (doc == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			response.addHeader("Error", "No Content - Invalid Ticker");
			return doc;
		} else {
			return doc;
		}
	}
	/**
	 * This method will be used to get snapshot Api from method {@link com.zversal.api.service.CompanyInfoServiceImpl#getSnapshot(String)}
	 * @param ticker the unique id of the Document
	 * @param response of Type HttpServletResponse provides HTTP-specific functionality in sending a response. 
	 * @return a BSON Document
	 */
	@RequestMapping(value = "/snapshot/{Ticker}", method = RequestMethod.GET)
	public Document getSnapshotApi(@PathVariable("Ticker") String ticker, HttpServletResponse response) {
		Document doc = companyInfoService.getSnapshot(ticker.toUpperCase());
		if (doc == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			response.addHeader("Error", "No Content - Invalid Ticker");
			return doc;
		} else {
			return doc;
		}
	}

	/**
	 * This method will be used to get Analyst Coverage Api from method {@link com.zversal.api.service.CompanyInfoServiceImpl#getAnalystCoverage(String)}
	 * @param ticker the unique id of the Document
	 * @param response of Type HttpServletResponse provides HTTP-specific functionality in sending a response. 
	 * @return a BSON Document
	 */
	@RequestMapping(value = "/analystcoverage/{Ticker}", method = RequestMethod.GET)
	public Document getAnalystApi(@PathVariable("Ticker") String ticker, HttpServletResponse response) {
		Document doc = companyInfoService.getAnalystCoverage(ticker.toUpperCase());
		if (doc == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			response.addHeader("Error", "No Content - Invalid Ticker");
			return doc;
		} else {
			return doc;
		}
	}
	@RequestMapping(value = "/keystatsandfinancials/{Ticker}", method = RequestMethod.GET)
	public Document getKeyStatsAndFinancials(@PathVariable("Ticker") String ticker, HttpServletResponse response) {
		Document doc = companyInfoService.getKeyStatsAndFinancials(ticker.toUpperCase());
		if (doc == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			response.addHeader("Error", "No Content - Invalid Ticker");
			return doc;
		} else {
			return doc;
		}
	}

}
