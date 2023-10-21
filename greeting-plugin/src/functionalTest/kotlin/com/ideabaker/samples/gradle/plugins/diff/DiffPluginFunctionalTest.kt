package com.ideabaker.samples.gradle.plugins.diff

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.io.TempDir
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.util.stream.Stream
import kotlin.test.assertTrue


/**
 * A simple functional test for the 'com.ideabaker.samples.gradle.plugin.greeting' plugin.
 */
class DiffPluginFunctionalTest {

  @field:TempDir
  lateinit var projectDir: File

  private val buildFile by lazy { projectDir.resolve("build.gradle.kts") }
  private val settingsFile by lazy { projectDir.resolve("settings.gradle.kts") }

  @ParameterizedTest
  @MethodSource("provideStringsForTaskRunner")
  fun canRunTask(str1: String, str2: String, expected: String) {
    val result = runTask(str1, str2)
    // Verify the result
    assertTrue(result.output.contains(expected))
  }

  companion object {
    @JvmStatic
    private fun provideStringsForTaskRunner(): Stream<Arguments> {
      return Stream.of(
        Arguments.of("string1", "string2", "string1 and string2 have the same size"),
        Arguments.of("s1", "string2", "string2 was larger"),
        Arguments.of("string1", "s2", "string1 was larger"),
      )
    }
  }

  private fun runTask(string1: String, string2: String): BuildResult {
    // Set up the test build
    settingsFile.writeText("")
    buildFile.writeText(
      """
            plugins {
                id("com.ideabaker.samples.gradle.plugins.diff-plugin")
            }
            
            diffConfig {
              string1.set("$string1")
              string2.set("$string2")
            }
        """.trimIndent()
    )

    // Run the build
    val runner = GradleRunner.create()
    runner.forwardOutput()
    runner.withPluginClasspath()
    runner.withArguments("diff")
    runner.withProjectDir(projectDir)
    return runner.build()
  }
}
