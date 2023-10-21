package com.ideabaker.samples.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A simple 'hello world' plugin.
 */
@Suppress("unused")
class GreetingPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    project.tasks.register("greeting") {
      this.doLast {
        println("Hello from plugin 'com.ideabaker.samples.gradle.plugin.greeting'")
      }
    }
  }
}
