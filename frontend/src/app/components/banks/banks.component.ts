import { Component, OnInit } from '@angular/core';
import { BankService } from '../../services/bank.service';
import { FormBuilder, Validators } from '../../../../node_modules/@angular/forms';
import { fadeIn } from '../../../animations';
import { CountryService } from '../../services/country.service';

@Component({
  selector: 'app-banks',
  templateUrl: './banks.component.html',
  styleUrls: ['./banks.component.css'],
  animations: [fadeIn()]
})
export class BanksComponent implements OnInit {

  banks: any = [];
  countries: any = [];
  country = {};

  constructor(
    private bankService: BankService,
    private countryService: CountryService,
    private formBuilder: FormBuilder
  ) { }

  bankForm = this.formBuilder.group({
    country: ['', Validators.required],
    pib: ['', Validators.required],
    name: ['', Validators.required],
    address: ['', Validators.required],
    email: ['', Validators.compose([
      Validators.required,
      Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')
    ])],
    web: ['', Validators.required],
    telephone: ['', Validators.required],
    fax: ['', Validators.required],
    swift: ['', Validators.required]
  });

  ngOnInit() {
    this.bankService.getBanks()
    .subscribe(res => this.banks = res);
    this.countryService.getCountries()
    .subscribe(res => this.countries = res);
  }

  addBank() {
    const data = {
      'pib': this.bankForm.value['pib'],
      'name': this.bankForm.value['name'],
      'address': this.bankForm.value['address'],
      'email': this.bankForm.value['email'],
      'web': this.bankForm.value['web'],
      'telephone': this.bankForm.value['telephone'],
      'fax': this.bankForm.value['fax'],
      'banka': document.querySelector('#isBank')['checked'],
      'swift': this.bankForm.value['swift']
    }
    this.bankService.addBank(this.bankForm.value['country'], data)
    .subscribe(res => {
      this.ngOnInit();
      console.log(res);
    }, err => {
      console.log(err);
    });
  }

  exportToPDF(bankId: number) {
    this.bankService.exportToPDF(bankId)
    .subscribe(res => {
      console.log(res);
    }, err => {
      console.log(err);
    });
  }
}
