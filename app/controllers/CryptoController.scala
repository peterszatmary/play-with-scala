package controllers


import java.net.UnknownHostException
import javax.inject._

import play.api.libs.json.JsResult.Exception
import play.api.libs.json.Json
import play.api.mvc._
import play.api.libs.ws._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.TimeoutException
import scala.concurrent.duration.Duration


@Singleton
class CryptoController @Inject() (cc: ControllerComponents)(ws: WSClient) extends AbstractController(cc) {


  /**
    * async rest call consumption. Result top ten crypto currencies from coinmarket cap API.
    * @return
    */
  def topTen() = Action.async {

    ws.url("https://api.coinmarketcap.com/v1/ticker/?limit=10")
      .withRequestTimeout(Duration.Inf)
      .get()
      .map(response => response.body)
      .map(respBody => Json.parse(respBody))
      .map(json => Ok(json))
      .recover ({
        case e: TimeoutException =>     InternalServerError("Timeout")
        case e: UnknownHostException => ServiceUnavailable("No internet or bad url")
      })
  }
}