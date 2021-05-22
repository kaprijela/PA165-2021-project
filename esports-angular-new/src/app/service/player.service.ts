import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Player} from "../model/player";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PlayerService {
  private readonly playersUrl: string;

  constructor(private http: HttpClient) {
    this.playersUrl = 'http://localhost:8080/esports/api/v2/players';
  }

  public findAll(): Observable<Player[]> {
    return this.http.get<Player[]>(this.playersUrl);
  }
}
