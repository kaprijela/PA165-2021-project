import { Component, OnInit } from '@angular/core';
import {Team} from "../../model/team";
import {TeamService} from "../../service/team.service";

@Component({
  selector: 'app-team-list',
  templateUrl: './team-list.component.html',
  styleUrls: ['./team-list.component.css']
})
export class TeamListComponent implements OnInit {

  teams: Team[];

  constructor(private teamService: TeamService) {
    this.teams = [];
  }

  ngOnInit(): void {
    this.teamService.findAll().subscribe(data => {
      this.teams = data;
    })
  }
}
