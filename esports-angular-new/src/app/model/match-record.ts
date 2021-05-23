import {Team} from "./team";
import {Player} from "./player";
import {Competition} from "./competition";

export class MatchRecord {
  id: number;
  competition: Competition;
  matchNumber: number;
  player: Player;
  team: Team;
  score: number;

  constructor(id: number,
              competition: Competition,
              matchNumber: number,
              player: Player,
              team: Team,
              score: number) {
    this.id = id;
    this.competition = competition;
    this.matchNumber = matchNumber;
    this.player = player;
    this.team = team;
    this.score = score;
  }
}
