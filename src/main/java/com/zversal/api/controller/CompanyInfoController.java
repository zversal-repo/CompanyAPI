package com.zversal.api.controller;

import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.zversal.api.service.CompanyInfoServiceImpl;

@RestController
@RequestMapping("/companyinfo")
public class CompanyInfoController {
	@Autowired
	private CompanyInfoServiceImpl infoServ;

	@RequestMapping(value = "/data/{Ticker}", method = RequestMethod.GET)
	public Document getData(@PathVariable("Ticker") String ticker,HttpServletResponse response) {
		Document doc = infoServ.getData(ticker);
		if (doc == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			response.addHeader("Error", "No Content - Invalid Ticker");
			return doc;
		} else {
			return doc;
		}
	}

	@RequestMapping(value = "/gettickers/{Channel}", method = RequestMethod.GET)
	public Document getTicker(@PathVariable("Channel") String channel,HttpServletResponse response) {
		Document doc = infoServ.getTicker(channel);
		if (doc == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			response.addHeader("Error", "No Content - Invalid Channel");
			return doc;
		} else {
			return doc;
		}
	}

	@RequestMapping(value = "/earnings/{Ticker}", method = RequestMethod.GET)
	public Document getEarningData(@PathVariable("Ticker") String ticker,HttpServletResponse response) {
		Document doc = infoServ.getEarningData(ticker);
		if (doc == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			response.addHeader("Error", "No Content - Invalid Ticker");
			return doc;
		} else {
			return doc;
		}
	}

	@RequestMapping(value = "/snapshot/{Ticker}", method = RequestMethod.GET)
	public Document getSnapshot(@PathVariable("Ticker") String ticker,HttpServletResponse response) {
		Document doc = infoServ.getSnapshot(ticker);
		if (doc == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			response.addHeader("Error", "No Content - Invalid Ticker");
			return doc;
		} else {
			return doc;
		}
	}

	@RequestMapping(value = "/analystcoverage/{Ticker}", method = RequestMethod.GET)
	public Document getAnalyst(@PathVariable("Ticker") String ticker,HttpServletResponse response) {
		Document doc = infoServ.getAnalystCoverage(ticker);
		if (doc == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			response.addHeader("Error", "No Content - Invalid Ticker");
			return doc;
		} else {
			return doc;
		}
	}

}
