# spring-boot-demo /w jwt

```
mvn clean install
java -jar target/spring-boot-demo-0.1-SNAPSHOT.jar
```

## login
```
curl -XPOST -d '{"username":"user","password":"password"}' -H 'Content-Type: application/json' -v http://localhost:8080/login
```
`Authorization` header holds the JWT.

## hello

The `/hello` resource requires a JWT and returns a message like

```
{
    "message": "Hello $username!"
}
```

where `$username` is taken from the `sub` (subject) field of the JWT.