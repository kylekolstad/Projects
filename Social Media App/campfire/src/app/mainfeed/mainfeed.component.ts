import { Component, OnInit } from '@angular/core';
import { IProfile } from '../profile';
import { UserService } from '../shared/user.service';

@Component({
  selector: 'app-mainfeed',
  templateUrl: './mainfeed.component.html',
  styleUrls: ['./mainfeed.component.css']
})

export class MainfeedComponent implements OnInit {

  currentProfile: IProfile = null;
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.curProfile$.subscribe(user => this.currentProfile = user);
  }

}
