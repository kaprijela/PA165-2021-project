import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PlayerListComponent} from "./components/player-list/player-list.component";
import {CompetitionListComponent} from "./components/competition-list/competition-list.component";
import {TeamListComponent} from "./components/team-list/team-list.component";
import {LoginComponent} from "./components/login/login.component";
import {TeamDetailComponent} from "./components/team-detail/team-detail.component";

const routes: Routes = [
  { path: 'players', component: PlayerListComponent },
  { path: 'competitions', component: CompetitionListComponent },
  { path: 'teams', component: TeamListComponent },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'team/:id', component: TeamDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
