# create user
curl -X PUT -d '{"operand":{"username":"manager2", "password": "123456"}}' -H "accept: application/json" -H "Content-Type: application/json" "http://localhost:8080/api/v1/auth/user"

curl -X POST -d '{"username":"manager", "password": "123456"}' -H "accept: application/json" -H "Content-Type: application/json" "http://localhost:8080/login"

curl -X POST -d '{"operand":{}}' -H "accept: application/json" -H "Content-Type: application/json" "http://localhost:8080/api/v1/auth/token"
curl -X POST -d '{"operand":{"accessToken":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW5hZ2VyIiwiZXhwIjoxNjgzNTExNjAyLCJpYXQiOjE2ODM1MTE1NzJ9.FsrooMFteLYcA4IBFwnYoQWWR6IykDXfrpxi_XD08cqK-4wf8ysU1gKHM1NPLrxFfWZKYgWoLnoH881rXSTXKg"}}' -H "accept: application/json" -H "Content-Type: application/json" "http://localhost:8080/api/v1/auth/token"
