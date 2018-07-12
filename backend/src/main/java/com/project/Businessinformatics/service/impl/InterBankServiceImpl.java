package com.project.Businessinformatics.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.project.Businessinformatics.model.AnalyticalStatement;
import com.project.Businessinformatics.model.Bank;
import com.project.Businessinformatics.model.Currency;
import com.project.Businessinformatics.model.xml.ClearingAndSettlementItem;
import com.project.Businessinformatics.model.xml.ClearingSettlementRequest;
import com.project.Businessinformatics.model.xml.Mt102Request;
import com.project.Businessinformatics.model.xml.Mt103Request;
import com.project.Businessinformatics.model.xml.RTGSRequest;
import com.project.Businessinformatics.repository.CurrencyExchangeRepository;
import com.project.Businessinformatics.repository.RTGSRequestRepository;
import com.project.Businessinformatics.service.BankService;
import com.project.Businessinformatics.service.ClearingSettlementRequestService;
import com.project.Businessinformatics.service.InterBankService;
import com.project.Businessinformatics.service.Mt102Service;
import com.project.Businessinformatics.service.Mt103Service;

@Service
public class InterBankServiceImpl implements InterBankService {

	private static ConcurrentHashMap<String, Mt102Request> clearingSettlementRequests = new ConcurrentHashMap<>();
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	
	@Autowired
	private RTGSRequestRepository rtgsRequestRepository;
	
	@Autowired
	private ClearingSettlementRequestService clearingAndSettlementRequestService;
	
	@Autowired
	private Mt103Service mt103Service;
	
	@Autowired
	private Mt102Service mt102Service;
	
