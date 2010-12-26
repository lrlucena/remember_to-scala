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
			flash.success("Your were successfully registered. Log in.")
			Action(Users.login)
		} else if (request.isAjax){
			BadRequest
		} else {
			if (user.password != user.passwordConfirmation){ 
				flash.error("Password does not match password confirmation.")
			}
			if (User.find("byEmail", user.email).first != None){
				flash.error("Email is already in use.")
			}
			"@register".asTemplate(user)
		}
	}
}