// package controllers
//  
// import play._
// import play.mvc._
// import play.data.validation._
//  
// import models._
// 
// object Admin extends Controller with Defaults with Secure {
// 	
// }
// 
// object Authentication extends Controller {
//     
//     def login = Template
//     
//     def authenticate(username: String, password: String) = {
//         Users.connect(username, password) match {
//             case Some(u) => session.put("user", u.email)
//                             Action(Admin.index)
//                             
//             case None    => flash.error("Oops, bad email or password")
//                             flash.put("username", username)
//                             Action(login)
//         }
//     }
//     
//     def logout = {
//         session.clear()
//         flash.success("You have been disconnected")
//         Action(login)
//     }
//     
// }
// 
// 
// trait Secure extends Controller {
//     
//     @Before def check = {        
//         session("user") match {
//             case Some(email) => renderArgs += "user" -> Users.find("byEmail", email).first.getOrNotFound; Continue
//             case None => Action(Authentication.login)
//         }
//     }
//     
//     @Util def connectedUser = renderArgs.get("user").asInstanceOf[User]
//     
// }