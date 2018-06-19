import { BaseReactComponent } from "../../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { Switch } from "react-router";
import { Route, Link, match } from 'react-router-dom';
import { Campaign } from '../../../models/Campaign';
import { CampaignComponents } from '../CampaignComponents';

export class Main extends BaseReactComponent {

    props: {
        match?: match<any>
    }

    render(){
        return <div>
            <div className="pure-menu pure-menu-horizontal">
                <ul className="pure-menu-list">
                    <li className="pure-menu-item">
                        <Link to="/campaign/for-game-master/index" className="pure-menu-link">
                            index campaigns i gm
                        </Link>
                    </li>
                    <li>
                        <Link to={`${this.props.match.url}/new`} className="pure-menu-link">
                            create new campaign
                        </Link>
                    </li>
                </ul>
            </div>
            <Switch>
                <Route path={`${this.props.match.url}/index`} component={CampaignComponents.forGameMaster.Index}/>
                <Route path={`${this.props.match.url}/new`} component={CampaignComponents.forGameMaster.New}/>
                <Route path={`${this.props.match.url}/:campaignId`} component={CampaignComponents.forGameMaster.Show}/>
            </Switch>
        </div>
    }

}
