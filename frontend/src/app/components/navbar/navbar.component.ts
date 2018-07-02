import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  userIsLoggedIn() {
    return this.userService.getCurrentUser() != null;
  }

  logout() {
    if (this.userService.getCurrentUser() != null) {
      localStorage.removeItem('token');
      this.router.navigate(['/login']);
    }
  }
}
