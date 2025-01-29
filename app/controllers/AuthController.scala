package controllers

import javax.inject.Inject
import org.pac4j.core.profile.CommonProfile
import org.pac4j.play.scala.{Security, SecurityComponents}
import play.api.mvc._
import play.api.libs.json.Json
import scala.concurrent.ExecutionContext

class AuthController @Inject()(val controllerComponents: SecurityComponents)(implicit ec: ExecutionContext)
  extends BaseController with Security[CommonProfile] {

  def callback: Action[AnyContent] = Secure("Google2Client") { implicit request =>
    val profiles = request.profiles
    val profileOption = profiles.headOption

    profileOption match {
      case Some(profile) =>
        val accessToken = profile.getAttribute("access_token").toString
        Ok(Json.obj("access_token" -> accessToken))
      case None =>
        Unauthorized("No profile found")
    }
  }
}
