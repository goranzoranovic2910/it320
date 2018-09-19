package ankete

class BootStrap {

    def init = { servletContext ->

        def adminRole = Rola.findOrSaveWhere(authority: 'ROLE_ADMIN')
        def regularRole = Rola.findOrSaveWhere(authority: 'ROLE_REGULAR')

        def admin = Korisnik.findOrSaveWhere(username:'admin', password:'admin', email:'gzoranovic@gmail.com', godiste: 1980, pol:'M')
        def user = Korisnik.findOrSaveWhere(username:'testuser', password:'test', email:'gzoranovic@yahoo.com', godiste: 1983, pol:'Z')
        
        if(!admin.authorities.contains(adminRole)){
            KorisnikRola.create(admin, adminRole, true)
        }

        if(!user.authorities.contains(regularRole)){
            KorisnikRola.create(user, regularRole, true)
        }
    }
    def destroy = {
    }
}
