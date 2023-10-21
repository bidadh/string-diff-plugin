plugins {
  `java-gradle-plugin`
  `kotlin-dsl`
  `maven-publish`
  kotlin("jvm") version "1.9.0"
}

group = "com.ideabaker.samples.gradle.plugins"
version = "0.0.3"

publishing {
  repositories {
    mavenLocal()
    maven {
      name = "github"
      url = uri("https://maven.pkg.github.com/bidadh/string-diff-plugin")
      credentials {
        username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR") ?: "bidadh"
        password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
      }
    }
  }
}

repositories {
  mavenCentral()
}

dependencies {
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

gradlePlugin {
  val greeting by plugins.creating {
    id = "$group.greeting-plugin"
    implementationClass = "$group.GreetingPlugin"
  }

  val diff by plugins.creating {
    id = "$group.diff-plugin"
    implementationClass = "$group.diff.DiffPlugin"
  }
}

// Add a source set for the functional test suite
val functionalTestSourceSet: SourceSet = sourceSets.create("functionalTest") {
}

configurations["functionalTestImplementation"].extendsFrom(configurations["testImplementation"])
configurations["functionalTestRuntimeOnly"].extendsFrom(configurations["testRuntimeOnly"])

// Add a task to run the functional tests
val functionalTest by tasks.registering(Test::class) {
  testClassesDirs = functionalTestSourceSet.output.classesDirs
  classpath = functionalTestSourceSet.runtimeClasspath
  useJUnitPlatform()
}

gradlePlugin.testSourceSets.add(functionalTestSourceSet)

tasks.named<Task>("check") {
  // Run the functional tests as part of `check`
  dependsOn(functionalTest)
}

tasks.named<Test>("test") {
  // Use JUnit Jupiter for unit tests.
  useJUnitPlatform()
}
