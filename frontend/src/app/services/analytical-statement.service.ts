import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { url } from '../../url';

@Injectable({
  providedIn: 'root'
})
export class AnalyticalStatementService {

  constructor(private http: HttpClient) { }

  getAnalyticalStatements() {
    return this.http.get(url + 'analyticalStatements');
  }

  addAnalyticalStatements(createUrl: string) {
    return this.http.post(url + 'analyticalStatements' + createUrl, {});
  }

  exportToXML(accountId: number, startDate: string, endDate: string) {
    let exportUrl = url + 'analyticalStatements/exportxml/' + accountId + '/' + startDate + '/' + endDate;
    return this.http.get(exportUrl);
  }
}
