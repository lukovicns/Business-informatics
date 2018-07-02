import { Injectable } from '@angular/core';
import * as decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  getCurrentUser() {
    let payload = null;
    if (localStorage.getItem('token') != null) {
      payload = decode(localStorage.getItem('token'));
    }
    return payload;
  }

  login() {
    
  }

  register() {

  }
}
