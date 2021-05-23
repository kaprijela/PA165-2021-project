import {Component, OnInit} from '@angular/core';
import {TeamService} from "../../service/team.service";
import {Team} from "../../model/team";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-team-detail',
  templateUrl: './team-detail.component.html',
  styleUrls: ['./team-detail.component.css']
})
export class TeamDetailComponent implements OnInit {
  addPlayer: FormGroup = this.formBuilder.group({playerAdd: ""})
  removePlayer: FormGroup = this.formBuilder.group({playerRem: ""})
  team?: Team;

  constructor(private teamService: TeamService,
              private router: Router,
              private route: ActivatedRoute, private formBuilder: FormBuilder) {
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
    this.addPlayer = this.formBuilder.group({
      playerAdd: new FormControl(),
    });
    this.removePlayer = this.formBuilder.group({
      playerRem: new FormControl(),
    });
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

  add(){
    const value = this.addPlayer.value;
    // @ts-ignore
    this.teamService.addPlayer(this.team.id, value.playerAdd).subscribe()
    location.reload();
  }

  remove(){
    const value = this.removePlayer.value;
    // @ts-ignore
    this.teamService.removePlayer(this.team.id, value.playerRem).subscribe()
    location.reload();
  }
}
