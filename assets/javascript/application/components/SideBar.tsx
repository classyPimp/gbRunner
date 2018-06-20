import { BaseReactComponent } from '../../reactUtils/BaseReactComponent';
import * as React from 'react'
import { Link } from 'react-router-dom';
import { UserComponents } from './user/UserComponents'
import { CurrentUser } from '../services/CurrentUser';


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
                {CurrentUser.instance.hasRole("SUPER_USER") &&
                    <li className="pure-menu-item">
                        <Link to="/user/management" className="pure-menu-link">
                            user management
                        </Link>
                    </li>
                }
                <li className="pure-menu-item">
                    <Link to="/campaign/for-game-master" className="pure-menu-link">
                        campaigns where I'm gm
                    </Link>
                </li>
                <li className="pure-menu-item">
                    <Link to="/campaign/for-player" className="pure-menu-link">
                        campaigns I play
                    </Link>
                </li>
            </ul>
        </div>
    }

}