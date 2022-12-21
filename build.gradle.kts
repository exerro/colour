import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val lwjglNatives = run {
    val name = System.getProperty("os.name")!!
    val arch = System.getProperty("os.arch")!!

    when {
        arrayOf("Linux", "FreeBSD", "SunOS", "Unit").any { name.startsWith(it) } ->
            if (arrayOf("arm", "aarch64").any { arch.startsWith(it) })
                "natives-linux${if (arch.contains("64") || arch.startsWith("armv8")) "-arm64" else "-arm32"}"
            else
                "natives-linux"
        arrayOf("Mac OS X", "Darwin").any { name.startsWith(it) }                ->
            "natives-macos${if (arch.startsWith("aarch64")) "-arm64" else ""}"
        arrayOf("Windows").any { name.startsWith(it) }                           ->
            if (arch.contains("64"))
                "natives-windows${if (arch.startsWith("aarch64")) "-arm64" else ""}"
            else
                "natives-windows-x86"
        else -> throw Error("Unrecognized or unsupported platform. Please set \"lwjglNatives\" manually")
    }
}

plugins {
    java
    kotlin("jvm") version "1.7.21"
    `maven-publish`
}

allprojects {
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))

    testImplementation(platform("org.lwjgl:lwjgl-bom:3.3.1-SNAPSHOT"))

    testImplementation("org.lwjgl", "lwjgl")
    testImplementation("org.lwjgl", "lwjgl-glfw")
    testImplementation("org.lwjgl", "lwjgl-nanovg")
    testImplementation("org.lwjgl", "lwjgl-opengl")
    testRuntimeOnly("org.lwjgl", "lwjgl", classifier = lwjglNatives)
    testRuntimeOnly("org.lwjgl", "lwjgl-glfw", classifier = lwjglNatives)
    testRuntimeOnly("org.lwjgl", "lwjgl-nanovg", classifier = lwjglNatives)
    testRuntimeOnly("org.lwjgl", "lwjgl-opengl", classifier = lwjglNatives)
    testRuntimeOnly("org.lwjgl", "lwjgl-stb", classifier = lwjglNatives)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "me.exerro"
            artifactId = "colour"
            version = "1.0.1"

            from(components["java"])
        }
    }
}

allprojects {
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.contracts.ExperimentalContracts"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.time.ExperimentalTime"
        kotlinOptions.freeCompilerArgs += "-Xcontext-receivers"
        kotlinOptions.freeCompilerArgs += "-language-version"
        kotlinOptions.freeCompilerArgs += "1.7"
    }
}
