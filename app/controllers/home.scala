package controllers

import play._
import play.mvc._
import play.data.validation._ 
import models._

object Home extends Controller with Secure {
    
	def index = {
		val currentUser = User.findById(1.toLong).first
		Template("currentUser" -> currentUser)
	}
}