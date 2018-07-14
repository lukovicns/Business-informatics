import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { url } from '../../url';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http: HttpClient) { }

  getAccounts() {
    return this.http.get(url + 'accounts');
  }

  getActiveAccounts() {
    return this.http.get(url + 'accounts/active');
  }

  addAccount(data) {
    return this.http.post(url + 'accounts', data);
  }

  exportToXML(accountId: number, startDate: string, endDate: string) {
    let exportUrl = url + 'analyticalStatements/exportxml/' + accountId + '/' + startDate + '/' + endDate;
    return this.http.get(exportUrl);
  }

  exportToPDF(accountId: number, startDate: string, endDate: string) {
    let exportUrl = url + 'analyticalStatements/export/' + accountId + '/' + startDate + '/' + endDate;
    return this.http.get(exportUrl);
  }

  deleteAccount(accountId: number, transferAccountNumber: string) {
    return this.http.post(url + 'accounts/delete/' + accountId + '/' + transferAccountNumber, null);
  }
}
