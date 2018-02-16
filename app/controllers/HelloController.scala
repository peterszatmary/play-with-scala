package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class HelloController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def hello() = Action { implicit request: Request[AnyContent] =>
    Ok("I am the easiest rest endpoint ever :)")
  }
}