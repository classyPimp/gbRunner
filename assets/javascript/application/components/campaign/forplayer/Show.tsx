import { BaseReactComponent } from "../../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { Campaign } from '../../../models/Campaign'
import { Switch } from "react-router";
import { Modal } from '../../shared/Modal'
import autobind from 'autobind-decorator'
import { CampaignComponents } from '../CampaignComponents'
import { ModelCollection } from '../../../../modelLayer/ModelCollection';
import { User } from '../../../models/User'
import { UserComponents } from '../../user/UserComponents'
import { UserToCampaignInvite } from '../../../models/UserToCampaignInvite'
import { Route, Link, match } from 'react-router-dom';
import { GameCharacterComponents } from '../../gamecharacter/GameCharacterComponents'

export class Show extends BaseReactComponent {

    props: {
      match?: match<any>
    }

    state: {
      campaign: Campaign
      playerUsers: ModelCollection<User>
      gameMasters: ModelCollection<User>
    } = {
      campaign: null,
      playerUsers: null,
      gameMasters: null
    }

    modal: Modal

    
    componentDidMount() {
      Campaign.forPlayerShow({wilds: {"campaignId": this.props.match.params.campaignId}}).then((campaign)=>{
         let playerUsers = campaign.extractPlayerUsers()
         let gameMasters = campaign.extractGameMasters()
         this.setState({campaign, playerUsers, gameMasters})
      })
    }

    render(){
        if (!this.state.campaign) {
          return <div>
            ...loading
          </div>
        }
        return <div>
          <Modal
            ref={(it)=>{this.modal = it}}
          />
          <h1>
            {this.state.campaign.name}
          </h1>
          <p>
            {this.state.campaign.description}
          </p>
          <div>
            <h1>
              game masters
            </h1>
            {this.state.gameMasters.map((gameMaster)=>{
               return <div key={gameMaster.id}>
                 <p>
                   {gameMaster.name}
                 </p>
               </div>
            })}
          </div>
          <div>
            <h1>
              players:
            </h1>
            {this.state.playerUsers.map((playerUser)=>{
              return <div key={playerUser.id}>
                {playerUser.name}
              </div>
            })}
          </div>

          <div>
              <div className="pure-menu pure-menu-horizontal">
                  <ul className="pure-menu-list">
                      <li className="pure-menu-item">
                          <Link to={`/campaign/for-player/${this.props.match.params.campaignId}/primary-character`} className="pure-menu-link">
                              my character
                          </Link>
                      </li>
                  </ul>
              </div>
              <Switch>
                  <Route path={`/campaign/for-player/:campaignId/primary-character`} component={GameCharacterComponents.forPlayer.asprimaryplayercharacter.Show}/>
              </Switch>
          </div>
        </div>

    }

}
