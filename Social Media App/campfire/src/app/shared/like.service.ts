import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { ILike } from '../like';
import { HttpClient } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LikeService {

  private GETurl = 'http://localhost:9001/CampFire_WAR/getAllLikes.app';
  private ADDurl = 'http://localhost:9001/CampFire_WAR/insertLike.app';
  // replace with post mocking json

  globalLikes: ILike[] = [];
  private postSource = new BehaviorSubject<ILike>(null);

  constructor(private httpCli: HttpClient) { }

  retrieveLikes(): Observable<ILike[]> {
    return this.httpCli.get<ILike[]>(this.GETurl);
  }

  getLikesArray(): ILike[] {
    return this.globalLikes;
  }

  addLike(like: ILike) {
    this.globalLikes.push(like);
    return this.globalLikes;
  }

  insertLikeinDB(like: ILike) {
    const params = new HttpParams().
      set('creationtime', like.likecreated.toString()).
      set('postid', like.postid.toString()).
      set('userid', like.userid.toString());
    return this.httpCli.post<number>(this.ADDurl, params);
    // this.httpCli.post(this.ADDurl, like);
  }
}
