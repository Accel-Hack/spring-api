curl -X POST -d '{"username":"manager", "password": "123456"}' -H "accept: application/json" -H "Content-Type: application/json" "http://localhost:8080/login"

curl -X POST -d '' -H "accept: application/json" -H "Content-Type: application/json" "http://localhost:8080/api/v1/refreshToken"
curl -X POST -d '' -H "accept: application/json" -H "Content-Type: application/json" "http://localhost:8080/api/v1/refreshToken"
