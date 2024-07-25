<h3> Add dependencies </h3>
Add kotr version, ktor dependencies, kotlin-serialization and ktor bundle to libs.versions.toml.
add okhttp, darwin to gradle and serialisation for both gradle files


<h3>Configure http engine for platforms</h3>
Create createHttpClient which you can install logging and auth utils packages.

<h3> Add data handling utils class </h3>
Create Error interface, NetworkingError Enum and finally Result

<h3> Create the client</h3>
- start with creating the client like InsultSensorClient
- create a get/put/update/delete method
- surround in try catch
- check for error codes and map the Network.Error if needed

<h3> Pass the client to the app entry point </h3>
for Android this is in mainActivity, pass th client into the app composable.

for iOS, go to MainViewController and pass the same remember variable to app, but give the Darwin Engine instead of OKHTTP

<h3>Give Android internet permission</h3>
Update manifest with internet permissions

<h6>Documentation link for setup </h6>
https://ktor.io/docs/client-create-multiplatform-application.html