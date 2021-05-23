# PA165-esports

[![Build Status](https://travis-ci.com/kaprijela/PA165-esports.svg?branch=main)](https://travis-ci.com/kaprijela/PA165-esports)

Check our [project wiki](https://github.com/kaprijela/PA165-esports/wiki) for more information.

- To see our project as it was at the time of **milestone 1**, you can checkout the tag `m1` or browse the
  repository [here](https://github.com/kaprijela/PA165-esports/tree/m1).
- To see our project as it was at the time of **milestone 2**, you can checkout the tag `m2` or browse the
  repository [here](https://github.com/kaprijela/PA165-esports/tree/m2).

## Instructions for running the project

### Setup

First, you need to have installed `Node.js` and `npm`. Here you can get both at once: https://nodejs.org/en/
Verify everything works by running `node -v` and `npm -v`.

Then go to the `esports-angular-new` directory, which contains our Angular application, and
run `npm install @angular/cli -g` to install `angular-cli`. Verify by running `ng -v`. Then run `npm install` to install
npm dependencies.

### Running

Go to the `esports-angular` directory and run: `cd .. && mvn clean install && cd esports-angular && mvn cargo:run`. This
starts the Tomcat server with our REST service, let it run in the background.

Next, launch another instance of the terminal, and run `ng serve` to start the `angular-cli` development server.

### How to test the REST API

#### GET

- `curl -i -X GET http://localhost:8080/pa165/api/v2/competitions/`
- `curl -i -X GET http://localhost:8080/pa165/api/v2/teams/`
- `curl -i -X GET http://localhost:8080/pa165/api/v2/players/`

### Logging in to the web application

After completing steps in Setup and Running, access `http://localhost:4200` from the browser.

- You can log in as a **player** by using the username `player` and password `player`.
- You can log in as a **team manager** via the username `teamManager` and password `admin`.
- You can log in as a **competition** manager via the username `compManager` and password `admin`.
