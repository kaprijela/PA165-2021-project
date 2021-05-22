import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Team} from "../model/team";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  private readonly teamsUrl: string;

  constructor(private http: HttpClient) {
    this.teamsUrl = 'http://localhost:8080/esports/api/v2/teams';
  }

  public findAll(): Observable<Team[]> {
    return this.http.get<Team[]>(this.teamsUrl);
  }
}
