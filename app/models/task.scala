package models

import java.util.{Date,TreeSet,Set=>JSet,List=>JList,ArrayList}
import play.db.jpa._
import play.data.Validators._

@Entity
class Task(
	// fields
	
	@Required var title: String, 
	
	@Lob @MaxSize(10000) var comments: String,
	
	@Required var beginning: Date, 
	
	@Required var end: Date, 
	
	@Required @ManyToOne var user: User
	

) extends Model {
	var allDay: Boolean = false
	
	// instance methods
}

object Task extends QueryOn[Task] {
	//placeholder for extra finder methods (if any) 
}