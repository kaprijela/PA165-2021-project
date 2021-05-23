import { Component, OnInit } from '@angular/core';
import {CompetitionService} from "../../service/competition.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Competition} from "../../model/competition";

@Component({
  selector: 'app-competition-detail',
  templateUrl: './competition-detail.component.html',
  styleUrls: ['./competition-detail.component.css']
})
export class CompetitionDetailComponent implements OnInit {

  competition?: Competition;

  constructor(private competitionService: CompetitionService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    const param = this.route.snapshot.paramMap.get("id");
    if (param) {
      const id = +param;
      this.getCompetition(id);
    }
  }

  getCompetition(id: number){
    console.log("getCompetition")
    this.competitionService.findById(id).subscribe(data => {
      this.competition = data;
    })
  }

}
