import {Component, OnInit} from '@angular/core';
import {Team} from "../../model/team";
import {TeamService} from "../../service/team.service";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-team-new',
  templateUrl: './team-new.component.html',
  styleUrls: ['./team-new.component.css']
})
export class TeamNewComponent implements OnInit {
  loginForm: FormGroup = this.formBuilder.group({name: "", abbreviation: "", description: ""})
  team = <Team>{}

  constructor(private teamService: TeamService, private formBuilder: FormBuilder) {
    this.loginForm = this.formBuilder.group({
      name: new FormControl(),
      abbreviation: new FormControl(),
      description: new FormControl(),
    });
  }

  ngOnInit(): void {
  }

  create() {
    const value = this.loginForm.value;
    if (value.name && value.abbreviation && value.description) {
      this.team.name = value.name;
      this.team.abbreviation = value.abbreviation;
      this.team.description = value.description;
      this.teamService.create(this.team).subscribe();
    }
  }
}
