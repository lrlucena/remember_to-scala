package models

import java.util.{Date,TreeSet,Set=>JSet,List=>JList,ArrayList}
import play.db.jpa._
import play.data.Validators._

@Entity
@Table(uniqueConstraints=Array(new UniqueConstraint(columnNames=Array("email"))))
class User(
	// fields
	
	@Email @Required var email: String,

	@Required var password: String,

	var fullName: String

) extends Model {
	// instance methods and fields with default values
	
	@OneToMany(mappedBy="user", cascade=Array(CascadeType.ALL))
	var tasks: JList[Task] = new ArrayList[Task]
	
    var isAdmin = false

    override def toString = email
}

object User extends QueryOn[User] {
	//placeholder for extra finder methods (if any)
	
	def connect(email: String, password: String) = {
        find("byEmailAndPassword", email, password).first
    }
}