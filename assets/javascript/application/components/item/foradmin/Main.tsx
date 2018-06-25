import { BaseReactComponent } from "../../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { Link, Route } from 'react-router-dom';
import { Switch } from 'react-router';
import { ItemComponents } from '../ItemComponents';

export class Main extends BaseReactComponent {

    render(){
        return <div>
            <div className="pure-menu pure-menu-horizontal">
                <ul className="pure-menu-list">
                    <li className="pure-menu-item">
                        <Link to="/item/manage/index" className="pure-menu-link">
                            list items
                        </Link>
                    </li>
                    <li className="pure-menu-item">
                        <Link to="/item/manage/blueprint/index" className="pure-menu-link">
                            list blue prints
                        </Link>
                    </li>
                    <li className="pure-menu-item">
                        <Link to="/item/manage/new" className="pure-menu-link">
                            creat new item from blueprint
                        </Link>
                    </li>
                </ul>
            </div>
            <Switch>
                <Route path={`${this.props.match.url}/index`} component={ItemComponents.forAdmin.Index}/>
                <Route path={`${this.props.match.url}/new`} component={ItemComponents.forAdmin.New}/>
                <Route path={`${this.props.match.url}/blueprint/index`} component={ItemComponents.asBlueprint.forAdmin.Index}/>
            </Switch>
        </div>
    }

}
