import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {PlayerListComponent} from "./components/player-list/player-list.component";
import {CompetitionListComponent} from "./components/competition-list/competition-list.component";
import {TeamListComponent} from "./components/team-list/team-list.component";
import {LoginComponent} from "./components/login/login.component";
import {TeamDetailComponent} from "./components/team-detail/team-detail.component";
import {AuthGuard} from "./auth.guard";
import {UnauthorizedComponent} from "./components/unauthorized/unauthorized.component";
import {NotFoundComponent} from "./components/not-found/not-found.component";
import {TeamAddComponent} from "./components/team-add/team-add.component";

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'players', component: PlayerListComponent },
  { path: 'competitions', component: CompetitionListComponent },
  { path: 'teams', component: TeamListComponent },
  // { path: 'team/:id', component: TeamDetailComponent },
  // { path: 'teams/new', component: TeamAddComponent, canActivate: [AuthGuard], data: { role: ['TEAM_MANAGER'] } },
  { path: 'unauthorized', component: UnauthorizedComponent },
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
