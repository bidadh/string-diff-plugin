package com.ideabaker.samples.gradle.plugins.diff

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

/**
 *
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @since 17/10/2023 06:22
 */
abstract class DiffTask: DefaultTask() {
  @get:Input
  abstract val string1: Property<String>

  @get:Input
  abstract val string2: Property<String>

  @TaskAction
  fun diff() {
    val str1 = string1.get()
    val str2 = string2.get()
    val output = if(str1.length == str2.length) {
      "$str1 and $str2 have the same size"
    } else if (str1.length > str2.length) {
      "$str1 was larger"
    } else {
      "$str2 was larger"
    }

    println(output)
  }
}
