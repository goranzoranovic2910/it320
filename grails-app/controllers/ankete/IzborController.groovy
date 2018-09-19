package ankete

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured;

@Secured(['ROLE_ADMIN','ROLE_REGULAR'])
class IzborController {

    IzborService izborService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond izborService.list(params), model:[izborCount: izborService.count()]
    }

    def show(Long id) {
        respond izborService.get(id)
    }

    def create() {
        respond new Izbor(params)
    }

    def save(Izbor izbor) {
        if (izbor == null) {
            notFound()
            return
        }

        try {
            izborService.save(izbor)
        } catch (ValidationException e) {
            respond izbor.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'izbor.label', default: 'Izbor'), izbor.id])
                redirect izbor
            }
            '*' { respond izbor, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def edit(Long id) {
        respond izborService.get(id)
    }

    @Secured(['ROLE_ADMIN'])
    def update(Izbor izbor) {
        if (izbor == null) {
            notFound()
            return
        }

        try {
            izborService.save(izbor)
        } catch (ValidationException e) {
            respond izbor.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'izbor.label', default: 'Izbor'), izbor.id])
                redirect izbor
            }
            '*'{ respond izbor, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        izborService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'izbor.label', default: 'Izbor'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'izbor.label', default: 'Izbor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
