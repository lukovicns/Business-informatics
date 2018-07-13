import { Component, OnInit } from '@angular/core';
import { fadeIn } from '../../../animations';
import { FormBuilder, Validators } from '@angular/forms';
import { AnalyticalStatementService } from '../../services/analytical-statement.service';
import { CurrencyService } from '../../services/currency.service';
import { CityService } from '../../services/city.service';
import { AccountService } from '../../services/account.service';

@Component({
  selector: 'app-analytical-statements',
  templateUrl: './analytical-statements.component.html',
  styleUrls: ['./analytical-statements.component.css'],
  animations: [fadeIn()]
})
export class AnalyticalStatementsComponent implements OnInit {

  analyticalStatements: any = [];
  currencies: any = [];
  cities: any = [];
  accounts: any = [];
  modes: any = [];
  isHidden: boolean = true;

  constructor(
    private formBuilder: FormBuilder,
    private analyticalStatementService: AnalyticalStatementService,
    private accountService: AccountService,
    private currencyService: CurrencyService,
    private cityService: CityService
  ) { }

  analyticalStatementForm = this.formBuilder.group({
    originator: ['', Validators.required],
    purpose: ['', Validators.required],
    recipient: ['', Validators.required],
    dateOfReceipt: ['', Validators.required],
    currencyDate: ['', Validators.required],
    originatorAccount: ['', Validators.required],
    model: ['', Validators.compose([
      Validators.required,
      Validators.min(0),
      Validators.max(99)
    ])],
    debitAuthorizationNumber: ['', Validators.required],
    recipientAccount: ['', Validators.required],
    approvalModel: ['', Validators.compose([
      Validators.required,
      Validators.min(0),
      Validators.max(99)
    ])],
    approvalAuthorizationNumber: ['', Validators.required],
    amount: ['', Validators.required],
    city: ['', Validators.required],
    currency: ['', Validators.required],
    analyticalStatementMode: ['', Validators.required]
  });

  ngOnInit() {
    this.analyticalStatementService.getAnalyticalStatements()
    .subscribe(res => {
      this.analyticalStatements = res;
    });
    this.currencyService.getCurrencies()
    .subscribe(res => this.currencies = res);
    this.cityService.getCities()
    .subscribe(res => this.cities = res);
    this.accountService.getAccounts()
    .subscribe(res => this.accounts = res);
    this.modes = ['PAYMENT', 'PAYOUT', 'TRANSFER'];
  }

  addAnalyticalStatement() {
    let data = {
      'originator': this.analyticalStatementForm.value['originator'],
      'purpose': this.analyticalStatementForm.value['purpose'],
      'recipient': this.analyticalStatementForm.value['recipient'],
      'dateOfReceipt': this.analyticalStatementForm.value['dateOfReceipt'],
      'currencyDate': this.analyticalStatementForm.value['currencyDate'],
      'originatorAccount': this.analyticalStatementForm.value['originatorAccount'],
      'model': this.analyticalStatementForm.value['model'],
      'debitAuthorizationNumber': this.analyticalStatementForm.value['debitAuthorizationNumber'],
      'recipientAccount': this.analyticalStatementForm.value['recipientAccount'],
      'approvalModel': this.analyticalStatementForm.value['approvalModel'],
      'approvalAuthorizationNumber': this.analyticalStatementForm.value['approvalAuthorizationNumber'],
      'amount': this.analyticalStatementForm.value['amount'],
      'urgently': document.querySelector('#urgently')['checked'],
      'placeOfAcceptance': this.findCity(this.analyticalStatementForm.value['city']),
      'currency': this.findCurrency(this.analyticalStatementForm.value['currency']),
      'analyticalStatementMode': this.analyticalStatementForm.value['analyticalStatementMode']
    }
    let createUrl = '/create/' + this.analyticalStatementForm.value['currency'] +
                    '/' + this.analyticalStatementForm.value['city'] +
                    '/' + this.analyticalStatementForm.value['dateOfReceipt'] +
                    '/' + this.analyticalStatementForm.value['currencyDate'];
    this.analyticalStatementService.addAnalyticalStatements(createUrl, data)
    .subscribe(res => {
      this.ngOnInit();
      this.analyticalStatementForm.reset();
    }, err => {
      console.log(err);
      this.analyticalStatementForm.reset();
    });
  }

  findCity(id: number) {
    for (let i = 0; i < this.cities.length; i++) {
      if (this.cities[i].id == id) {
        return this.cities[i];
      }
    }
    return null;
  }
  
  findCurrency(id: number) {
    for (let i = 0; i < this.currencies.length; i++) {
      if (this.currencies[i].id == id) {
        return this.currencies[i];
      }
    }
    return null;
  }
}
