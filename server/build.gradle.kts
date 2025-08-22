import io.ktor.plugin.features.DockerPortMapping
import io.ktor.plugin.features.DockerPortMappingProtocol

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.serialization)
    application
}

group = "kmp.multimodule.sample"
version = "1.0.0"

application {
    mainClass.set("kmp.multimodule.sample.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

ktor {
    docker {
        jreVersion.set(JavaVersion.VERSION_21)
        localImageName.set("kmp-sample-docker-image")
        imageTag.set("0.0.1-preview")

        portMappings.set(
            listOf(
                DockerPortMapping(
                    outsideDocker = 80,
                    insideDocker = 8080,
                    DockerPortMappingProtocol.TCP,
                )
            )
        )
    }

    fatJar {
        archiveFileName.set("fat.jar")
    }
}

dependencies {
    implementation(libs.logback)

    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.config.yaml)
    implementation(libs.ktor.server.negotiation)
    implementation(libs.ktor.cors)
    implementation(libs.ktor.serialization.json)

    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)

    implementation(libs.hikari)
    implementation(libs.postgresql)
}

tasks {
    create("stage").dependsOn("installDist")
}