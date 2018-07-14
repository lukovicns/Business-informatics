import { Injectable } from '@angular/core';
import { HttpClient } from '../../../node_modules/@angular/common/http';
import { url } from '../../url';

@Injectable({
  providedIn: 'root'
})
export class ClearingService {

  constructor(private http: HttpClient) { }

  getClearingSettlements() {
    return this.http.get(url + 'clearings');
  }
}
