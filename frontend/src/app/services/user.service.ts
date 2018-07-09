import { Injectable } from '@angular/core';
import * as decode from 'jwt-decode';
import { HttpClient } from '@angular/common/http';
import { url } from '../../url';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  userUrl: string = url + 'users/';
  clientUrl: string = url + 'clients/';

  constructor(private http: HttpClient) { }

  getToken() {
    return localStorage.getItem('token');
  }

  getCurrentUser() {
    let payload = null;
    if (this.getToken() != null) {
      payload = decode(this.getToken());
    }
    return payload;
  }

  login(user) {
    return this.http.post(this.userUrl + 'login', user);
  }

  register(data) {
    const user = {
      'name': data.name,
      'surname': data.surname,
      'email': data.email,
      'password': data.password1
    }
    return this.http.post(this.userUrl + 'register', user);
  }
}
