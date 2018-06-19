import { BaseReactComponent } from "../../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { Campaign } from '../../../models/Campaign'
import { match } from 'react-router-dom'
import { Modal } from '../../shared/Modal'
import autobind from 'autobind-decorator'
import { CampaignComponents } from '../CampaignComponents'
import { ModelCollection } from '../../../../modelLayer/ModelCollection';

export class Show extends BaseReactComponent {

    props: {
      match?: match<any>
    }

    state: {
      campaign: Campaign
      playerUsers: ModelCollection<User>
    } = {
      campaign: null,
      playerUsers: null
    }

    modal: Modal

    
    componentDidMount() {
      Campaign.forGameMasterShow({wilds: {"campaignId": this.props.match.params.campaignId}}).then((campaign)=>{
         let playerUsers = campaign.extractPlayerUsers()
         let gameMasters = campaign.extractGameMasters()
         this.setState({campaign})
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
          <p>
            {this.state.campaign.name}
          </p>
          <p>
            {this.state.campaign.description}
          </p>
          <button onClick={this.initEdit}>
            edit
          </button>
          <div>

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



}
