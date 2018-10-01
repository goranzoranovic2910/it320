package ankete

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured;

class KorisnikController {

    KorisnikService korisnikService
    KorisnikRolaService korisnikRolaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond korisnikService.list(params), model:[korisnikCount: korisnikService.count()]
    }

    @Secured(['ROLE_ADMIN','ROLE_REGULAR'])
    def show(Long id) {
        respond korisnikService.get(id), model: [korisnikRole:KorisnikRola.where {korisnik.id == id}.list()]
    }

    def create() {
        respond new Korisnik(params)
    }

    def save(Korisnik korisnik) {
        if (korisnik == null) {
            notFound()
            return
        }

        try {
            Korisnik k = korisnikService.save(korisnik)
            def regularRole = Rola.findOrSaveWhere(authority: 'ROLE_REGULAR')
            KorisnikRola.create(k, regularRole, true)
        } catch (ValidationException e) {
            respond korisnik.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'korisnik.label', default: 'Korisnik'), korisnik.id])
                redirect korisnik
            }
            '*' { respond korisnik, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN','ROLE_REGULAR'])
    def edit(Long id) {
        respond korisnikService.get(id)
    }

    @Secured(['ROLE_ADMIN','ROLE_REGULAR'])
    def update(Korisnik korisnik) {
        if (korisnik == null) {
            notFound()
            return
        }

        try {
            korisnikService.save(korisnik)
        } catch (ValidationException e) {
            respond korisnik.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'korisnik.label', default: 'Korisnik'), korisnik.id])
                redirect korisnik
            }
            '*'{ respond korisnik, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        korisnikService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'korisnik.label', default: 'Korisnik'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'korisnik.label', default: 'Korisnik'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
