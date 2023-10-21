plugins {
  `java-gradle-plugin`
  `kotlin-dsl`
  `maven-publish`
  kotlin("jvm") version "1.9.0"
}

publishing {
  publications {
    create<MavenPublication>("maven") {
      groupId = "com.ideabaker.samples.gradle.plugin"
      artifactId = "string-diff-plugin"
      version = "0.0.1"
      from(components["java"])
    }
  }
}

group = "com.raysaz.gradle.plugins"
version = "0.0.1"

repositories {
  mavenCentral()
}

dependencies {
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

gradlePlugin {
  // Define the plugin
  val greeting by plugins.creating {
    id = "com.ideabaker.samples.gradle.plugin.greeting"
    implementationClass = "com.ideabaker.samples.gradle.plugin.StringDiffPlugin"
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