	@Override
	public void generateRTGSRequest(AnalyticalStatement as) throws DatatypeConfigurationException {
		
		RTGSRequest req = new RTGSRequest();
		req.setAnalyticalStatement(as);
		Bank paymentBank = bankService.findBankByLeadNumber(as.getOriginatorAccount().substring(0, 3));
		req.setPaymentBank(paymentBank);
		Bank receiverBank = bankService.findBankByLeadNumber(as.getRecipientAccount().substring(0, 3));
		req.setRecieverBank(receiverBank);
		req.setMessageId("MT103");
		
		rtgsRequestRepository.save(req);

		try {
			
			Mt103Request mt103Request = mapAnalyticalStatementToMt103Request(as, paymentBank, receiverBank);
			
			File file = new File("G:\\rtgs" + mt103Request.getId() + ".xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Mt103Request.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(mt103Request, file);
			jaxbMarshaller.marshal(mt103Request, System.out);

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addToClearing(AnalyticalStatement as) throws JAXBException 
	{
		Bank receiverBank = bankService.findBankByLeadNumber(as.getRecipientAccount().substring(0, 3));
		Bank paymentBank = bankService.findBankByLeadNumber(as.getOriginatorAccount().substring(0, 3));
		if(!clearingSettlementRequests.containsKey(receiverBank.getSwift() + ":" + as.getCurrency().getOfficialCode()))
		{
			System.out.println("usao "+receiverBank.getSwift() + ":" + as.getCurrency().getOfficialCode());
			ClearingSettlementRequest csr = new ClearingSettlementRequest();
			csr.setPaymentBank(paymentBank);
			csr.setRecieverBank(receiverBank);
			csr.setTotalAmount(as.getAmount());
			csr.setCurrency(as.getCurrency());
			csr.setCurrencyDate(as.getCurrencyDate());
			csr.setDate(new Date().toString());
			
			HashSet<AnalyticalStatement> tempAS = new HashSet<>();
			tempAS.add(as);
			csr.setAnalyticalStatements(tempAS);
			
			clearingAndSettlementRequestService.save(csr);
			
			try{
				
				makeMt102Request(paymentBank, receiverBank, as.getCurrency(), as.getCurrencyDate());
				
			}catch (DatatypeConfigurationException e) {
				
				e.printStackTrace();
			}
			
		}else{
			
			ClearingSettlementRequest clearingSettlementRequest = clearingAndSettlementRequestService.search(paymentBank.getId(), receiverBank.getId());
			clearingSettlementRequest.getAnalyticalStatements().add(as);
			clearingAndSettlementRequestService.save(clearingSettlementRequest);
		}
		
		try{
			
			ClearingAndSettlementItem clearingAndSettlementItem = this.mapAnalyticalStatementToClearingAndSetlmentItem(as);
			Mt102Request mt102Request = clearingSettlementRequests.get(receiverBank.getSwift()+":"+as.getCurrency().getOfficialCode());
			
			mt102Request.setAmount(new BigDecimal(mt102Request.getAmount().doubleValue()+as.getAmount()));
			Set<ClearingAndSettlementItem> items = mt102Request.getStatementItems();
			
			items.add(clearingAndSettlementItem);
			mt102Request.setStatementItems(items);
			
			clearingAndSettlementItem.setMt102Request(mt102Request);
			
		}catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	@Scheduled(cron = "0 30 10,12 ? * *")
//	@Scheduled(fixedRate = 15000)
	@Override
	public void generateClearingService() throws JAXBException {
		
		if(!clearingSettlementRequests.isEmpty()){
			
			for(Map.Entry<String, Mt102Request> pair : clearingSettlementRequests.entrySet()){
				System.out.println("statements "+pair.getValue().getStatementItems().size());
				File file = new File("G:\\kliring" + pair.getValue().getId() + ".xml");
				JAXBContext jaxbContext = JAXBContext.newInstance(Mt102Request.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(pair.getValue(), file);
				jaxbMarshaller.marshal(pair.getValue(), System.out);
			}
		}
		clearingSettlementRequests.clear();
	}
	
	@Scheduled(cron = "0 45 14 ? * *")
	@Override
	public void generateLastClearingService() throws JAXBException {
		
		if(!clearingSettlementRequests.isEmpty()){
			
			for(Map.Entry<String, Mt102Request> pair : clearingSettlementRequests.entrySet()){
				File file = new File("G:\\kliring" + pair.getValue().getId() + ".xml");
				JAXBContext jaxbContext = JAXBContext.newInstance(Mt102Request.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(pair.getValue(), file);
				jaxbMarshaller.marshal(pair.getValue(), System.out);
			}
		}
		clearingSettlementRequests.clear();
	}

	@Override
	public void RTGSOrClearing(AnalyticalStatement as) throws DatatypeConfigurationException, JAXBException{
		double sum;
		
		if(!as.getCurrency().getOfficialCode().equals("DIN")){
			sum = as.getAmount() * currencyExchangeRepository.findMiddleRateAccordingToDinars(as.getCurrency().getOfficialCode());
		} else {
			sum = as.getAmount();
		}
		
		if(sum >= 250000 || as.isUrgently()){
			generateRTGSRequest(as);
		} else {
			addToClearing(as);
		}
	}
		
	private Mt103Request mapAnalyticalStatementToMt103Request(AnalyticalStatement as, Bank originatorBank, Bank recieverBank) throws DatatypeConfigurationException{
		
		Mt103Request mt103Request = new Mt103Request();
		mt103Request.setMessageId("mt103");
		mt103Request.setAmount(new BigDecimal(as.getAmount()));
		mt103Request.setChargeDialNumber(as.getDebitAuthorizationNumber());
		mt103Request.setChargeModel(as.getModel());
		mt103Request.setClearanceDialNumber(as.getApprovalAuthorizationNumber());
		mt103Request.setClearanceModel(as.getApprovalModel());
		mt103Request.setCurrency(as.getCurrency().getOfficialCode());
	
		mt103Request.setCurrencyDate(as.getCurrencyDate());
		
		mt103Request.setOriginator(as.getOriginator());
		mt103Request.setOriginatorAccountNumber(as.getOriginatorAccount());
		mt103Request.setOriginatorBankSwiftCode(originatorBank.getSwift());
		mt103Request.setOriginatorBankTransactionAccount(originatorBank.getTransactionAccount());
		mt103Request.setPaymentPurpose(as.getPurpose());
		mt103Request.setReciever(as.getRecipient());
		mt103Request.setRecieverAccountNumber(as.getRecipientAccount());
		mt103Request.setRecieverBankSwiftCode(recieverBank.getSwift());
		mt103Request.setRecieverBankTransactionAccount(recieverBank.getTransactionAccount());
		mt103Request.setStatementDate(as.getDateOfReceipt());
		
		mt103Service.save(mt103Request);
		
		return mt103Request;
	}
	
	private ClearingAndSettlementItem mapAnalyticalStatementToClearingAndSetlmentItem(AnalyticalStatement as) throws DatatypeConfigurationException{
		
		ClearingAndSettlementItem clearingAndSettlementItem = new ClearingAndSettlementItem();
		clearingAndSettlementItem.setRequestId(as.getId().toString());
		clearingAndSettlementItem.setAmount(new BigDecimal(as.getAmount()));
		clearingAndSettlementItem.setChargeDialNumber(as.getDebitAuthorizationNumber());
		clearingAndSettlementItem.setChargeModel(as.getModel());
		clearingAndSettlementItem.setClearanceDialNumber(as.getApprovalAuthorizationNumber());
		clearingAndSettlementItem.setClearanceModel(as.getApprovalModel());
		clearingAndSettlementItem.setCurrency(as.getCurrency().getOfficialCode());
		
		clearingAndSettlementItem.setOriginator(as.getOriginator());
		clearingAndSettlementItem.setOriginatorAccountNumber(as.getOriginatorAccount());
		clearingAndSettlementItem.setPaymentPurpose(as.getPurpose());
		clearingAndSettlementItem.setReciever(as.getRecipient());
		clearingAndSettlementItem.setRecieverAccountNumber(as.getRecipientAccount());
		clearingAndSettlementItem.setStatementDate(as.getDateOfReceipt());
		
		System.out.println("dole " + clearingAndSettlementItem.getId() + clearingAndSettlementItem.getAmount());
		
		
		return clearingAndSettlementItem;
	}
	
	private void makeMt102Request(Bank paymentBank, Bank receiverBank, Currency currency, String currencyDate) throws DatatypeConfigurationException {
		
		Mt102Request mt102request = new Mt102Request();
		mt102request.setAmount(new BigDecimal(0));
		mt102request.setCurrency(currency.getOfficialCode());
		
		mt102request.setCurrencyDate(currencyDate);
		
		Date todayDate = new Date();
		mt102request.setDate(todayDate.toString());
		mt102request.setMessageId("mt102Request");
		mt102request.setOriginatorBankSwiftCode(paymentBank.getSwift());
		mt102request.setOriginatorBankTransactionAccount(paymentBank.getTransactionAccount());
		mt102request.setRecieverBankSwiftCode(receiverBank.getSwift());
		mt102request.setRecieverBankTransactionAccount(receiverBank.getTransactionAccount());
		
		clearingSettlementRequests.put(receiverBank.getSwift()+":"+currency.getOfficialCode(), mt102request);
		
		mt102Service.save(mt102request);
		
	}


}
