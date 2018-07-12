import { Component, OnInit } from '@angular/core';
import { fadeIn } from '../../../animations';
import { FormBuilder, Validators } from '@angular/forms';
import { AnalyticalStatementService } from '../../services/analytical-statement.service';
import { CurrencyService } from '../../services/currency.service';
import { CityService } from '../../services/city.service';

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

  constructor(
    private formBuilder: FormBuilder,
    private analyticalStatementService: AnalyticalStatementService,
    private currencyService: CurrencyService,
    private cityService: CityService
  ) { }

  analyticalStatementForm = this.formBuilder.group({
    currency: ['', Validators.required],
    city: ['', Validators.required],
    dateOfReceipt: ['', Validators.required],
    currencyDate: ['', Validators.required]
  });

  ngOnInit() {
    this.analyticalStatementService.getAnalyticalStatements()
    .subscribe(res => {
      this.analyticalStatements = res;
      console.log(res);
    });
    this.currencyService.getCurrencies()
    .subscribe(res => this.currencies = res);
    this.cityService.getCities()
    .subscribe(res => this.cities = res);
  }

  addAnalyticalStatement() {
    let createUrl = '/create/' + this.analyticalStatementForm.value['currency'] +
                    '/' + this.analyticalStatementForm.value['city'] +
                    '/' + this.analyticalStatementForm.value['dateOfReceipt'] +
                    '/' + this.analyticalStatementForm.value['currencyDate'];
    this.analyticalStatementService.addAnalyticalStatements(createUrl)
    .subscribe(res => {
      this.ngOnInit();
    }, err => {
      console.log(err);
      this.ngOnInit();
    });
  }
}
