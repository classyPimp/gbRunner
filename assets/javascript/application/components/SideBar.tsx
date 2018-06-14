import { BaseReactComponent } from '../../reactUtils/BaseReactComponent';
import * as React from 'react'
import { Link } from 'react-router-dom';
import { UserComponents } from './user/UserComponents'


export class SideBar extends BaseReactComponent {

    render() {
        return <div className="SideBar pure-menu custom-restricted-width">
            <UserComponents.LoginStatus/>
            <ul className="pure-menu-list">           
                <li className="pure-menu-item">
                    <Link to="/" className="pure-menu-link">
                        home
                    </Link>
                </li>
            </ul>
        </div>
    }

}