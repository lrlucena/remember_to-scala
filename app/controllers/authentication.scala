package controllers

import play._
import play.mvc._
import play.data.validation._ 
import models._

object Authentication extends Controller {
    
	def login = Template
		
	def authenticate(username: String, password: String) = {
        User.connect(username, password) match {
            case Some(u) => session.put("user", u.email)
                            Action(Home.index)
                            
            case None    => flash.error("Oops, bad email or password")
                            flash.put("username", username)
                            Action(login)
        }
    }
    
    def logout = {
        session.clear()
        flash.success("You have been disconnected")
        Action(login)
    }
}