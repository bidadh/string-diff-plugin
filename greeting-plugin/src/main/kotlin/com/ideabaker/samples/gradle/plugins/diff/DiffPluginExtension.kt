package com.ideabaker.samples.gradle.plugins.diff

import org.gradle.api.provider.Property

interface DiffPluginExtension {
  val string1: Property<String>
  val string2: Property<String>
}
