import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Competition} from "../model/competition";
import {MatchRecord} from "../model/match-record";
import {Observable} from "rxjs";

import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {
  private readonly apiEndpoint: string = "http://localhost:8080/pa165/api/v2/competitions";
  private readonly create = this.apiEndpoint + "/create";
  private readonly byId = this.apiEndpoint + "/id";
  private readonly byName = this.apiEndpoint + "/name";

  constructor(private http: HttpClient, private router: Router) {
  }

  public findAll(): Observable<Competition[]> {
    return this.http.get<Competition[]>(this.apiEndpoint);
  }

  public createCompetition(competition: Competition): void {
    // var json = {"name": competition.name, "pricepool": competition.pricepool, "location": competition.location}
    this.http.post<Competition>(this.create, {
      "id": null,
      "name": competition.name,
      "game": null,
      "location": competition.location,
      "prizepool": competition.prizepool,
      "teams": [],
      "date": null
    })
    this.router.navigate(['/competitions/']);
  }

  public findById(id: number): Observable<Competition> {
    return this.http.get<Competition>(this.byId + "/" + id);
  }

  public findByName(name: string): Observable<Competition> {
    return this.http.get<Competition>(this.byName + "/" + name);
  }

  public deleteCompetition(id: number): void {
    this.http.delete(this.apiEndpoint + "/" + id);
  }

  public addTeam(competition: number, team: string): Observable<Competition> {
    this.router.navigate(['/competitions/id/' + competition]);
    return this.http.get<Competition>(this.apiEndpoint + "/add/" + competition + "/addTeam/" + team);
  }

  public removeTeam(competition: number, team: string): Observable<Competition> {
    this.router.navigate(['/competitions/id/' + competition]);
    return this.http.get<Competition>(this.apiEndpoint + "/remove/" + competition + "/removeTeam/" + team);
  }

  public addMatchRecord(competitionId: number, record: MatchRecord) {
    var x = this.http.post<MatchRecord>(this.apiEndpoint + "/" + competitionId + "/records", record);
    // @ts-ignore
    this.router.navigate(['/competitions/id/' + this.competition.id]);
    return x
  }
}
