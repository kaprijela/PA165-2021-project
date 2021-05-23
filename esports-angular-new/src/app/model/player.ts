export class Player {
  id: number;
  name: string;
  gender: string;
  team: string;

  constructor(id: number, name: string, gender: string, team: string) {
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.team = team;
  }
}
