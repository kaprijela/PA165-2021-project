import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Player} from "../model/player";
import {Observable} from "rxjs";
import {Team} from "../model/team";
import {Statistics} from "../model/statistics";

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

  public createPlayer(player: Player): Observable<Player> {
    return this.http.post<Player>(this.create, player)
  }

  public findById(id: number): Observable<Player> {
    return this.http.get<Player>(this.byId + "/" + id);
  }

  public findByName(name: string): Observable<Player> {
    return this.http.get<Player>(this.byName + "/" + name);
  }

  // public getPlayerScore(id: number): number {
  //   return this.http.get<Statistics>(this.statistics + "/" + id);
  // }

}
