package controllers

import play._
import play.mvc._
import play.data.validation._ 
import models._

object Tasks extends Controller with Secure with Defaults{
	def list() = {
		var tasks = Task.find("user", connectedUser)
	}
}