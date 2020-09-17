import { Component, OnInit } from '@angular/core';
import { UserService } from '../shared/user.service';
import { IProfile } from '../profile';
import { LoginService } from './login.service';
import { MatSnackBar } from '@angular/material';
import { PostService } from '../shared/post.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  currentUser: IProfile;
  validatedUser: IProfile;

  username: string;
  password: string;

  constructor(private userService: UserService, private loginService: LoginService,
              private snackBar: MatSnackBar, private postService: PostService) { }

  ngOnInit() {
    this.userService.curUser$.subscribe(user => this.currentUser = user);
  }

  login(form: any) {
    console.log(form.value);
    this.loginService.getUserInfo(this.username, this.password).subscribe(data => {
    this.validatedUser = data;
    if (this.validatedUser !== null) {
        console.log(this.validatedUser);
        this.userService.updateCurrentUser({
          cf_User_UserId: this.validatedUser.cf_User_UserId,
          cf_User_Username: this.validatedUser.cf_User_Username,
          cf_User_Password: this.validatedUser.cf_User_Password,
          cf_User_Title: this.validatedUser.cf_User_Title,
          cf_User_Fname: this.validatedUser.cf_User_Fname,
          cf_User_Lname: this.validatedUser.cf_User_Lname,
          cf_User_Email: this.validatedUser.cf_User_Email,
          cf_User_Birthday: this.validatedUser.cf_User_Birthday,
          cf_User_ProfilePic: this.validatedUser.cf_User_ProfilePic,
          cf_User_Bio: this.validatedUser.cf_User_Bio
        });

        this.postService.retrievePosts().subscribe(data => {
          data.forEach(post => {
            this.postService.addPost({
              userid: post[0],
              userfname: post[1],
              userlname: post[2],
              userprofurl: post[3],
              usertitle: post[4],
              postid: post[5],
              postcontents: post[6],
              postcreated: post[7],
              postimgurl: post[8],
              likecount: post[9],
            });
          });
        });

        this.userService.retrieveUsers().subscribe( info => {
          info.forEach( user => {
            user.cf_User_Birthday = new Date(user.cf_User_Birthday);
            if (user.cf_User_UserId !== this.currentUser.cf_User_UserId) {
              this.userService.addUser(user);
            }
          });
        });
      } else {
        this.snackBar.open('Wrong username or password',
          'Dismiss', {duration: 5000, verticalPosition: 'top'});
      }
    });
  }

  goToReset() {
    this.loginService.setSelectedLoginOrReset(true);
  }
}
