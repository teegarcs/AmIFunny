The AI Joke Generator app by Clinton Teegarden

#### Build
```bash
./gradlew clean build
```

#### Run Locally

```bash
./gradlew clean aifunny-app:bootRun
```

#### Run in IDE (Intellij)
right click on AIFunnyApp.kt

### Testing
```
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{
    "jokeType": Dad, 
    "familyFriendly": true,
    "jokeNouns": ["dog", "cat"]
}' \
  http://localhost:8080/joke
```
