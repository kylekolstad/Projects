import { Component, Input, OnInit } from '@angular/core';
import { UserService } from './shared/user.service';
import { IProfile } from './profile';
import { LoginService } from './login/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'campfire';

  loggedin = false;
  resetSelected: boolean;

  currentUser: IProfile;

  constructor(private userService: UserService, private loginService: LoginService) {
  }

  ngOnInit() {
    this.userService.curUser$.subscribe( user => this.currentUser = user);
    this.loginService.loginOrReset$.subscribe(reset => this.resetSelected = reset);
  }

  login() {
    this.loggedin = true;

  }

  logout() {
    this.loggedin = false;
  }
}
