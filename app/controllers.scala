package controllers

import play._
import play.mvc._

object Application extends Controller {
    
    def index = render()
    
}


object Home extends Controller {
	
	def index {
		val notice = "Mensagem de sucesso."
		val alert = "Mensagem de erro."
		val current_user = "mateus"
		render(notice, alert, current_user)
	}
		
	
}
