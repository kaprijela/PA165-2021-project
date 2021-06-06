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
import {PlayerNewComponent} from "./components/player-new/player-new.component";
import {HomeComponent} from "./components/home/home.component";
import {CompetitionDetailComponent} from "./components/competition-detail/competition-detail.component";
import {CompetitionNewComponent} from "./components/competition-new/competition-new.component";

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'player/:id', component: PlayerDetailComponent },
  { path: 'players', component: PlayerListComponent },
  { path: 'players/new', component: PlayerNewComponent, canActivate: [AuthGuard], data: { role: ['PLAYER_MANAGER'] } },
  { path: 'competitions', component: CompetitionListComponent },
  { path: 'competitions/id/:id', component: CompetitionDetailComponent},
  { path: 'competitions/new', component: CompetitionNewComponent},
  { path: 'teams', component: TeamListComponent },
  { path: 'home', component: HomeComponent },
  { path: 'teams/abbr/:abbr', component: TeamDetailComponent },
  { path: 'teams/new', component: TeamNewComponent, canActivate: [AuthGuard], data: { role: ['TEAM_MANAGER'] } },
  { path: 'unauthorized', component: UnauthorizedComponent },
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
