import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Media } from '../models/media.model';

@Injectable({
  providedIn: 'root'
})
export class MediaService {
  apiUrl: string;

  constructor(private _http: HttpClient) { }

  getMedia() {
    this.apiUrl = 'https://www.envano.com/wp-json/wp/v2/media?page=1&per_page=100';
    return this._http.get<Media []>(this.apiUrl);
  }

  getSpecificMedia(_featured_media) {
    this.apiUrl = 'https://www.envano.com/wp-json/wp/v2/media/' + _featured_media;
    return this._http.get<Media>(this.apiUrl);
  }
}
