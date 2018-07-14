import { Component, OnInit } from '@angular/core';
import { fadeIn } from '../../../animations';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  animations: [fadeIn()]
})
export class HomeComponent implements OnInit {

  currentUser: string;
  constructor(private userService: UserService) { }

  ngOnInit() {

  }
  
  userIsLoggedIn() {
    if (this.userService.getCurrentUser() != null) {
      this.currentUser = this.userService.getCurrentUser()['email'];
      return true;
    } else {
      return false;
    }
  }
}
