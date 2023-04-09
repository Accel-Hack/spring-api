# create user
curl -X POST -d '{"username":"manager2", "password": "123456"}' -H "accept: application/json" -H "Content-Type: application/json" "http://localhost:8080/api/v1/user/add"

curl -X POST -d '{"username":"manager", "password": "123456"}' -H "accept: application/json" -H "Content-Type: application/json" "http://localhost:8080/login"

curl -X POST -d '' -H "accept: application/json" -H "Content-Type: application/json" "http://localhost:8080/api/v1/refreshToken"
curl -X POST -d '{"accessToken":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW5hZ2VyIiwiZXhwIjoxNjgxMDMzNDI1LCJpYXQiOjE2ODEwMzMzOTV9.qAtnfoUS22VWCxthkeJiPdTErrnP_-LomSWep4U_QA-5pKVVwVfgRMZEA_GPBDKZNr3J980AuGxz9RU6qeM6sg","refreshToken":"AJhkzcMBh8nGqcPSZvfSd9Ze"}' -H "accept: application/json" -H "Content-Type: application/json" "http://localhost:8080/api/v1/refreshToken"
