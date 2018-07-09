import { Component, OnInit } from '@angular/core';
import { fadeIn } from '../../../animations';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  animations: [fadeIn()]
})
export class LoginComponent implements OnInit {

  private errorMessage: string;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router
  ) { }

  loginForm = this.formBuilder.group({
    email: ['', Validators.compose([
      Validators.required,
      Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')
    ])],
    password: ['', Validators.compose([
      Validators.required,
      Validators.minLength(8)
    ])]
  });

  ngOnInit() {
    if (this.userService.getCurrentUser() != null) {
      this.router.navigate(['/']);
    }
  }

  login() {
    this.userService.login(this.loginForm.value)
    .subscribe(res => {
      localStorage.setItem('token', res['token']);
      this.router.navigate(['/']);
    }, err => {
      this.errorMessage = err['error'];
      document.querySelector('#password')['value'] = '';
    });
  }
}
