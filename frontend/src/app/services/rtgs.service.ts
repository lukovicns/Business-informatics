import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { url } from '../../url';

@Injectable({
  providedIn: 'root'
})
export class RtgsService {

  constructor(private http: HttpClient) { }

  getRtgsSettlements() {
    return this.http.get(url + 'rtgs');
  }
}
