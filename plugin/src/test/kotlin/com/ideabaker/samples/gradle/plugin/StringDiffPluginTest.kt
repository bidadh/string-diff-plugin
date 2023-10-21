package com.ideabaker.samples.gradle.plugin

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertNotNull

/**
 * A simple unit test for the 'com.ideabaker.samples.gradle.plugin.greeting' plugin.
 */
class StringDiffPluginTest {
  @Test
  fun `plugin registers task`() {
    // Create a test project and apply the plugin
    val project = ProjectBuilder.builder().build()
    project.plugins.apply("com.ideabaker.samples.gradle.plugin.greeting")

    // Verify the result
    assertNotNull(project.tasks.findByName("greeting"))
  }
}
