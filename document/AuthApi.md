# Auth Api

## Apis

### create user
```bash
HEADER="Content-Type: application/json"
REQUEST='{"operand": {"username":"user1","password":"password1"}}'
curl -X PUT -H $HEADER -d $REQUEST http://localhost:8080/api/v1/auth/user
```

### sign in 
```bash
# call login api
AUTH=$(curl -X POST -H "Content-Type: application/json" -d '{"username":"manager","password":"123456"}' http://localhost:8080/login)

echo $AUTH
# success response
# sample_json. {"accessToken":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW5hZ2VyIiwiZXhwIjoxNjgyNTk0Nzc2LCJpYXQiOjE2ODI1OTQ3NDZ9.fG6C_hRVVPVGg_7retkV26XBlxQbn78SCKVyLLK8qu0lbM4NpjSXQNuGeccAxTJzxhMRPmekn038QPc092UGzg","refreshToken":"y3tjHKETSzu4m8BiKHhsVYJA"}
```

### refresh token
```bash
# call login api
curl -X POST -H "Content-Type: application/json" -d "{\"operand\": $AUTH}" http://localhost:8080/api/v1/auth/token
```


