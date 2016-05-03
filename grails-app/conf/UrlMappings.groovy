class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		//"/"(view:"/index")

        //"/"(controller: 'home', action: 'index')
        "/"(controller: 'index', action: 'index')



        "500"(view:'/error')
                // "/login/$action?"(controlleer:"login")
                // "/logout/$action?"(controlleer:"logout")
                
                //"/"(controller:"index")
	}
}
