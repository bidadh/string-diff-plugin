package com.ideabaker.samples.gradle.plugins.diff

import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

/**
 *
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @since 22/10/2023 01:47
 */
class DiffPluginTest {
  @Test
  fun `plugin registers task`() {
    // Create a test project and apply the plugin
    val project = ProjectBuilder.builder().build()
    project.plugins.apply("com.ideabaker.samples.gradle.plugins.diff-plugin")

    // Verify the result
    assertNotNull(project.tasks.findByName("diff"))
  }
}
