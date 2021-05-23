import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Competition} from "../model/competition";
import {MatchRecord} from "../model/match-record";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {
  private readonly apiEndpoint: string = "http://localhost:8080/pa165/api/v2/competitions";
  private readonly create = this.apiEndpoint + "/create";
  private readonly byId = this.apiEndpoint + "/id";
  private readonly byName = this.apiEndpoint + "/name";

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Competition[]> {
    return this.http.get<Competition[]>(this.apiEndpoint);
  }

  public createCompetition(competition: Competition): Observable<Competition> {
    return this.http.post<Competition>(this.create, competition)
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

  public addTeam(competition: number, team: number){
    this.http.get(this.apiEndpoint + "/" + competition + "/addTeam/" + team );
  }

  public removeTeam(competition: number, team: number){
    this.http.get(this.apiEndpoint + "/" + competition + "/removeTeam/" + team );
  }

  public addMatchRecord(competitionId: number, record: MatchRecord) {
    this.http.post<MatchRecord>(this.apiEndpoint + "/" + competitionId + "/records", record);
  }
}
