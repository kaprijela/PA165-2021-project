import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Team} from "../model/team";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  private readonly teamsUrl: string = "http://localhost:8080/pa165/api/v2/teams";
  private readonly byId: string = "http://localhost:8080/pa165/api/v2/teams/id";
  private readonly byName: string = "http://localhost:8080/pa165/api/v2/teams/id";
  private readonly create: string = "http://localhost:8080/pa165/api/v2/teams/create";
  private readonly byAbbreviation: string = "http://localhost:8080/pa165/api/v2/teams/abbreviation";
  constructor(private http: HttpClient) { }

  public findAll(): Observable<Team[]> {
    return this.http.get<Team[]>(this.teamsUrl);
  }

  public findById(id: number): Observable<Team> {
    return this.http.get<Team>(this.byId + "/" + id);
  }

  public findByName(name: string): Observable<Team> {
    return this.http.get<Team>(this.byName + "/" + name);
  }

  public createTeam(team: Team): Observable<Team> {
    return this.http.post<Team>(this.create, team)
  }

  /** POST: add a new hero to the database */
//   addHero(hero: Hero): Observable<Hero> {
//     return this.http.post<Hero>(this.heroesUrl, hero, httpOptions)
//       .pipe(
//         catchError(this.handleError('addHero', hero))
//       );
//   }

  public findByAbbreviation(abbreviation: string): Observable<Team> {
    return this.http.get<Team>(this.byAbbreviation + "/" + abbreviation);
  }

  public deleteTeam(id: number): void {
    this.http.delete(this.teamsUrl + "/" + id);
  }

  public addPlayer(team: number, player: number){
    this.http.get(this.teamsUrl + "/" + team + "/addPlayer/" + player );
  }

  public removePlayer(team: number, player: number){
    this.http.get(this.teamsUrl + "/" + team + "/removePlayer/" + player );
  }



}
