import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../service/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  loggedIn: boolean = false;

  constructor(private authService: AuthenticationService, private router: Router) {
    this.loggedIn = authService.isAuthenticated();
    console.log(this.loggedIn);
  }

  ngOnInit(): void {
    // automatically refresh logged-in status after navigating
    this.router.events.subscribe(event => {
      if (event.constructor.name === "NavigationEnd") {
        this.loggedIn = this.authService.isAuthenticated();
      }
    })
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login'])
  }
}
