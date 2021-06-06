import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Player} from "../model/player";
import {Observable} from "rxjs";
import {Router} from "@angular/router";
import {Statistics} from "../model/statistics";

@Injectable({
  providedIn: 'root'
})
export class PlayerService {
  private readonly url: string = "http://localhost:8080/pa165/api/v2/players";

  constructor(private http: HttpClient, private router: Router) { }

  public findAll(): Observable<Player[]> {
    return this.http.get<Player[]>(this.url);
  }

  public createPlayer(player: Player): Observable<Player> {
    this.router.navigateByUrl("/players").then();
    return this.http.post<Player>(this.url, player)
  }

  public findById(id: number): Observable<Player> {
    return this.http.get<Player>(this.url + "/" + id);
  }

  public getPlayerAverageScore(id: number): Observable<Statistics> {
    return this.http.get<Statistics>(this.url + "/" + id + "/statistics/average");
  }
}
