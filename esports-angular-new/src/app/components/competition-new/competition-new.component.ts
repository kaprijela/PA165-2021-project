import { Component, OnInit } from '@angular/core';
import {TeamService} from "../../service/team.service";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-competition-new',
  templateUrl: './competition-new.component.html',
  styleUrls: ['./competition-new.component.css']
})
export class CompetitionNewComponent implements OnInit {
  public createCompetition: FormGroup;

  constructor(private teamService: TeamService, private formBuilder: FormBuilder) {
    this.createCompetition = this.formBuilder.group({
      name: new FormControl(),
      abbreviation: new FormControl(),
      description: new FormControl(),
    });
  }

  ngOnInit(): void {
  }

  create() {

  }
}
