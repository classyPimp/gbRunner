import { CookieHandler } from './CookiesHandler';
import { EmptyClass } from '../../modelLayer/utils/EmptyClass';
import { User } from '../models/User';
import { MixinPubSubbableTrait } from '../../simplepubsub/MixinPubSubbableTrait';


export interface LoginStatusListener {
    onLoggedInEvent: (user: User)=>any
    onLoggedOutEvent: (user: User)=>any
}

export class CurrentUser extends MixinPubSubbableTrait(EmptyClass) {

    static instance = new CurrentUser()

    loggedIn: Boolean = false
    loggedInInstance: User = null

    logIn(user: User) {
       this.loggedIn = true
       this.loggedInInstance = user
       this.publishLoggedIn(user)
    }

    logOut(user: User) {
        this.loggedIn = false
        this.loggedInInstance = null
        this.publishLoggedOut(user)
    }
    
    subscribeLoginStatusListener(subscriber: LoginStatusListener){
        this.addSubscriberUnder("loginStatusListeners", subscriber)
    }

    unsubcsribeLoginStatusListener(subscriber: LoginStatusListener) {
        this.removeSubscriberFrom("loginStatusListeners", subscriber)
    }
    
    publishLoggedIn(user: User){
        console.log("publishing logged in!")
        this.publish("loginStatusListeners", "onLoggedInEvent", user)
    }

    publishLoggedOut(user: User){
        this.publish("loginStatusListeners", "onLoggedOutEvent", user)
    }

    tryLoginFromCookie(){
        let cookies = CookieHandler.convertToObject()
        if (cookies["jwt"]) {
            let encodedUserPart = cookies["jwt"].split('.')[1]
            let decodedUserPart = atob(encodedUserPart)
            let userParams = JSON.parse(decodedUserPart)
            if (userParams['userRoles']) {
              userParams['userRoles'] = JSON.parse(userParams['userRoles'])
            }
            let user = new User(userParams)
            this.logIn(user)
        }
    }

    hasRole(roleName: string): boolean {
        if (!this.loggedInInstance) {
            return false
        }
        if (!this.loggedInInstance.userRoles) {
          return false
        }
        for (let userRole of this.loggedInInstance.userRoles.array) {
            if (userRole.name === roleName) {
                return true
            }
        }
        return false
    }
    

}



