import { Injectable } from '@angular/core';
import { HttpClient } from '../../../node_modules/@angular/common/http';
import { url } from '../../url';

@Injectable({
  providedIn: 'root'
})
export class BankService {

  constructor(private http: HttpClient) { }

  getBanks() {
    return this.http.get(url + 'banks');
  }

  getBank(id: number) {
    return this.http.get(url + 'banks/' + id);
  }

  addBank(countryId: number, data) {
    return this.http.post(url + 'banks/' + countryId, data);
  }

  exportToPDF(bankId: number) {
    return this.http.get(url + 'banks/export/' + bankId);
  }
}
