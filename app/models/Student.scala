package models

import play.api.libs.json.{Json, OFormat}

case class Student(
  id: Option[String],
  lastName: String,
  firstName: String,
  middleName: String,
  group: String,
  averageGrade: Double
)

object Student {

  implicit val studentFormat: OFormat[Student] = Json.format[Student]
}
