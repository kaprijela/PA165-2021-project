#! /bin/bash
while IFS="," read -r name gender
do
curl -H "Content-Type: application/json"  -d '{"name":"'"$name"'", "gender":"'"$gender"'"}'  http://localhost:8080/pa165/api/v2/players/create
done < <(head -n +100 esports-sample-data/src/main/resources/players.csv)

while IFS="," read -r name abr desc
do
curl -H "Content-Type: application/json"  -d '{"name":"'"$name"'", "abbreviation":"'"$abr"'", "description":"'"$desc"'"}'  http://localhost:8080/pa165/api/v2/teams/
done < <(head -n +100 esports-sample-data/src/main/resources/Teams-2.csv)