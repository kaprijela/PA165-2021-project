import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {User} from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private loginUrl: string = "http://localhost:8080/esports/api/v2/login";

  constructor(private http: HttpClient, private router: Router) { }

  authenticate(username: string, password: string) {
    this.http.post<User>(this.loginUrl, {username: username, password: password}).subscribe(
      response => {
        console.log("Response: " + response);
        sessionStorage.setItem('username', username);
        this.router.navigate(['/competitions']);
        return true;
      },
      error => {
        console.log("Error: "+ error);
        return false;
      }
    )
  }

  logout() {
    sessionStorage.removeItem('username');
  }

  public isAuthenticated(): boolean {
    let user = sessionStorage.getItem('username')
    console.log(!(user === null))
    return !(user === null)
  }

}
