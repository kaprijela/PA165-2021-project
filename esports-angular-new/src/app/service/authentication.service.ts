import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private loginUrl: string = "http://localhost:8080/pa165/api/v2/login";

  constructor(private http: HttpClient, private router: Router) {
  }

  authenticate(username: string, password: string) {
    this.http.post(this.loginUrl, {username: username, password: password}).subscribe(
      response => {
        console.log("Response: " + response);
        if (response !== null) {
          sessionStorage.setItem('user-role', response.toString());
        } else {
          return false;
        }

        sessionStorage.setItem('username', username);
        this.router.navigate(['/competitions']);
        return true;
      },
      error => {
        console.log("Error: " + error);
        return false;
      }
    )
  }

  logout() {
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('user-role');
  }

  public isAuthenticated(): boolean {
    let user = sessionStorage.getItem('username')
    console.log(!(user === null))
    return !(user === null)
  }

  public getRoles(): string[] {
    const role = sessionStorage.getItem("user-role");
    if (role) {
      return role.split(",")
    }
    return [];
  }

  public isRole(role: string): boolean {
    const roles = this.getRoles();
    for (let i = 0; i < roles.length; i++) {
      if (roles[i] === role) {
        return true;
      }
    }
    return false;
  }
}
