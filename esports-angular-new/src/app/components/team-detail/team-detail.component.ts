import {Component, OnInit} from '@angular/core';
import {TeamService} from "../../service/team.service";
import {Team} from "../../model/team";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-team-detail',
  templateUrl: './team-detail.component.html',
  styleUrls: ['./team-detail.component.css']
})
export class TeamDetailComponent implements OnInit {

  team?: Team;

  constructor(private teamService: TeamService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    const param = this.route.snapshot.paramMap.get("id");
    const arg = this.route.snapshot.paramMap.get("name");
    if (param) {
      const id = +param;
      this.getTeam(id);
    }
    if (arg) {
          const name = arg;
          console.log(arg)
          this.getTeamName(name);
        }
  }

  getTeam(id: number) {
    console.log("getTeam")
    this.teamService.findById(id).subscribe(data => {
      this.team = data;
    })
  }
  getTeamName(name: string) {
      console.log("getTeam")
      this.teamService.findByName(name).subscribe(data => {
        this.team = data;
      })
    }
}
