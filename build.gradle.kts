import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    id("fabric-loom") version "1.6.0"
}

group = "com.xini4"
version = "1.0.0"
val minecraftVersion = "1.21.11"

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")
    maven("https://maven.shedaniel.me/")
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:1.21.11+build.1:v2")
    modImplementation("net.fabricmc:fabric-api:0.90.0+1.21.11")
    modImplementation("me.shedaniel.cloth:cloth-config-fabric:8.1.9")
    modImplementation("com.terraformersmc:modmenu:6.3.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
