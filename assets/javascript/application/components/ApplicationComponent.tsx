import autobind from 'autobind-decorator';
import { NotFound } from './shared/NotFound';
import { CurrentUser } from '../services/CurrentUser';
import * as React from 'react';
import { BaseReactComponent } from '../../reactUtils/BaseReactComponent'
import { Router, Route, BrowserRouter, Link, match, Switch } from 'react-router-dom';
import {XhrRequestMaker} from '../../modelLayer/utils/XhrRequestMaker'
import { FlashMessageQueue } from './shared/FlashMessageQueue';
import { ModelRegistry } from '../../modelLayer/ModelRegistry'
import { MainWithSidebarComponent } from './MainWithSidebarComponent'
import { UserComponents } from './user/UserComponents'

export class ApplicationComponent extends BaseReactComponent {

    static instance: ApplicationComponent

    flashMessageQueue: FlashMessageQueue

    props: {match?: match<any>, history?: any}

    constructor(props: any) {
        super(props)
        CurrentUser.instance.tryLoginFromCookie()
        XhrRequestMaker.onFailHandler = this.xhrOnFailHandler
        ApplicationComponent.instance = this
    }

    @autobind
    xhrOnFailHandler(xhr: XMLHttpRequest) {
        if (xhr.status === 404) {
            this.props.history.replace("/404", {})
        }
    }

    componentWillMount() {
      if (!this.userIsLoggedIn() && this.props.match.url !== "/404") {
        this.props.history.push("/user/session/new")
      } 
    }

    @autobind
    userIsLoggedIn(): Boolean {
      return CurrentUser.instance.loggedIn
    }

    render() {
        return <div>
            <FlashMessageQueue ref={(it)=>{this.flashMessageQueue = it}}/>
            <Switch>
                <Route path="/404" component={NotFound}/>
                <Route path="/user/session/new" component={UserComponents.session.New}/>
                <Route component={MainWithSidebarComponent}/>
            </Switch>
        </div>
    }

}
