package ankete

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured;

@Secured(['ROLE_ADMIN'])
class PitanjeController {

    PitanjeService pitanjeService
    AnketaService anketaService
    IzborService izborService
    OdgovorService odgovorService

    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond pitanjeService.list(params), model:[pitanjeCount: pitanjeService.count()]
    }

    def show(Long id) {
        respond pitanjeService.get(id)
    }

    def create(Long id) {
        Pitanje p = new Pitanje(params)
        p.anketa = anketaService.get(id)

        respond p, model:[sveAnkete:anketaService.list()]
    }

    def save(Pitanje pitanje) {
        if (pitanje == null) {
            notFound()
            return
        }

        try {
            pitanjeService.save(pitanje)
        } catch (ValidationException e) {
            respond pitanje.errors, view:'create'
            return
        }

        redirect(controller:'anketa', action:'show',id:pitanje.anketa.id)
        return
    }

    @Secured(['ROLE_ADMIN','ROLE_REGULAR'])
    def vote(Long id){
        def pitanje = pitanjeService.get(id)

        pitanje.odgovori.add(new Odgovor(slobodanUnos: true))

        respond pitanje
    }

     @Secured(['ROLE_ADMIN','ROLE_REGULAR'])
    def voteSave(){

         def pitanje = pitanjeService.get(params.long('pitanje'))
        //Save checked izbor
         Long odgovorId = params.long('pitanjaGroup')
         def firstCheckedOdgovor = null
         if(odgovorId == 0){
             firstCheckedOdgovor = odgovorService.save(new Odgovor(slobodanUnos: true, pitanje: pitanje,tekst: params.slobodanUnos ))
         }
         else{
             firstCheckedOdgovor = odgovorService.get(odgovorId)
         }

        Izbor izbor = new Izbor(pitanje:firstCheckedOdgovor.pitanje,
                anketa:firstCheckedOdgovor.pitanje.anketa,
                odgovor:firstCheckedOdgovor,
                korisnik:springSecurityService.currentUser,
                datum:new Date(),
                ipAdress: request.remoteAddr)
        izborService.save(izbor)

        //Move to next pitanje if exists
        def nextPitanje = Pitanje.where { anketa.id == pitanje.anketa.id && id > pitanje.id }

        if(nextPitanje.size() == 0){
            respond pitanje.anketa, view:'finishedAnketa'
            return
        }

        Pitanje p = nextPitanje.find()
        respond p, view:'vote'
        return
    }

    @Secured(['ROLE_ADMIN','ROLE_REGULAR'])
    def report(Long id){

        def izboriCount = [:]
        def pitanje = pitanjeService.get(id)

        def izbori=[]
        boolean filtered = params.pol != null || params.godiste != null
        if(params.pol !=null && params.pol !=""){
            izbori = Izbor.findAllByPitanje(pitanje).findAll { it.korisnik.pol == params.pol}
        }

        if(params.godiste !=null && params.godiste !=""){
            izbori = Izbor.findAllByPitanje(pitanje).findAll { it.korisnik.godiste == params.int('godiste')}
        }

        if(!filtered){
            izbori = Izbor.findAllByPitanje(pitanje)
        }

        for(o in pitanje.odgovori){
            izboriCount.put(o.id, 0)
        }

        for(i in izbori){
            izboriCount.put(i.odgovor.id, izboriCount.get(i.odgovor.id)+1 )
        }
        respond pitanjeService.get(id), model:[izboriCount:izboriCount.values(), pol: params.pol, godiste:params.godiste]
   }


    def edit(Long id) {
        respond pitanjeService.get(id), model:[sveAnkete:anketaService.list()]
    }



    def update(Pitanje pitanje) {
        if (pitanje == null) {
            notFound()
            return
        }

        try {
            pitanjeService.save(pitanje)
        } catch (ValidationException e) {
            respond pitanje.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pitanje.label', default: 'Pitanje'), pitanje.id])
                redirect pitanje
            }
            '*'{ respond pitanje, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        pitanjeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pitanje.label', default: 'Pitanje'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pitanje.label', default: 'Pitanje'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
