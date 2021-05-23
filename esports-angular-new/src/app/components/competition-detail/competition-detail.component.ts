import { Component, OnInit } from '@angular/core';
import {CompetitionService} from "../../service/competition.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Competition} from "../../model/competition";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-competition-detail',
  templateUrl: './competition-detail.component.html',
  styleUrls: ['./competition-detail.component.css']
})
export class CompetitionDetailComponent implements OnInit {
  addTeam: FormGroup = this.formBuilder.group({idTeamA: ""})
  removeTeam: FormGroup = this.formBuilder.group({idTeamR: ""})
  competition?: Competition;

  constructor(private competitionService: CompetitionService,
              private router: Router,
              private route: ActivatedRoute, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    const param = this.route.snapshot.paramMap.get("id");
    if (param) {
      const id = + param;
      this.getCompetition(id);
    }
    this.addTeam = this.formBuilder.group({
      idTeamA: new FormControl(),
    });
    this.removeTeam = this.formBuilder.group({
      idTeamR: new FormControl(),
    });
  }

  getCompetition(id: number){
    console.log("getCompetition")
    this.competitionService.findById(id).subscribe(data => {
      this.competition = data;
    })
  }

  add(){
    const value = this.addTeam.value;
    // @ts-ignore
    this.competitionService.addTeam(this.competition.id,value.idTeamA).subscribe()
    location.reload();
  }

  remove(){
    const value = this.removeTeam.value;
    // @ts-ignore
    this.competitionService.removeTeam(this.competition.id, value.idTeamR).subscribe()
    location.reload();
  }

}
