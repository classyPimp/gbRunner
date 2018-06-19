import { IModelProperties } from '../../modelLayer/interfaces/IModelProperties';
import { HasOne } from '../../modelLayer/annotations/HasOne';
import { HasMany } from '../../modelLayer/annotations/HasMany'
import { Promise } from 'es6-promise';
import { RequestOptions, Route } from '../../modelLayer/annotations/ModelRoute';
import { Property } from '../../modelLayer/annotations/Property';
import { BaseModel } from '../../modelLayer/BaseModel';
import {Account} from './Account'
import  { ModelRegistry } from '../../modelLayer/ModelRegistry' 
import { ModelCollection } from '../../modelLayer/ModelCollection';
import { UserRole } from './UserRole'

export class User extends BaseModel {
   
    static className = "user"

    @Property
    id: number

    @Property
    name: string
 

    @Route("POST", {url: "/api/user/registration"})
    registrationCreate: (options?: RequestOptions) => Promise<any>

    beforeRegistrationCreateRequest(options: RequestOptions) {
      this.beforeCreateRequest(options)
    }

    afterRegistrationCreateRequest(options?: RequestOptions) {
      this.afterCreateRequest(options)
    }



    @Route("POST", {url: "/api/session"})
    login: (options?: RequestOptions) => Promise<any>

    beforeLoginRequest(options: RequestOptions) {
      this.beforeCreateRequest(options)
    }

    afterLoginRequest(options: RequestOptions) {
        this.afterCreateRequest(options)
    }    

    @Route("DELETE", {url: "/api/session"})
    logout: (options?: RequestOptions) => Promise<any>

    afterLogoutReuqest(options: RequestOptions) {
        options.deferredPromise.then((resp) => {
            let newUser = new User(resp)
            newUser.validate()
            return newUser
        })
    }

    @Route("GET", {url: "/api/users/forSearchForm"})
    static forSearchFormIndex: (options?: RequestOptions) => Promise<ModelCollection<User>>

    static afterForSearchFormIndexRequest(options: RequestOptions) {
      this.afterIndexRequest(options)
    }

    @HasOne("Account")
    account: Account

    @HasMany("UserRole")
    userRoles: ModelCollection<UserRole>

    nameValidator(){
        // if (!(this.name)) {
        //     this.addErrorFor("name", "no name given")
        // }
    }

    @Route("GET", {url: "/api/user/management"})
    static managementIndex: (options?: RequestOptions) => Promise<any>

    static afterManagementIndexRequest(options: RequestOptions) {
      this.afterIndexRequest(options)
    }

    @Route("GET", {url: "/api/user"})
    static index: (options?: RequestOptions) => Promise<ModelCollection<User>>

    @Route("GET", {url: "/api/user/as-player-of-campaign/:campaignId"})
    static asPlayerOfCampaignIndex: (options?: RequestOptions) => Promise<ModelCollection<User>>

    static afterAsPlayerOfCampaignIndexRequest(options: RequestOptions) {
      this.afterIndexRequest(options)
    }

}
