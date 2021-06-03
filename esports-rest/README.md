## REST API design

### Player

#### `GET /players`

- returns a list of all players

#### `GET /players/{id}`

- returns player identified by ID

#### `GET /players/{id}/stats`

- returns statistics for player identified by ID

#### `POST /players`

- creates a new player

#### `DELETE /players/{id}`

- deletes a player identified by ID

### Team

#### `GET /teams`

- returns a list of all teams

#### `GET /teams/{id}`

- returns team identified by ID

#### `GET /teams/abbr/{abbr}`

- returns team identified by abbreviation

#### `GET /teams/{id}/players`

- returns players for the team identified by ID

#### `GET /teams/abbr/{abbr}/players`

- returns players for the team identified by abbreviation

#### `GET /teams/{id}/competitions`

- returns competitions the team identified by ID participated in

#### `GET /teams/abbr/{abbr}/competitions`

- returns competitions the team identified by abbreviation participated in

#### `POST /teams`

- creates a new team

#### `DELETE /teams/{id}`

- deletes a team identified by ID

### Competition

#### `GET /competitions`

- returns a list of all competitions

#### `GET /competitions?game={GAME}`

- returns a list of all competitions filtered by game

#### `GET /competitions/{id}`

- returns competition identified by ID

#### `POST /competitions`

- creates a new competition

#### `DELETE /competitions/{id}`

- deletes a competition identified by ID
