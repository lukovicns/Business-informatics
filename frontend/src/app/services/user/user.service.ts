import { Injectable } from '@angular/core';
import * as decode from 'jwt-decode';
import { HttpClient } from '@angular/common/http';
import { url } from '../../../url';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getCurrentUser() {
    let payload = null;
    if (localStorage.getItem('token') != null) {
      payload = decode(localStorage.getItem('token'));
    }
    return payload;
  }

  login(user) {
    return this.http.post(url + 'klijenti/login', user);
  }

  register() {

  }
}
