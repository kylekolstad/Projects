import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { IProfile } from '../profile';
import { Observable, BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private url = 'http://localhost:9001/CampFire_WAR/getUserByNamePassword.app';

  private loginOrReset = new BehaviorSubject<boolean>(false);
  loginOrReset$ = this.loginOrReset.asObservable();

  constructor(private http: HttpClient) { }

  getUserInfo(username: string, password: string): Observable<IProfile> {

    const params = new HttpParams()
    .set('username', username)
    .set('password', password);

    return this.http.post<IProfile>(this.url, params, {withCredentials: true});
  }

  logoutUser(url: string){
    return this.http.get(url, {withCredentials: true});
  }

  setSelectedLoginOrReset(value: boolean) {
    this.loginOrReset.next(value);
  }
}
