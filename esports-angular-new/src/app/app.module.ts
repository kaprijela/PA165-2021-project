import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule} from "@angular/router";
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {PlayerService} from "./service/player.service";
import {PlayerListComponent} from './components/player-list/player-list.component';
import {CompetitionService} from "./service/competition.service";
import {CompetitionListComponent} from './components/competition-list/competition-list.component';
import {TeamService} from "./service/team.service";
import {TeamListComponent} from './components/team-list/team-list.component';
import {NavbarComponent} from './components/navbar/navbar.component';
import {LoginComponent} from './components/login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {TeamDetailComponent} from './components/team-detail/team-detail.component';
import { UnauthorizedComponent } from './components/unauthorized/unauthorized.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { PlayerDetailComponent } from './components/player-detail/player-detail.component';
import { TeamNewComponent } from './components/team-new/team-new.component';

@NgModule({
  declarations: [
    AppComponent,
    PlayerListComponent,
    CompetitionListComponent,
    TeamListComponent,
    NavbarComponent,
    LoginComponent,
    TeamDetailComponent,
    UnauthorizedComponent,
    NotFoundComponent,
    PlayerDetailComponent,
    TeamNewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [PlayerService, CompetitionService, TeamService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
