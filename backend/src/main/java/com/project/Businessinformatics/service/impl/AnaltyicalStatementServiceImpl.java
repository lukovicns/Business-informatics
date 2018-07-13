package com.project.Businessinformatics.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.Businessinformatics.model.Account;
import com.project.Businessinformatics.model.AnalyticalStatement;
import com.project.Businessinformatics.model.AnalyticalStatementMode;
import com.project.Businessinformatics.model.xml.ClientStatement;
import com.project.Businessinformatics.repository.AnalyticalStatementRepository;
import com.project.Businessinformatics.service.AccountService;
import com.project.Businessinformatics.service.AnaltyicalStatementService;
import com.project.Businessinformatics.service.CityService;
import com.project.Businessinformatics.service.CurrencyService;
import com.project.Businessinformatics.service.InterBankService;
import com.project.Businessinformatics.util.XmlPaths;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
@Transactional
public class AnaltyicalStatementServiceImpl implements AnaltyicalStatementService {
	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private InterBankService interBankService;
	
	@Autowired
	DataSource dataSource;	

	@Autowired
	private AnalyticalStatementRepository analyticalStatementRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Collection<AnalyticalStatement> getAnalyticalStatements() {
		return analyticalStatementRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public AnalyticalStatement getAnalyticalStatement(Long id) {
		return analyticalStatementRepository.getOne(id);
	}
	
	@Override
	public Collection<AnalyticalStatement> createAnalyticalStatement(String currencyId, String cityId,
			String dateOfReceipt, String currencyDate, AnalyticalStatement analyticalStatement) throws DatatypeConfigurationException, JAXBException {
		
		if (currencyId != null && !currencyId.trim().equals("NOT_ENTERED"))
			analyticalStatement.setCurrency(currencyService.getCurrency(new Long(currencyId)));
		
		if (cityId != null && !cityId.trim().equals("NOT_ENTERED"))
			analyticalStatement.setPlaceOfAcceptance(cityService.getCity(new Long(cityId)));
		
		analyticalStatement.setDateOfReceipt(dateOfReceipt.toString());
		analyticalStatement.setCurrencyDate(currencyDate.toString());
		analyticalStatement.setPayment(false);
		
		return this.doTransaction(analyticalStatement);
	}
	
	public Collection<AnalyticalStatement> doTransaction(AnalyticalStatement analyticalStatement) throws JAXBException, DatatypeConfigurationException
	{	
		ArrayList<AnalyticalStatement> analyticalStatements = new ArrayList<>();
		
		if (analyticalStatement.getAnalyticalStatementMode() == AnalyticalStatementMode.TRANSFER) 
		{
			if (!analyticalStatement.getOriginatorAccount().substring(0, 3)
					.equals(analyticalStatement.getRecipientAccount().substring(0, 3))) {
				
				analyticalStatements.add(analyticalStatementRepository.save(analyticalStatement));
				interBankService.RTGSOrClearing(analyticalStatement);
				
			} else {
				analyticalStatements.add(analyticalStatementRepository.save(analyticalStatement));
				
				File file = new File(XmlPaths.getXmlPath() + "analyticalStatement" + analyticalStatement.getId() + ".xml");
				JAXBContext jaxbContext = JAXBContext.newInstance(AnalyticalStatement.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(analyticalStatement, file);
				jaxbMarshaller.marshal(analyticalStatement, System.out);

			}
			
		} else {
			
			analyticalStatements.add(analyticalStatementRepository.save(analyticalStatement));
			
			File file = new File(XmlPaths.getXmlPath() + "analyticalStatement" + analyticalStatement.getId() + ".xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(AnalyticalStatement.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(analyticalStatement, file);
			jaxbMarshaller.marshal(analyticalStatement, System.out);

		}
		
		return analyticalStatements;
	}

	public AnalyticalStatement cloneAnalyticalStatement(AnalyticalStatement as) {
		AnalyticalStatement clone = new AnalyticalStatement();
		clone.setAmount(as.getAmount());
		clone.setApprovalAuthorizationNumber(as.getApprovalAuthorizationNumber());
		clone.setApprovalModel(as.getApprovalModel());
		clone.setCurrency(as.getCurrency());
		clone.setCurrencyDate(as.getCurrencyDate());
		clone.setDebitAuthorizationNumber(as.getDebitAuthorizationNumber());
		clone.setErrorType(as.getErrorType());
		clone.setModel(as.getModel());
		clone.setOriginator(as.getOriginator());
		clone.setOriginatorAccount(as.getOriginatorAccount());
		clone.setPlaceOfAcceptance(as.getPlaceOfAcceptance());
		clone.setPurpose(as.getPurpose());
		clone.setDateOfReceipt(as.getDateOfReceipt());
		clone.setRecipient(as.getRecipient());
		clone.setRecipientAccount(as.getRecipientAccount());
		clone.setUrgently(as.isUrgently());
		clone.setAnalyticalStatementMode(as.getAnalyticalStatementMode());
		return clone;
	}

	@Override
	public void exportToPdf(Long accountId, Date startDate, Date endDate, HttpServletResponse response)
			throws JRException, SQLException, IOException {
		Account a = accountService.getAccount(accountId);
		Map<String, Object> params = new HashMap<>();
		params.put("bankAccount", a.getAccountNumber());
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("client", a.getClient().getName() + " " + a.getClient().getSurname());
//		URL url = this.getClass().getClassLoader().getResource("jasper/logo.png");
//		params.put("logo", url
		FileInputStream fileInputStream;
		params.put("address", a.getClient().getAddress());
		URL filePath = this.getClass().getClassLoader().getResource("jasper/BankReport.jasper");
		System.out.println("file "+filePath.openStream());
		JasperPrint jp = JasperFillManager.fillReport(filePath.openStream(),
				params, dataSource.getConnection());
		File pdf = new File(
				System.getProperty("user.home") + "/Downloads/" + "izvestaj-" + a.getAccountNumber() + ".pdf");
		FileOutputStream out = new FileOutputStream(pdf);
		JasperExportManager.exportReportToPdfStream(jp, out);
		fileInputStream = new FileInputStream(pdf);
		response.setContentLengthLong(pdf.length());
		IOUtils.copy(fileInputStream, response.getOutputStream());
		fileInputStream.close();
		out.close();
		response.flushBuffer();
		pdf.delete();
	}
	
	@Override
	public void exportToXml(Long accountId, Date startDate, Date endDate, HttpServletResponse response)
			throws JRException, SQLException, IOException, ParseException {
		JAXBContext context;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			startDate = cal.getTime();
			
			cal.setTime(endDate);
			cal.set(Calendar.HOUR, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 59);
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
			endDate = cal.getTime();
			
			context = JAXBContext.newInstance(ClientStatement.class);
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			Account account =  accountService.getAccount(accountId);
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			String formattedStartDate = dateFormatter.format(startDate);
			String formattedEndDate = dateFormatter.format(endDate);
			List<AnalyticalStatement> statements = analyticalStatementRepository.findClientStatements(account.getAccountNumber(), formattedStartDate, formattedEndDate);
			
			ClientStatement clientStatement = new ClientStatement();
			clientStatement.setAccount(account);
			clientStatement.setStatements(statements);
			
			System.out.println("[INFO] Marshalling...");
			OutputStream os;
			Date now = new Date();
			
			String name = "Client Statement - " + now.toString() + " - " + account.getAccountNumber() + ".xml";
			name = name.replace(":", "-");
			try {
				os = new FileOutputStream(name);
				marshaller.marshal(clientStatement, os);
				os.close();
				os.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			FileInputStream fileInputStream;
			File pdf = new File(name);
			fileInputStream = new FileInputStream(pdf);
			response.setContentLengthLong(pdf.length());
			IOUtils.copy(fileInputStream, response.getOutputStream());
			fileInputStream.close();
			response.flushBuffer();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

//	private List<AnalyticalStatement> findClientStatements(String accountNumber, Date startDate, Date endDate) throws ParseException {
//		List<AnalyticalStatement> clientStatements = new ArrayList<AnalyticalStatement>();
//		for (AnalyticalStatement as : getAnalyticalStatements()) {
//			Date dateOfReceipt = new SimpleDateFormat("yyyy-MM-dd").parse(as.getDateOfReceipt());
//			if (as.getOriginatorAccount().equals(accountNumber)/* && dateOfReceipt.compareTo(startDate) >= 0 && dateOfReceipt.compareTo(endDate) <= 0*/) {
//				clientStatements.add(as);
//			}
//		}
//		return clientStatements;
//	}

	@Override
	public void delete(Long id) {
		analyticalStatementRepository.deleteById(id);
	}

}
