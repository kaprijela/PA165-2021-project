import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Competition} from "../model/competition";
import {Observable} from "rxjs";
import {Player} from "../model/player";
import {Team} from "../model/team";

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {
  private readonly competitionUrl: string;
  private readonly create = 'http://localhost:8080/pa165/api/v2/competitions/create';
  private readonly byId = 'http://localhost:8080/pa165/api/v2/competitions/id';
  private readonly byName = 'http://localhost:8080/pa165/api/v2/competitions/name';

  constructor(private http: HttpClient) {
    this.competitionUrl = 'http://localhost:8080//api/v2/competitions';
  }

  public findAll(): Observable<Competition[]> {
    return this.http.get<Competition[]>(this.competitionUrl);
  }

  public createCompetition(competition: Competition): Observable<Competition> {
    return this.http.post<Team>(this.create, competition)
  }

  public findById(id: number): Observable<Competition> {
    return this.http.get<Team>(this.byId + "/" + id);
  }

  public findByName(name: string): Observable<Competition> {
    return this.http.get<Team>(this.byName + "/" + name);
  }

  public deleteCompetition(id: number): void {
    this.http.delete(this.competitionUrl + "/" + id);
  }

  public addTeam(competition: number, team: number){
    this.http.get(this.competitionUrl + "/" + competition + "/addTeam/" + team );
  }

  public removeTeam(competition: number, team: number){
    this.http.get(this.competitionUrl + "/" + competition + "/removeTeam/" + team );
  }
}
