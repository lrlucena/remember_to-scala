package models

import java.util.{Date,TreeSet,Set=>JSet,List=>JList,ArrayList}
import play.db.jpa._
import play.data.Validators._

@Entity
class Tag(
	// fields
	
	@Required var name: String,
	
	@Required @ManyToOne var user: User
	
) extends Model {
	// instance methods and fields with default values
	
	@ManyToMany(mappedBy="tags")
	var tasks: JList[Task] = new ArrayList[Task]

    override def toString = name
}

object Tag extends QueryOn[Tag] {

}