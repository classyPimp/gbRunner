import { CurrentUser } from '../../../services/CurrentUser';
import autobind from 'autobind-decorator';
import { PlainInputElement } from '../../../../reactUtils/plugins/formable/formElements/PlainInput';
import { User } from '../../../models/User';
import { BaseReactComponent } from '../../../../reactUtils/BaseReactComponent';
import { MixinFormableTrait } from '../../../../reactUtils/plugins/formable/MixinFormableTrait';
import * as React from 'react';
import { Account } from '../../../models/Account';

export class New extends MixinFormableTrait(BaseReactComponent) {

    state: {user: User} = {
      user: new User({account: {}, avatar: {}})
    }

    render(){
        return <div>
            <PlainInputElement model={this.state.user} propertyName="name" registerInput={this.registerInput}
              optional={{
                placeholder: "name"
              }}
            />
            <PlainInputElement model={this.state.user.account} propertyName="email" registerInput={this.registerInput}
              optional={{
                placeholder: "email"
              }}
            />
            <PlainInputElement model={this.state.user.account} propertyName="password" registerInput={this.registerInput}
              optional={{
                placeholder: "password"
              }}
            />
            <PlainInputElement model={this.state.user.account} propertyName="passwordConfirmation" registerInput={this.registerInput}
              optional={{
                placeholder: "confirm password"
              }}
            />
            <button onClick={this.submit}>
                submit
            </button>
        </div>
    }

    @autobind
    submit(){
        this.collectInputs()
        let user = this.state.user
        user.registrationCreate().then((newUser)=>{
            if (newUser.isValid()) {
                CurrentUser.instance.logIn(newUser)
            } else {
                user.mergeWith(newUser)
            }
            this.setState({user})
        })
    }


}