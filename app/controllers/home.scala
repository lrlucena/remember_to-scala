package controllers

import play._
import play.mvc._
import play.data.validation._ 
import models._

object Home extends Controller {
    
	def index = {
		val currentUser = User.findById(1.toLong).first
		Template("currentUser" -> currentUser)
	}
}