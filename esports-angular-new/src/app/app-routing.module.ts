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
import {PlayerDetailComponent} from "./components/player-detail/player-detail.component";
import {TeamNewComponent} from "./components/team-new/team-new.component";
import {CompetitionDetailComponent} from "./components/competition-detail/competition-detail.component";

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'players', component: PlayerListComponent },
  { path: 'players/id/:id', component: PlayerDetailComponent },
  { path: 'competitions', component: CompetitionListComponent },
  { path: 'competitions/id/:id', component: CompetitionDetailComponent},
  { path: 'teams', component: TeamListComponent },
  { path: 'teams/id/:id', component: TeamDetailComponent },
  { path: 'teams/name/:name', component: TeamDetailComponent },
  { path: 'teams/new', component: TeamNewComponent, canActivate: [AuthGuard], data: { role: ['TEAM_MANAGER'] } },
  { path: 'unauthorized', component: UnauthorizedComponent },
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
