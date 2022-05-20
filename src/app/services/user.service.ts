import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Users} from "../model/users";

const baseUrl = 'http://userservice-env-1.eba-nf42cbg3.us-east-1.elasticbeanstalk.com/api/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUsers(): Observable<Users[]> {
    return this.http.get<Users[]>(baseUrl);
  }

  getUser(id: any): Observable<Users> {
    // @ts-ignore
    return this.http.get(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

  findByFirstName(firstName: any): Observable<Users[]> {
    return this.http.get<Users[]>(`${baseUrl}?firstName=${firstName}`);
  }
}
