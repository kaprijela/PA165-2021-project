import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Competition} from "../model/competition";
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

  constructor(private http: HttpClient, private router: Router) { }

  public findAll(): Observable<Competition[]> {
    return this.http.get<Competition[]>(this.apiEndpoint);
  }

  public createCompetition(competition: Competition): Observable<Competition> {
    // var json = {"name": competition.name, "pricepool": competition.pricepool, "location": competition.location}
    return this.http.post<Competition>(this.create, {
      "id": null,
      "name": competition.name,
      "game": null,
      "location": competition.location,
      "prizepool":  competition.prizepool,
      "teams": [],
      "date": null
    })
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

  public addTeam(competition: number | undefined, team: string){
    this.router.navigate(['/teams']);
    this.http.get(this.apiEndpoint + "/" + competition + "/addTeam/" + team );
  }

  public removeTeam(competition: number | undefined, team: string){
    this.http.get(this.apiEndpoint + "/" + competition + "/removeTeam/" + team );
  }
}
