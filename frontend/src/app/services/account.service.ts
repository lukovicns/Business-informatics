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

  addAccount(data) {
    return this.http.post(url + 'accounts', data);
  }

  exportToXml() {
    
  }
}
