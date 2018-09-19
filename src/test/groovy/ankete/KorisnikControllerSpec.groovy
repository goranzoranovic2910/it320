package ankete

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import grails.validation.ValidationException
import spock.lang.*

class KorisnikControllerSpec extends Specification implements ControllerUnitTest<KorisnikController>, DomainUnitTest<Korisnik> {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {
        given:
        controller.korisnikService = Mock(KorisnikService) {
            1 * list(_) >> []
            1 * count() >> 0
        }

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        !model.korisnikList
        model.korisnikCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
        controller.create()

        then:"The model is correctly created"
        model.korisnik!= null
    }

    void "Test the save action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        controller.save(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/korisnik/index'
        flash.message != null
    }

    void "Test the save action correctly persists"() {
        given:
        controller.korisnikService = Mock(KorisnikService) {
            1 * save(_ as Korisnik)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        populateValidParams(params)
        def korisnik = new Korisnik(params)
        korisnik.id = 1

        controller.save(korisnik)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/korisnik/show/1'
        controller.flash.message != null
    }

    void "Test the save action with an invalid instance"() {
        given:
        controller.korisnikService = Mock(KorisnikService) {
            1 * save(_ as Korisnik) >> { Korisnik korisnik ->
                throw new ValidationException("Invalid instance", korisnik.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def korisnik = new Korisnik()
        controller.save(korisnik)

        then:"The create view is rendered again with the correct model"
        model.korisnik != null
        view == 'create'
    }

    void "Test the show action with a null id"() {
        given:
        controller.korisnikService = Mock(KorisnikService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.show(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the show action with a valid id"() {
        given:
        controller.korisnikService = Mock(KorisnikService) {
            1 * get(2) >> new Korisnik()
        }

        when:"A domain instance is passed to the show action"
        controller.show(2)

        then:"A model is populated containing the domain instance"
        model.korisnik instanceof Korisnik
    }

    void "Test the edit action with a null id"() {
        given:
        controller.korisnikService = Mock(KorisnikService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.edit(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the edit action with a valid id"() {
        given:
        controller.korisnikService = Mock(KorisnikService) {
            1 * get(2) >> new Korisnik()
        }

        when:"A domain instance is passed to the show action"
        controller.edit(2)

        then:"A model is populated containing the domain instance"
        model.korisnik instanceof Korisnik
    }


    void "Test the update action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/korisnik/index'
        flash.message != null
    }

    void "Test the update action correctly persists"() {
        given:
        controller.korisnikService = Mock(KorisnikService) {
            1 * save(_ as Korisnik)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        populateValidParams(params)
        def korisnik = new Korisnik(params)
        korisnik.id = 1

        controller.update(korisnik)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/korisnik/show/1'
        controller.flash.message != null
    }

    void "Test the update action with an invalid instance"() {
        given:
        controller.korisnikService = Mock(KorisnikService) {
            1 * save(_ as Korisnik) >> { Korisnik korisnik ->
                throw new ValidationException("Invalid instance", korisnik.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(new Korisnik())

        then:"The edit view is rendered again with the correct model"
        model.korisnik != null
        view == 'edit'
    }

    void "Test the delete action with a null instance"() {
        when:"The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then:"A 404 is returned"
        response.redirectedUrl == '/korisnik/index'
        flash.message != null
    }

    void "Test the delete action with an instance"() {
        given:
        controller.korisnikService = Mock(KorisnikService) {
            1 * delete(2)
        }

        when:"The domain instance is passed to the delete action"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(2)

        then:"The user is redirected to index"
        response.redirectedUrl == '/korisnik/index'
        flash.message != null
    }
}






