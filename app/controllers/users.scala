package controllers

import play._
import play.mvc._
import play.data.validation._ 
import models._

object Users extends Controller {
    
	def login = {
		Template
	}
		
	def authenticate(email: String, password: String) = {
        User.connect(email, password) match {
            case Some(u) => session.put("user", u.email)
							flash.success("Logged in successfully.")
                            Action(Application.index)
                            
            case None    => flash.error("Oops, bad email or password.")
                            flash.put("email", email)
                            Action(login)
        }
    }
    
    def logout = {
        session.clear()
        flash.success("You have been disconnected.")
        Action(Application.index)
    }

	def register = Template
	
	def save(@Valid user: User) = {
		if ((user.password == user.passwordConfirmation) && (User.find("byEmail", user.email).first == None) && (user.validateAndSave())){
			flash.success("You were successfully registered. Now, log in.")
			Action(Users.login)
		} else if (request.isAjax){
			BadRequest
		} else {
			//flash.error("Some errors were found. Check the fields above.")
			val errors2 = scala.collection.mutable.HashMap.empty[String,String]
			if (user.password != user.passwordConfirmation){
				errors2 += ("passwordConfirmation" -> "Doesn't match the password")
			}
			if (User.find("byEmail", user.email).first != None){
				errors2 += ("email" -> "Already in use")
			}
			renderArgs += "errors2" -> errors2
			"@register".asTemplate(user)
		}
	}
}