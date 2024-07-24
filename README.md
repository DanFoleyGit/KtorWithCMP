<h3> Add dependencies </h3>
Add kotr version, ktor dependencies, kotlin-serialization and ktor bundle to libs.versions.toml.
add okhttp, darwin to gradle and serialisation for both gradle files


<h3>Configure http engine for platforms</h3>
Create createHttpClient which you can install logging and auth utils packages.

<h3> Add data handling utils class </h3>


This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…