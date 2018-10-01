package ankete

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class KorisnikRolaController {

    KorisnikRolaService korisnikRolaService
    KorisnikService korisnikService
    RolaService rolaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond korisnikRolaService.list(params), model:[korisnikRolaCount: korisnikRolaService.count()]
    }

    def show(Long id) {
        respond korisnikRolaService.get(id)
    }

    def create() {
        respond new KorisnikRola(params), model: [sviKorisnici:korisnikService.list(), sveRole:rolaService.list()]
    }

    def save(KorisnikRola korisnikRola) {
        if (korisnikRola == null) {
            notFound()
            return
        }

        try {
            korisnikRolaService.save(korisnikRola)
        } catch (ValidationException e) {
            respond korisnikRola.errors, view:'create'
            return
        }

        //request.withFormat {
        //    form multipartForm {
         //       flash.message = message(code: 'default.created.message', args: [message(code: 'korisnikRola.label', default: 'KorisnikRola'), korisnikRola.id])
        //        redirect korisnikRola
        //    }
       //    '*' { respond korisnikRola, [status: CREATED] }
        //}
        redirect korisnikRola.korisnik
        return
    }

    def edit(Long id) {
        respond korisnikRolaService.get(id)
    }

    def update(KorisnikRola korisnikRola) {
        if (korisnikRola == null) {
            notFound()
            return
        }

        try {
            korisnikRolaService.save(korisnikRola)
        } catch (ValidationException e) {
            respond korisnikRola.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'korisnikRola.label', default: 'KorisnikRola'), korisnikRola.id])
                redirect korisnikRola
            }
            '*'{ respond korisnikRola, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        korisnikRolaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'korisnikRola.label', default: 'KorisnikRola'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'korisnikRola.label', default: 'KorisnikRola'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
