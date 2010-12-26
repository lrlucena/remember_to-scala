package controllers

import play._
import play.mvc._
import models._

// trait Secure {
// 	self: Controller =>
// 	@Before
// 	def check {
// 		session("user") match {
// 			name: String => info("Logged as %s", name)
// 			_ => Security.login
// 		}
// 	}
// }

trait Secure extends Controller {
    
    @Before 
	def check = {        
        session("user") match {
            case Some(email) => renderArgs += "user" -> User.find("byEmail", email).first.getOrNotFound; Continue
            case None => Action(Authentication.login)
        }
    }
    
    @Util
	def connectedUser = renderArgs.get("user").asInstanceOf[User]
    
}