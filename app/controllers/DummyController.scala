package controllers

import javax.inject._

import play.api.libs.json.Json
import play.api.mvc._

@Singleton
class DummyController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def collectionExamples() = Action { request: Request[AnyContent] =>

    val list = List.newBuilder.+=("1").+=("2").+=("3").+=("w").+=("e").+=("s").result()
    val map = Map.newBuilder.+=("a" -> "1").+=("b" -> "2").+=("c" -> "3").result();
    val a  = Json.toJson(map) // json play api
    Ok(a);
  }
}