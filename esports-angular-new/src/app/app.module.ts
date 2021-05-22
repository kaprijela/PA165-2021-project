import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {PlayerService} from "./service/player.service";
import {PlayerListComponent} from './components/player-list/player-list.component';
import {CompetitionService} from "./service/competition.service";
import {CompetitionListComponent} from './components/competition-list/competition-list.component';

@NgModule({
  declarations: [
    AppComponent,
    PlayerListComponent,
    CompetitionListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [PlayerService, CompetitionService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
