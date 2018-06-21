import { BaseReactComponent } from "../../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { Campaign } from '../../../models/Campaign'
import { match } from 'react-router-dom'
import { Modal } from '../../shared/Modal'
import autobind from 'autobind-decorator'
import { CampaignComponents } from '../CampaignComponents'
import { ModelCollection } from '../../../../modelLayer/ModelCollection';
import { User } from '../../../models/User'
import { UserComponents } from '../../user/UserComponents'
import { UserToCampaignInvite } from '../../../models/UserToCampaignInvite'

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
      Campaign.forGameMasterShow({wilds: {"campaignId": this.props.match.params.campaignId}}).then((campaign)=>{
         let playerUsers = campaign.extractPlayerUsers()
         let gameMasters = campaign.extractGameMasters()
         console.log("calling setstate")
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
          <button onClick={this.initEdit}>
            edit
          </button>
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
            <button onClick={this.initPLayerAddition}>
              add player
            </button>
          </div>
        </div>
    }

    @autobind
    initEdit() {
      this.modal.open(
        <CampaignComponents.forGameMaster.Edit
          campaignId={this.props.match.params.campaignId}
          onUpdateSuccess={this.onUpdateSucces}
        />
      )
    }

    @autobind
    onUpdateSucces(campaign: Campaign) {
      this.modal.close()
      this.state.campaign.name = campaign.name
      this.state.campaign.description = campaign.description
      this.forceUpdate()
    }

    @autobind
    initPLayerAddition() {
      this.modal.open(
        <UserComponents.Index
          selectable={true}
          onSelect={this.openInviteConfirmation}
        />
      )
    }    

    @autobind
    openInviteConfirmation(user: User) {
      let userToCampaignInvite = new UserToCampaignInvite()
      userToCampaignInvite.userThatIsInvitedId = user.id
      userToCampaignInvite.create({wilds: {campaignId: this.props.match.params.campaignId}}).then((userToCampaignInvite)=>{
        this.modal.open(
          <div>
            <p>
              invitation token, copy and send it to user:
            </p>
            <p>
              {`${window.location.hostname}:${window.location.port}/user-to-campaign-invite/${userToCampaignInvite.invitationToken}`}
            </p>
          </div>
        )
      })
    }

}
