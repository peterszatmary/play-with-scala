package controllers

import javax.inject._
import play.api.mvc._
import scala.io.Source


/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class FromFileController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {




  val JSON_FILE_PATH : String = "public/coins.json"

  def symbols() = Action { implicit request: Request[AnyContent] =>

    val symbols : List[String] = getCoinSymbols(JSON_FILE_PATH)
    Ok(symbols + "")
  }

  def stringSymbols() = Action { implicit request: Request[AnyContent] =>

    val result = getCoinSymbols(JSON_FILE_PATH)
    Ok(stringFromSymbols(result))
  }










  /**
    * get symbols of coins from json file as list
    * @param path
    * @return
    */
  private def getCoinSymbols(path : String) : List[String] = {

    Source.fromFile(path).getLines.toStream
      .filter(x => x.contains("Symbol"))
      .map(x => x.replaceAll("\"Symbol\":",""))
      .map(x => x.replaceAll(",", ""))
      .map(x =>x.replaceAll("\"", ""))
      .map(x => x.trim)
      .toList
  }

  /**
    * result is string with format x,x,x,x,x,x,x,x,x
    * @param symbols
    * @return
    */
  private def stringFromSymbols(symbols : List[String]) : String = {
    symbols.mkString(",")
  }
}