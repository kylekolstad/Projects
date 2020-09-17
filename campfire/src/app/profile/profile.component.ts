import { Component, OnInit } from '@angular/core';
import { UserService } from '../shared/user.service';
import { IProfile } from '../profile';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  userToDisplay = 'profile';
  myUsers: IProfile[] = [];
  currentProfile: IProfile = null;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.curProfile$.subscribe(user => this.currentProfile = user);
    console.log("I'm in ONINIT");
  }

}
