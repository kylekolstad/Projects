import { Component, OnInit } from '@angular/core';
import { IProfile } from '../profile';
import { UserService } from '../shared/user.service';
import { RegisterService } from './register.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: IProfile;

  constructor(private userService: UserService, private registerService: RegisterService, private snackBar: MatSnackBar) { }

  ngOnInit() {
  }

  openSnack() {
    this.snackBar.open('I work', 'Dismiss', {duration: 2000});
  }

  register(form: any) {
    this.registerService.createNewUser(form).subscribe(data => {
      console.log(data);
      switch(data){
        case -1:
          this.snackBar.open('Email Already Exists With Different User!', 'Dismiss', {duration: 5000, verticalPosition: 'top'});
          break;
        case -2:
          this.snackBar.open('Username Already Exists with Different Email!', 'Dismiss', {duration: 5000, verticalPosition: 'top'});
          break;
        case -3:
          this.snackBar.open('An account with the credentials you used already exists!',
          'Dismiss', {duration: 5000, verticalPosition: 'top'});
          break;
        default:
          this.snackBar.open('Your account has been successfully created',
          'Proceed to login', {duration: 5000, verticalPosition: 'top'});
          form.reset();
      }
    });
  }
}
