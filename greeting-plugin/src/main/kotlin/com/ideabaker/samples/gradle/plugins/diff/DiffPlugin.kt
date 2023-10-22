package com.ideabaker.samples.gradle.plugins.diff

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.register

/**
 *
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @since 17/10/2023 06:35
 */
@Suppress("unused")
class DiffPlugin: Plugin<Project> {
  override fun apply(project: Project) {
    val extension = project.extensions.create<DiffPluginExtension>("diffConfig")
  
    project.tasks.register<DiffTask>("diff") {
      group = "ideabaker-samples"

      string1.set(extension.string1)
      string2.set(extension.string2)
    }
  }
}

