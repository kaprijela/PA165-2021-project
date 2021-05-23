import { Component, OnInit } from '@angular/core';
import {Player} from "../../model/player";
import {PlayerService} from "../../service/player.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Statistics} from "../../model/statistics";

@Component({
  selector: 'app-player-detail',
  templateUrl: './player-detail.component.html',
  styleUrls: ['./player-detail.component.css']
})
export class PlayerDetailComponent implements OnInit {
  player?: Player
  statistics?: Statistics

  constructor(private playerService: PlayerService,
              private router: Router,
              private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    const param = this.route.snapshot.paramMap.get("id");
    if (param) {
      const id = +param;
      this.getPlayer(id);
    }
  }

  private getPlayer(id: number) {
    console.log("getPlayer")
    this.playerService.findById(id).subscribe(data => {
      this.player = data;
    })
  }

  // private getStatsPlayer(id: number) {
  //   console.log("getPlayer")
  //   this.playerService.(id).subscribe(data => {
  //     this.player = data;
  //   })
  // }

}
