import {Team} from "./team";

export class Competition {
  id: number;
  name: string;
  teams: Team[];
  location: string;
  prizepool: string;
  game: string;


  constructor(id: number, name: string, teams: Team[], location: string, prizepool: string, game: string) {
    this.id = id;
    this.name = name;
    this.teams = teams;
    this.location = location;
    this.prizepool = prizepool;
    this.game = game;
  }
}
