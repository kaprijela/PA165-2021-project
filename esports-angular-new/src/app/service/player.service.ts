import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Player} from "../model/player";
import {Observable} from "rxjs";
import {Team} from "../model/team";

@Injectable({
  providedIn: 'root'
})
export class PlayerService {
  private readonly playersUrl: string;
  private readonly create = 'http://localhost:8080/pa165/api/v2/players/create';
  private readonly byId = 'http://localhost:8080/pa165/api/v2/players/id';
  private readonly byName = 'http://localhost:8080/pa165/api/v2/players/name';
  private readonly statistics= 'http://localhost:8080/pa165/api/v2/players/getPlayerStatistics';

  constructor(private http: HttpClient) {
    this.playersUrl = 'http://localhost:8080/pa165/api/v2/players';

  }

  public findAll(): Observable<Player[]> {
    return this.http.get<Player[]>(this.playersUrl);
  }

  public createPlayer(player: Player): Observable<Team> {
    return this.http.post<Team>(this.create, player)
  }

  public findById(id: number): Observable<Team> {
    return this.http.get<Team>(this.byId + "/" + id);
  }

  public findByName(name: string): Observable<Team> {
    return this.http.get<Team>(this.byName + "/" + name);
  }

  // public getPlayerScore(id: number): number {
  //   return this.http.get<Team>(this.statistics + "/" + id);
  // }

}
