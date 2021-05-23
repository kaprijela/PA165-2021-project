import {Component, OnInit} from '@angular/core';
import {Competition} from "../../model/competition";
import {CompetitionService} from "../../service/competition.service";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-competition-list',
  templateUrl: './competition-list.component.html',
  styleUrls: ['./competition-list.component.css']
})
export class CompetitionListComponent implements OnInit {

  competitions: Competition[];

  constructor(private competitionService: CompetitionService) {
    this.competitions = [];
  }

  ngOnInit(): void {
    this.competitionService.findAll().subscribe(data => {
      this.competitions = data;
    })
  }
}
