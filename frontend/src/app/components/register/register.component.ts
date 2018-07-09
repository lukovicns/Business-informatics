import { Component, OnInit } from '@angular/core';
import { fadeIn } from '../../../animations';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CityService } from '../../services/city.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  animations: [fadeIn()]
})
export class RegisterComponent implements OnInit {

  errorMessage: string;
  cities: any = [];

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private cityService: CityService,
    private router: Router
  ) { }

  registerForm = this.formBuilder.group({
    email: ['', Validators.compose([
      Validators.required,
      Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')
    ])],
    name: ['', Validators.required],
    surname: ['', Validators.required],
    password1: ['', Validators.compose([
      Validators.required,
      Validators.minLength(8)
    ])],
    password2: ['', Validators.compose([
      Validators.required,
      Validators.minLength(8)
    ])]
  });

  ngOnInit() {
    if (this.userService.getCurrentUser() != null) {
      this.router.navigate(['/']);
    }
    this.cityService.getCities()
    .subscribe(res => {
      this.cities = res;
    }, err => {
      console.log(err);
    })
  }

  register() {
    if (this.registerForm.value['password1'] != this.registerForm.value['password2']) {
      this.errorMessage = 'Passwords don\'t match!';
    } else {
      this.userService.register(this.registerForm.value)
      .subscribe(res => {
        this.router.navigate(['/login']);
      }, err => {
        this.errorMessage = err['error'];
      })
    }
  }
}
