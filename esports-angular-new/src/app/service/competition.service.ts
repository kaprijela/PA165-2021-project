import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Competition} from "../model/competition";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CompetitionService {
  private readonly competitionUrl: string;

  constructor(private http: HttpClient) {
    this.competitionUrl = 'http://localhost:8080/esports/api/v2/competitions';
  }

  public findAll(): Observable<Competition[]> {
    return this.http.get<Competition[]>(this.competitionUrl);
  }
}
