package controllers

import play.api.libs.json.Json

class User(name: String, surname: String) {

  object User {
    //implicit val userFormat = Json.format[User]
  }
}