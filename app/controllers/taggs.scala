package controllers
import java.util._
import play._
import play.mvc._
import play.data.validation._ 
import play.data.binding._
import models._

object Taggs extends Controller with Secure with Defaults {
	def list = {
		Template("tags" -> Tag.find("user_id", connectedUser.id).fetch)
	}
	
	def form(id: Long) = {
		Template("tag" -> Tag.findById(id).orNull)
	}

	def save(@Valid tag: Tag) = {
		tag.user = connectedUser
		if (tag.name != null){
			tag.name = tag.name.toLowerCase()
		}
		if (tag.validateAndSave()){
			flash.success("Tag saved successfully.")
			Action(list)
		} else {
			//flash.error("Some errors were found. Check the fields above.")
			"@form".asTemplate(tag)
		}
	}

	def delete(id: Long) = {
		Tag.findById(id).head.delete()
		flash.success("Tag deleted successfully.")
		Action(list)
	}
}