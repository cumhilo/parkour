import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    id("com.github.johnrengelman.shadow") version ("7.0.0")
}

val pluginName = "parkour"

group = "me.cxmilo.$pluginName"
version = "2.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.unnamed.team/repository/unnamed-public/")
}

val targetJavaVersion = 8
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
    }
}

dependencies {
    val spigot = "org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT"

    compileOnly(spigot)
    compileOnly("org.jetbrains:annotations:22.0.0")

    implementation("team.unnamed.gui:core:2.3.3-SNAPSHOT")
    implementation("me.yushust.message:core:6.0.17")
    implementation("me.yushust.message:sourcetype-bukkit-yml:6.0.17")
}

tasks.withType<ShadowJar> {
    archiveFileName.set("$pluginName.jar")
}