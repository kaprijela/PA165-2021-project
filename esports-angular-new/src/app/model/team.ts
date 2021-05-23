import {Player} from "./player";

export class Team {
  id: number;
  name: string;
  abbreviation: string;
  description: string;
  players: Player[];

  constructor(id: number, name: string, abbreviation: string, description: string, players: Player[]) {
    this.id = id;
    this.name = name;
    this.abbreviation = abbreviation;
    this.description = description;
    this.players = players;
  }
}
