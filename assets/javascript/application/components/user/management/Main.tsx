import { BaseReactComponent } from "../../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { Router, Route, BrowserRouter, Link, match, Switch } from 'react-router-dom';
import { UserComponents } from '../UserComponents';

export class Main extends BaseReactComponent {

    props: {
        match?: match<any>
    }

    render(){
        return <div>
            <div className="pure-menu pure-menu-horizontal">
                <ul className="pure-menu-list">
                    <li className="pure-menu-item">
                        <Link to="/user/management/index" className="pure-menu-link">
                            index
                        </Link>
                    </li>
                </ul>
            </div>
            <Switch>
                <Route path={`${this.props.match.path}/index`} component={UserComponents.management.Index}/>
            </Switch>
        </div>
    }

}
