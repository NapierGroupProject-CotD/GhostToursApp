package ghost

class MainController {

    def index() {
		redirect(controller:"staff", action:"login")
	}
}
