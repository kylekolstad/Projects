import { Component, OnInit } from '@angular/core';
import { UserService } from '../shared/user.service';
import { IProfile } from '../profile';

@Component({
  selector: 'app-friend',
  templateUrl: './friend.component.html',
  styleUrls: ['./friend.component.css']
})
export class FriendComponent implements OnInit {

  userToDisplay = 'friend';
  myUsers: IProfile[] = [];
  currentProfile: IProfile = null;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.curProfile$.subscribe(user => this.currentProfile = user);
  }

}

