package com.project.Businessinformatics.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Businessinformatics.model.AnalyticalStatement;
import com.project.Businessinformatics.service.AnaltyicalStatementService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/analyticalStatements")
public class AnalyticalStatementController {

	private final AnaltyicalStatementService analyticalStatementService;

	@Autowired
	public AnalyticalStatementController(AnaltyicalStatementService analyticalStatementService) {
		this.analyticalStatementService = analyticalStatementService;
	}

	@PostMapping("/create/{currencyId}/{cityId}/{dateOfReceipt}/{currencyDate}")
	@ResponseBody
	public Collection<AnalyticalStatement> createAnalyticalStatement(@PathVariable("currencyId") String currencyId,
			 @PathVariable("cityId") String cityId,
			@PathVariable("dateOfReceipt") String dateOfReceipt, @PathVariable("currencyDate") String currencyDate,
			@RequestBody AnalyticalStatement analyticalStatement) throws DatatypeConfigurationException, JAXBException {
		return analyticalStatementService.createAnalyticalStatement(currencyId, cityId,
				dateOfReceipt, currencyDate, analyticalStatement);
	}

	@GetMapping
	@ResponseBody
	public Collection<AnalyticalStatement> getAnalyticalStatements() {
		return analyticalStatementService.getAnalyticalStatements();
	}

	@GetMapping("/{id}")
	@ResponseBody
	public AnalyticalStatement getAnalyticalStatement(@PathVariable("id") Long id) {
		return analyticalStatementService.getAnalyticalStatement(id);
	}

    @RequestMapping(value = "/export/{accountId}/{startDate}/{endDate}", method = RequestMethod.GET)
    public void exportToPdf(@PathVariable("accountId") Long accountId,@PathVariable("startDate") Date start,@PathVariable("endDate") Date end,HttpServletResponse response) throws ParseException, SQLException, IOException, JRException {
	    analyticalStatementService.exportToPdf(accountId, start,end,response);
    }
  
    @RequestMapping(value = "/exportxml/{accountId}/{startDate}/{endDate}", method = RequestMethod.GET)
    public void exportToXml(@PathVariable("accountId") Long accountId,@PathVariable("startDate") Date start,@PathVariable("endDate") Date end,HttpServletResponse response) throws ParseException, SQLException, IOException, JRException {
	    analyticalStatementService.exportToXml(accountId, start,end,response);
    }
    
    @PostMapping("/generateMt102Request/{currencyId}/{cityId}/{dateOfReceipt}/{currencyDate}")
	@ResponseBody
	public void generateMt102Request(@PathVariable("currencyId") String currencyId,
			 @PathVariable("cityId") String cityId,
			@PathVariable("dateOfReceipt") String dateOfReceipt, @PathVariable("currencyDate") String currencyDate,
			@RequestBody AnalyticalStatement analyticalStatement) throws DatatypeConfigurationException, JAXBException {
	}
	  
}