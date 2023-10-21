package com.ideabaker.samples.gradle.plugin

import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * A simple functional test for the 'com.ideabaker.samples.gradle.plugin.greeting' plugin.
 */
class StringDiffPluginFunctionalTest {

  @field:TempDir
  lateinit var projectDir: File

  private val buildFile by lazy { projectDir.resolve("build.gradle.kts") }
  private val settingsFile by lazy { projectDir.resolve("settings.gradle.kts") }

  @Test
  fun `can run task`() {
    // Set up the test build
    settingsFile.writeText("")
    buildFile.writeText(
      """
            plugins {
                id("com.ideabaker.samples.gradle.plugin.greeting")
            }
        """.trimIndent()
    )

    // Run the build
    val runner = GradleRunner.create()
    runner.forwardOutput()
    runner.withPluginClasspath()
    runner.withArguments("greeting")
    runner.withProjectDir(projectDir)
    val result = runner.build()

    // Verify the result
    assertTrue(result.output.contains("Hello from plugin 'com.ideabaker.samples.gradle.plugin.greeting'"))
  }
}
