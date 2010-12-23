package models

import play.db.jpa._
import play.data.Validators._

@Entity
class User(
	// fields
	
	@Email
	@Required
    var email: String,

	@Required
    var password: String,

    var fullName: String
) extends Model {

	// instance methods
	
    var isAdmin = false 
//    override def toString = email
}

object User extends QueryOn[User] {
//placeholder for extra finder methods (if any) 
}