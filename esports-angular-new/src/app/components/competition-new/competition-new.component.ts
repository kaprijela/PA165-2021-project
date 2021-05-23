import { Component, OnInit } from '@angular/core';
import {TeamService} from "../../service/team.service";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {Competition} from "../../model/competition";
import {CompetitionService} from "../../service/competition.service";

@Component({
  selector: 'app-competition-new',
  templateUrl: './competition-new.component.html',
  styleUrls: ['./competition-new.component.css']
})
export class CompetitionNewComponent implements OnInit {
  createCompetition: FormGroup = this.formBuilder.group({name: "", prizepool: "", location: ""})
  competition = <Competition> {}

  constructor(private competitionService: CompetitionService, private formBuilder: FormBuilder) {
    this.createCompetition = this.formBuilder.group({
      name: new FormControl(),
      prizepool: new FormControl(),
      location: new FormControl(),
    });
  }

  ngOnInit(): void {
  }

  createComp() {
    const value = this.createCompetition.value;
    if (value.name && value.prizepool && value.location) {
      this.competition.name = value.name;
      this.competition.prizepool = value.prizepool;
      this.competition.location = value.description;
      this.competitionService.createCompetition(this.competition).subscribe();
    }
  }
}
