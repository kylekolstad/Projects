import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login/login.service';
import { NgForm } from '@angular/forms';
import { ResetService } from './reset.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-reset',
  templateUrl: './reset.component.html',
  styleUrls: ['./reset.component.css']
})
export class ResetComponent implements OnInit {

  constructor(private loginService: LoginService, private resetService: ResetService, private snackBar: MatSnackBar) { }

  ngOnInit() {
  }

  backToLogin() {
    this.loginService.setSelectedLoginOrReset(false);
  }

  restPassword(form: NgForm) {

    this.resetService.restPassword(form).subscribe(data => {
      console.log(data);
      switch (data) {
        case -2: {
          this.snackBar.open('There is no account linked to provided information!',
          'Dismiss', {duration: 5000, verticalPosition: 'top'});
          break;
        }
        case 1: {
          this.snackBar.open('A reset password instruction message has been sent to your email!',
          'Dismiss', {duration: 5000, verticalPosition: 'top'});
          break;
        }
      }
    });
  }

}