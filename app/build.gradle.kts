plugins {
    id("java")
    application
}

group = "it.unicam.cs.ids"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass.set("it.unicam.cs.ids.app.Main")
}

tasks.test {
    useJUnitPlatform()
}

