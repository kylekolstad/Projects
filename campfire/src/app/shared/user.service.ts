import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { IProfile } from '../profile';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = 'http://localhost:9001/CampFire_WAR/getAllUsers.app';

  myUsers: IProfile[] = [];
  private profileSource = new BehaviorSubject<IProfile>(null);
  private curUser = new BehaviorSubject<IProfile>(null);
  curProfile$ = this.profileSource.asObservable();
  curUser$ = this.curUser.asObservable();


  constructor(private httpCli: HttpClient) { }

  retrieveUsers(): Observable<IProfile[]> {
    return this.httpCli.post<IProfile[]>(this.url, '', {withCredentials: true});
  }

  addUser(user: IProfile) {
    this.myUsers.push(user);
  }

  clearUserData() {
    this.myUsers = [];
    this.profileSource.next(null);
    this.curUser.next(null);
  }

  getUsers(): IProfile[] {
    return this.myUsers;
  }

  updateCurrentProfile(user: IProfile) {
    this.profileSource.next(user);
  }

  updateCurrentUser(user: IProfile) {
    this.curUser.next(user);
  }
}
