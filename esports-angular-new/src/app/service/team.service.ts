import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpErrorResponse} from "@angular/common/http";
import { Observable, Subject, throwError} from 'rxjs';
import { catchError } from 'rxjs/operators';
import {Router} from "@angular/router";
import {Team} from "../model/team";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    Authorization: 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  private readonly teamsUrl: string = "http://localhost:8080/pa165/api/v2/teams";
  private readonly byId: string = "http://localhost:8080/pa165/api/v2/teams/id";
  private readonly byName: string = "http://localhost:8080/pa165/api/v2/teams/name";
  private readonly create: string = "http://localhost:8080/pa165/api/v2/teams/create";
  private readonly byAbbreviation: string = "http://localhost:8080/pa165/api/v2/teams/abbreviation";
  private handleError(error: HttpErrorResponse, team: Team) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // Return an observable with a user-facing error message.
    return throwError(
      'Something bad happened; please try again later.');
  }
  constructor(private http: HttpClient, private router: Router) { }

  public findAll(): Observable<Team[]> {
    return this.http.get<Team[]>(this.teamsUrl);
  }

  public findById(id: number): Observable<Team> {
    return this.http.get<Team>(this.byId + "/" + id);
  }

  public findByName(name: string): Observable<Team> {
    return this.http.get<Team>(this.byName + "/" + name);
  }

  public createTeam(team: Team): Observable<any> {
  const body=JSON.stringify(team);
  console.log(team);
  this.router.navigate(['/teams']);
    return this.http.post(this.create, team)
  }

  public findByAbbreviation(abbreviation: string): Observable<Team> {
    return this.http.get<Team>(this.byAbbreviation + "/" + abbreviation);
  }

  public deleteTeam(id: number): void {
    this.http.delete(this.teamsUrl + "/" + id);
  }

  public addPlayer(team: number, player: number): Observable<any>{
   return this.http.get<any>(this.teamsUrl + "/add/" + team + "/addPlayer/" + player );
  }

  public removePlayer(team: number, player: number): Observable<any>{
    return this.http.get<Team>(this.teamsUrl + "/remove/" + team + "/removePlayer/" + player );
  }



}
