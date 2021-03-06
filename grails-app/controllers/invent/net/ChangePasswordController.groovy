package invent.net

import com.inventnet.security.*

class ChangePasswordController {
    
    def springSecurityService

    def index = { }
    
    def update = {
        
        def username = springSecurityService.principal.username        
        def user = SecUser.findByUsername( username )
        
        if( user.password != springSecurityService.encodePassword(params.password) ){
            user.errors.rejectValue("password", "secuser.passwor.error", [message(code: 'secuser.label', default: 'Cambiar Contraseña')] as Object[], "La contraseña no es igual a la del usuario")
            render(view: "index", model: [secUserInstance: user])
            return
        }        
        if( params.password2.trim() == '' || params.password1 != params.password2 ){
            user.errors.rejectValue("password", "secuser.passwor.error", [message(code: 'secuser.label', default: 'Cambiar Contraseña')] as Object[], "La confirmacion de la contraseña no es igual")
            render(view: "index", model: [secUserInstance: user])
            return        
        }
        
        if( params.password1 && params.password1.length() < 8 ||
            (!params.password1.matches('^.*\\p{Alpha}.*$') ||
                !params.password1.matches('^.*\\p{Digit}.*$')
            )) {
            user.errors.rejectValue("password", "secuser.passwor.error", [message(code: 'secuser.label', default: 'Cambiar Contraseña')] as Object[], "La contraseña debe tener 8 caranteres con una cobinacion de caracteres alfabeticos y numericos")
            render(view: "index", model: [secUserInstance: user])
            return            
        }   
        
        user.password = params.password1
        user.encodePassword()

        if( user.save(flush:true) ){
            flash.message = "Contraseña actualizada con exito"
            //render(view: "index")
            def url = "/"
            redirect uri: url
        }        
    }
}
