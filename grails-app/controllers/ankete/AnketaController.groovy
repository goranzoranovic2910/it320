package ankete

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured;

class AnketaController {

    AnketaService anketaService
    PitanjeService pitanjeService
    KorisnikService korisnikService
    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN','ROLE_REGULAR'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond anketaService.list(params), model:[anketaCount: anketaService.count()]
    }

    @Secured(['ROLE_ADMIN','ROLE_REGULAR'])
    def show(Long id) {
        respond anketaService.get(id)
    }

    @Secured(['ROLE_ADMIN'])
    def create() {
        respond new Anketa(params)
    }

    @Secured(['ROLE_ADMIN'])
    def save(Anketa anketa) {
        if (anketa == null) {
            notFound()
            return
        }

        try {
            anketaService.save(anketa)
        } catch (ValidationException e) {
            respond anketa.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'anketa.label', default: 'Anketa'), anketa.id])
                redirect anketa
            }
            '*' { respond anketa, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN','ROLE_REGULAR'])
    def vote(Long id){
        def query = Pitanje.where{anketa.id == id}

       def izborTest = Izbor.where{anketa.id == id && (korisnik.id == springSecurityService.currentUser.id  || ipAdress == request.remoteAddr )}
       if(izborTest.list().size() > 0){
            redirect(action:'alreadyVoted')
            return
        }        

        def pitanje = query.listOrderById(order:"asc")[0]

        redirect(controller:'pitanje', action:'vote',id:pitanje.id)        
    }

    @Secured(['ROLE_ADMIN','ROLE_REGULAR'])
    def alreadyVoted(){

    }

    @Secured(['ROLE_ADMIN','ROLE_REGULAR'])
    def reports(Long id){

        respond anketaService.get(id)
    }

    @Secured(['ROLE_ADMIN'])
    def edit(Long id) {
        respond anketaService.get(id)
    }

    @Secured(['ROLE_ADMIN'])
    def update(Anketa anketa) {
        if (anketa == null) {
            notFound()
            return
        }

        try {
            anketaService.save(anketa)
        } catch (ValidationException e) {
            respond anketa.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'anketa.label', default: 'Anketa'), anketa.id])
                redirect anketa
            }
            '*'{ respond anketa, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def sendMailNew(Long id){
        
        def anketa = anketaService.get(id)
        for(korisnik in Korisnik.list()){
             sendMail {
                to korisnik.email
                subject "Kreirana je nova anketa: ${anketa.naslov}"
                html "<html><body><h1>Kreirana je nova anketa: ${anketa.naslov}</h1><p>Novu anketu mozete pogledati <a href='http://localhost:8080/anketa/vote/${anketa.id}'>ovde.</a></p></body></html>"
            }

        }
        
        redirect action:"mailSent"
    }

    @Secured(['ROLE_ADMIN'])
    def sendMailFinished(Long id){

        def anketa = anketaService.get(id)
        for(korisnik in Korisnik.list()){
             sendMail {
                to korisnik.email
                subject "Zatvorena je anketa:${anketa.naslov}"
                html "<html><body><h1>Zavrsena je anketa: ${anketa.naslov}</h1><p>Rezultate ankete mozete pogledati <a href='http://localhost:8080/anketa/reports/${anketa.id}'>ovde.</a></p></body></html>"
            }
        }
        
        redirect action:"mailSent"
    }

    @Secured(['ROLE_ADMIN'])
    def mailSent(){

    }

    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        anketaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'anketa.label', default: 'Anketa'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'anketa.label', default: 'Anketa'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
