import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-team-new',
  templateUrl: './team-new.component.html',
  styleUrls: ['./team-new.component.css']
})
export class TeamNewComponent implements OnInit {

  constructor(private teamService: TeamService,
                            private router: Router,
                            private route: ActivatedRoute) {

   }

  ngOnInit(): void {
  }

  createTeam() {
  }
  getTeam(id: number) {
      console.log("getTeam")
      this.teamService.findById(id).subscribe(data => {
        this.team = data;
      })
    }
}
