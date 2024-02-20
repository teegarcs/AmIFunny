The AI Joke Generator app by Clinton Teegarden
## Kotlin Multiplatform 
Project Directory: Project/AmIFunny_Mobile

XCode Project Directory: Project/AmIFunny_Mobile/iOSApp

Android Project Directory: Project/AmIFunny_Mobile/androidApp

Shared Code Directory: Project/AmIFunny_Mobile/Shared

#### Running with Local Services
If you are hitting the REST Services packaged with this project that you are running on your local machine, you will need to modify the Networking class to point to your local host. To do so, simply modify the `ROOT_URL` found in the following class within the commonMain package within the Shared Code: `com.teegarcs.amifunny.network.Networking.kt`

#### Architecture: 
![image](https://github.com/teegarcs/AmIFunny/assets/1863002/a9b986f8-b1f8-4266-bdfc-2b35ae57a5c9)

## REST Services
The REST services live in the Project/aifunny-app directory. The steps below are what you can take to run these outside of the IntelliJ IDE. 
#### Setup:
You will need to add your own PALM/Vertex API key to interact with the AI Model. To add your own, place it here: `aifunny-app/src/main/resources/application.properties`
Create your own API key here: https://ai.google.dev/tutorials/setup
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
