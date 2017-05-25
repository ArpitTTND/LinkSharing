package linksharing

import com.ttn.co.UserCO
import com.ttn.vo.ResourceVO

class LoginController {
    def userService

    def dummy() {
        render "Hello World"
    }

    def index() {
        List<ResourceVO> topPosts = Resource.topPost()
        List<ResourceVO> recentShares = Resource.recentShares()
        if (session.getAttribute('user')) {
            println session.user
            redirect(controller: 'user', action: 'index')
        } else {
            render view: 'index', model: [topPosts: topPosts, recentShares: recentShares]
        }
    }


    def loginHandler(String username, String password) {

        User user = User.findByUserNameAndPassword(username, password)
        if (user) {
            if (user.isActive) {
                session.setAttribute("user", user)
                redirect(controller: 'user', action: 'index')
            } else {
                log.error("User is not active")
                flash.singleError = "Account not active Kindly contact admin"
                redirect(controller: 'login', action: 'index')
            }

        } else {
            log.error("User not exist in database")
            flash.singleError = "Wrong Username or Password"
            redirect(controller: 'login', action: 'index')


        }
    }

    def logout() {
        session.invalidate()
        println "logout"
        redirect(controller: 'login', action: 'index')
    }

    def register(UserCO userCO) {
        User user = new User()
        bindData(user, userCO)
        def file = request.getFile('image')
        if (file) {
            user.photo = file.getBytes()
        }
        if (user.save(flush: true)) {
            session.setAttribute('user', user)
            flash.message = 'Registered Successfully'
            redirect(controller: 'login', action: 'index')
        } else {
            if (user.hasErrors()) {

                flash.error = user.errors.allErrors.collect { message(error: it) }
                redirect(controller: 'login', action: 'index')
            }
        }

    }


    def loginWithGoogle(UserCO userCO) {
        println userCO
        User user = User.findByUserName(userCO.userName)
        if (user) {
            println user
            session.setAttribute('user', user)
        } else {
            User user1 = new User()
            bindData(user1, userCO)
            user1.save(flush: true, failOnError: true)
        }
        return true
    }

}