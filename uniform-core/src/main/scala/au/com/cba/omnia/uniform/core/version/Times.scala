package au.com.cba.omnia.uniform.core
package version

import java.text.SimpleDateFormat
import java.util.{Date, TimeZone}

import sbt._, Keys._

object Times {
  def timestamp(instant: Date, format: String = "yyyyMMddHHmmss") = {
    val formatter = new SimpleDateFormat("yyyyMMddHHmmss")
    formatter.setTimeZone(TimeZone.getTimeZone("UTC"))
    formatter.format(instant)
  }

  lazy val now =
    new Date
}
