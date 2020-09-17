import { Injectable } from '@angular/core';
import { HttpRequest, HttpClient, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  constructor( private http: HttpClient) { }

  uploadData(url: string, formData: FormData): Observable<HttpEvent<{}>> {
    const newRequest = new HttpRequest('POST', url, formData, {withCredentials: true});
    return this.http.request(newRequest);
  }
}
