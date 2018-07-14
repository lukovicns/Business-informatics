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

  addAnalyticalStatements(createUrl: string, data) {
    return this.http.post(url + 'analyticalStatements' + createUrl, data);
  }
  
  import(id) {
      return this.http.get(url + 'analyticalStatements/' + id)
  }
}
