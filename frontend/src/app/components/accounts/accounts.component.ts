import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { fadeIn } from '../../../animations';
import { AccountService } from '../../services/account.service';
import { BankService } from '../../services/bank.service';
import { ClientService } from '../../services/client.service';
import { CurrencyService } from '../../services/currency.service';
import { AnalyticalStatementService } from '../../services/analytical-statement.service';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css'],
  animations: [fadeIn()]
})
export class AccountsComponent implements OnInit {

  accounts: any = [];
  banks: any = [];
  clients: any = [];
  currencies: any = [];
  bank = {};
  client = {};
  currency = {};

  constructor(
    private formBuilder: FormBuilder,
    private accountService: AccountService,
    private bankService: BankService,
    private clientService: ClientService,
    private currencyService: CurrencyService,
    private analyticalStatementService: AnalyticalStatementService
  ) { }

  accountForm = this.formBuilder.group({
    accountNumber: ['', Validators.required],
    openingDate: ['', Validators.required],
    bank: ['', Validators.required],
    client: ['', Validators.required],
    currency: ['', Validators.required]
  });

  exportForm = this.formBuilder.group({
    startDate: ['', Validators.required], 
    endDate: ['', Validators.required]
  });

  ngOnInit() {
    this.accountService.getAccounts()
    .subscribe(res => this.accounts = res);
    this.bankService.getBanks()
    .subscribe(res => this.banks = res);
    this.clientService.getClients()
    .subscribe(res => this.clients = res);
    this.currencyService.getCurrencies()
    .subscribe(res => this.currencies = res);
  }

  addAccount() {
    for (let i = 0; i < this.banks.length; i++) {
      if (this.banks[i].id == this.accountForm.value['bank']) {
        this.bank = this.banks[i];
      }
    }
    for (let i = 0; i < this.clients.length; i++) {
      if (this.clients[i].id == this.accountForm.value['client']) {
        this.client = this.clients[i];
      }
    }
    for (let i = 0; i < this.currencies.length; i++) {
      if (this.currencies[i].id == this.accountForm.value['currency']) {
        this.currency = this.currencies[i];
      }
    }
    const data = {
      'accountNumber': this.accountForm.value['accountNumber'],
      'openingDate': this.accountForm.value['openingDate'],
      'active': document.querySelector('#active')['checked'],
      'bank': this.bank,
      'client': this.client,
      'currency': this.currency
    }
    this.accountService.addAccount(data)
    .subscribe(res => {
      console.log(res);
      this.ngOnInit();
    }, err => {
      console.log(err);
    });
  }
  exportToXML(accountId: number) {
    this.analyticalStatementService.exportToXML(accountId, this.exportForm.value['startDate'], this.exportForm.value['endDate'])
    .subscribe(res => {
      console.log(res);
    }, err => {
      console.log(err);
    });
  }
}
