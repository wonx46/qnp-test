# JWT Springboot for QNP Test
This project for qnp test only.pleae install mySQL and restore the table first.

## Usage

```python
run project: mvn spring-boot:run
or 
java -jar target/iwan-test-0.0.1-SNAPSHOT.jar
```

## Endpoints
```python
POST /authenticate

curl --location 'http://localhost:9999/authenticate' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=561DB700056684814CA3380CC0AE54C2' \
--data '{
    "username":"iwan-test",
    "password":"password"
}'

Response:
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpd2FuLXRlc3QiLCJleHAiOjE3MTAzNjQyNTAsImlhdCI6MTcxMDM0NjI1MH0.aOFWig5FbcE2LhcpbgZFuICjUNsW6eDmOXr6z9iLJgAdSQtg5luEEG7005mcq5wQ1-Onn77WScJw-UD18c065g"
}

```

```python
GET /v1/jsonplaceholder/fetch

curl --location 'http://localhost:9999/v1/jsonplaceholder/fetch' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpd2FuLXRlc3QiLCJleHAiOjE3MTAzNjQyNTAsImlhdCI6MTcxMDM0NjI1MH0.aOFWig5FbcE2LhcpbgZFuICjUNsW6eDmOXr6z9iLJgAdSQtg5luEEG7005mcq5wQ1-Onn77WScJw-UD18c065g' \
--header 'Cookie: JSESSIONID=561DB700056684814CA3380CC0AE54C2'

Response:
{
    "timestamp": 1710346276482,
    "status": 200,
    "message": "OK"
}
```


```python
POST /v1/contact/create

curl --location 'http://localhost:9999/v1/contact/create' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=ED6D44BA674246BA48F1500C6C330B0D' \
--data-raw '{
"name":"iwan",
"email":"abdurahman.iwan@gmail.com",
"phone":"081902512555",
"address":"Bandung"
}
'
Response:
{
    "timestamp": 1710346276482,
    "status": 200,
    "message": "OK"
}
```

```python
POST /v1/contact/update

curl --location 'http://localhost:9999/v1/contact/update' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=ED6D44BA674246BA48F1500C6C330B0D' \
--data-raw '{
"id":"1710340232056",
"name":"iwan abdur",
"email":"abdurahman.iwan@gmail.com",
"phone":"081902512555",
"address":"Bandung"
}
'
Response:
{
    "timestamp": 1710346276482,
    "status": 200,
    "message": "OK"
}
```


```python
POST /v1/contact/delete

curl --location 'http://localhost:9999/v1/contact/delete' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=ED6D44BA674246BA48F1500C6C330B0D' \
--data '{
"id":"1710340232056"
}
'
Response:
{
    "timestamp": 1710346276482,
    "status": 200,
    "message": "OK"
}
```
```python
GET /v1/contact/list

curl --location 'http://localhost:9999/v1/contact/list' \
--header 'Cookie: JSESSIONID=561DB700056684814CA3380CC0AE54C2'
Response:
{
    "timestamp": 1710340284447,
    "status": 200,
    "message": "OK",
    "data": [
        {
            "id": "1710340232056",
            "name": "iwan",
            "email": "abdurahman.iwan@gmail.com",
            "phone": "081902512555",
            "address": "Bandung"
        }
    ]
}

```
