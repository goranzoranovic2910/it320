package ankete

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured;

@Secured(['ROLE_ADMIN'])
class RolaController {

    RolaService rolaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond rolaService.list(params), model:[rolaCount: rolaService.count()]
    }

    def show(Long id) {
        respond rolaService.get(id)
    }

    def create() {
        respond new Rola(params)
    }

    def save(Rola rola) {
        if (rola == null) {
            notFound()
            return
        }

        try {
            rolaService.save(rola)
        } catch (ValidationException e) {
            respond rola.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'rola.label', default: 'Rola'), rola.id])
                redirect rola
            }
            '*' { respond rola, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond rolaService.get(id)
    }

    def update(Rola rola) {
        if (rola == null) {
            notFound()
            return
        }

        try {
            rolaService.save(rola)
        } catch (ValidationException e) {
            respond rola.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'rola.label', default: 'Rola'), rola.id])
                redirect rola
            }
            '*'{ respond rola, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        rolaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'rola.label', default: 'Rola'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'rola.label', default: 'Rola'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
