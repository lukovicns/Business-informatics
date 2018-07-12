import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { url } from '../../url';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {

  constructor(private http: HttpClient) { }

  getCurrencies() {
    return this.http.get(url + 'currencies');
  }
}
