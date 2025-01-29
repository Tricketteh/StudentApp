package controllers

import javax.inject._

import play.api.mvc._
import play.api.libs.json._
import models.Student
import repositories.StudentRepository
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class StudentController @Inject()(cc: ControllerComponents, studentRepository: StudentRepository) extends AbstractController(cc) {

  def listStudents: Action[AnyContent] = Action.async { implicit request =>
    studentRepository.findAll().map { students =>
      Ok(Json.toJson(students))
    }
  }

  def getStudent(id: String): Action[AnyContent] = Action.async { implicit request =>
    studentRepository.findById(id).map {
      case Some(student) => Ok(Json.toJson(student))
      case None => NotFound
    }
  }

  def createStudent: Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[Student].fold(
      errors => {
        Future.successful(BadRequest(Json.obj("message" -> JsError.toJson(errors))))
      },
      student => {
        studentRepository.create(student).map { _ =>
          Created(Json.toJson(student))
        }
      }
    )
  }

  def updateStudent(id: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[Student].fold(
      errors => {
        Future.successful(BadRequest(Json.obj("message" -> JsError.toJson(errors))))
      },
      student => {
        studentRepository.update(id, student).map { _ =>
          Ok(Json.toJson(student))
        }
      }
    )
  }

  def deleteStudent(id: String): Action[AnyContent] = Action.async { implicit request =>
    studentRepository.delete(id).map { _ =>
      NoContent
    }
  }
}