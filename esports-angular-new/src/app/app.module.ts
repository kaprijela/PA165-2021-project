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

@NgModule({
  declarations: [
    AppComponent,
    PlayerListComponent,
    CompetitionListComponent,
    TeamListComponent,
    NavbarComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [PlayerService, CompetitionService, TeamService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
