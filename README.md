<h1 align="center">
  colour
</h1>

<p align="center">
  <a href="https://jitpack.io/#exerro/colour"><img src="https://jitpack.io/v/exerro/colour.svg" alt="JitPack badge"/></a>
</p>

Defines an RGBA/HSLA colour API supporting conversion, interpolation, gamma
correction, and colour palettes.

## Installation

Check out the [releases](https://github.com/exerro/colour/releases), or
using a build system...

### Gradle (`build.gradle.kts`)

```kotlin
repositories {
    // ...
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("me.exerro:colour:1.0.1")
}
```

### Maven

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>me.exerro</groupId>
  <artifactId>colour</artifactId>
  <version>1.0.1</version>
</dependency>
```

## Testing the build before release

    ./gradlew clean && ./gradlew build && ./gradlew build publishToMavenLocal
