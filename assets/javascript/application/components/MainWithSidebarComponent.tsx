import { BaseReactComponent } from "../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { SideBar } from './SideBar'
import { Router, Route, BrowserRouter, Link, match, Switch, Redirect } from 'react-router-dom';
import { UserComponents } from "./user/UserComponents";
import { NotFound } from "./shared/NotFound";
import { History } from "history";
import { CampaignComponents } from './campaign/CampaignComponents';
import { UserToCampaignInviteComponents } from './usertocampaigninvite/UserToCampaignInvitateComponents'
import { ItemComponents } from './item/ItemComponents';


export class MainWithSidebarComponent extends BaseReactComponent {

    props: {
      history?: History
    }

    render(){
        return <div>
          <div className="pure-g">
            <div className="pure-u-1-8">
              <SideBar/>          
            </div>
            <div className="pure-u-7-8">
              <Switch>
                <Route exact path="/" render={()=>{return <h1>welcome</h1>}}/>  
                <Route path="/user/management" component={UserComponents.management.Main}/>
                <Route path="/campaign/for-game-master" component={CampaignComponents.forGameMaster.Main}/>
                <Route path="/campaign/for-player" component={CampaignComponents.forPlayer.Main}/>
                <Route path="/user-to-campaign-invite/:invitationToken" component={UserToCampaignInviteComponents.Show}/>
                <Route path="/item/manage" component={ItemComponents.forAdmin.Main}/>
                <Route render={() => {console.log(`redirecting to 404 from ${this.props.history.location.pathname}`);return <Redirect to="/404"/>}}/>
              </Switch>
            </div>
          </div>
        </div>
    }

}
