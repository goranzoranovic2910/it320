package ankete

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured;

@Secured(['ROLE_ADMIN'])
class OdgovorController {

    OdgovorService odgovorService
    PitanjeService pitanjeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond odgovorService.list(params), model:[odgovorCount: odgovorService.count()]
    }

    def show(Long id) {
        respond odgovorService.get(id)
    }

    def create(Long id) {

        respond new Odgovor(pitanje:pitanjeService.get(id))
    }

    def save(Odgovor odgovor) {
        if (odgovor == null) {
            notFound()
            return
        }

        try {
            odgovorService.save(odgovor)
        } catch (ValidationException e) {
            respond odgovor.errors, view:'create'
            return
        }

        redirect(controller: "pitanje", action: "show", id: odgovor.pitanje.id)
        return

    }

    def edit(Long id) {
        respond odgovorService.get(id)
    }

    def update(Odgovor odgovor) {
        if (odgovor == null) {
            notFound()
            return
        }

        try {
            odgovorService.save(odgovor)
        } catch (ValidationException e) {
            respond odgovor.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'odgovor.label', default: 'Odgovor'), odgovor.id])
                redirect odgovor
            }
            '*'{ respond odgovor, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        odgovorService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'odgovor.label', default: 'Odgovor'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'odgovor.label', default: 'Odgovor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
