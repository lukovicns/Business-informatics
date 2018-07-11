package com.project.Businessinformatics.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Businessinformatics.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	public ArrayList<Account> findAccountsByBankIdOrderByOpeningDateAsc(Long id);
	

	@Query("select aa from Account aa where aa.accountNumber like ?1 and aa.openingDate between ?2 and ?3 and aa.bank.name like ?4 and aa.client.name like ?5 and aa.client.surname like ?6 and aa.currency.officialCode like ?7") 
	public Set<Account> search(String accountNumber, java.util.Date openingMin, java.util.Date openingMax, String bankName, String name, String surname, String currency);
	
	@Query("select account from Account account where account.accountNumber = ?1")
	public Account findAccountByAccountNumber(String accountNumber);
	

	@Query("select aa from Account aa where aa.accountNumber like ?1 and aa.openingDate between ?2 and ?3 and aa.bank.name like ?4 and aa.client.name like ?5 and aa.client.surname like ?6 and aa.currency.officialCode like ?7 and aa.active = ?8") 
	public Set<Account> searchWithActive(String accountNumber, Date openingMin, Date openingMax, String bankName, String name, String surname, String currency, Boolean active);

	
}
