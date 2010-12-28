package controllers
import java.util._
import play._
import play.mvc._
import play.data.validation._ 
import play.data.binding._
import models._

object Tasks extends Controller with Secure with Defaults {
	def list = {
		Template("tasks" -> Task.find("user_id", connectedUser.id).fetch)
	}
	
	def form(id: Long) = {
		Template("task" -> Task.findById(id).orNull)
	}

	def save(@Valid task: Task, tags: String) = {
		task.user = connectedUser
		task.tags = new java.util.ArrayList[Tag]()
		tags.split(" ").foreach{ tagName: String =>
			if (tagName != "" && tagName != " "){
				var tagAdd = Tag.find("byNameAndUser", tagName, connectedUser).first.orNull
				if (tagAdd == null){
					var tagAdd = new Tag(tagName, connectedUser)
					tagAdd.save()
				}
				if (!task.tags.contains(tagAdd)){
					task.tags.add(tagAdd)
				}
			}
		}
		if (task.validateAndSave()){
			flash.success("Task saved successfully.")
			Action(Tasks.list)
		} else {
			//flash.error("Some errors were found. Check the fields above.")
			"@form".asTemplate(task)
		}
	}
	
	def delete(id: Long) = {
		Task.findById(id).head.delete()
		flash.success("Task deleted successfully.")
		Action(list)
	}
	
	// Tag.findByNameAndUser("escola", u)
	
}