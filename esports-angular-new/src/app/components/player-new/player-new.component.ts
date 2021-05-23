import { Component, OnInit } from '@angular/core';
import {Player} from "../../model/player";
import {PlayerService} from "../../service/player.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-player-new',
  templateUrl: './player-new.component.html',
  styleUrls: ['./player-new.component.css']
})
export class PlayerNewComponent implements OnInit {
  loginForm: FormGroup = this.formBuilder.group({name: ""})
    player = <Player>{}

  constructor(private playerService: PlayerService, private formBuilder: FormBuilder) {
  this.loginForm = this.formBuilder.group({
          name: new FormControl(),

        });
  }

  ngOnInit(): void {
  }
  create() {
          const value = this.loginForm.value;
          if (value.name ) {
            this.player.name = value.name;
            this.playerService.createPlayer(this.player).subscribe();
          }
        }
}
