package controllers

import play._
import play.mvc._
import play.data.validation._ 
import models._

// TRAITS
trait Secure extends Controller {
    
    @Before 
	def check = {        
        session("user") match {
            case Some(email) => renderArgs += "user" -> User.find("byEmail", email).first.getOrNotFound; Continue
            case None => Action(Users.login)
        }
    }
    
    @Util
	def connectedUser = renderArgs.get("user").asInstanceOf[User]
}

trait Defaults extends Controller {
    
	@Before 
	def setDefaults = {
		session("user") match {
			case Some(email) => renderArgs += "connectedUser" -> User.find("byEmail", email).first.orNull; Continue
			case None => Continue;
		}
	}
}


object Application extends Controller with Defaults {
    
	def index = {
		Template()
	}
	
	def about = {
		Template()
	}
}


