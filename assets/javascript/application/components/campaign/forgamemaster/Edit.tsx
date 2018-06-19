import { BaseReactComponent } from "../../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { MixinFormableTrait } from '../../../../reactUtils/plugins/formable/MixinFormableTrait';
import { Campaign } from '../../../models/Campaign';
import { PlainInputElement } from '../../../../reactUtils/plugins/formable/formElements/PlainInput';
import autobind from "autobind-decorator";
import { ApplicationComponent } from '../../ApplicationComponent';
import { FlashMessageQueue } from '../../shared/FlashMessageQueue';
import { match } from 'react-router-dom'

export class Edit extends MixinFormableTrait(BaseReactComponent) {

    props: {
      match?: match<any>
      campaignId?: number,
      onUpdateSuccess?: (campaign: Campaign) => any 
    }

    state: {
      campaign: Campaign
    } = {
      campaign: null
    }

    componentDidMount() {
      let campaignId
      if (this.props.campaignId) {
        campaignId = this.props.campaignId
      } else {
        campaignId = this.props.match.params.campaignId
      }

      Campaign.forGameMasterEdit({wilds: {"id": campaignId}}).then((campaign)=>{
        this.setState({campaign})
      })
    }

    render(){
        if (!this.state.campaign) {
          return <div>
            ...Loading
          </div>
        }
        
        return <div>
          <PlainInputElement
            model={this.state.campaign}
            propertyName="name"
            registerInput={this.registerInput}
            optional={{
              placeholder: "name"
            }}
          />
          <PlainInputElement
            model = {this.state.campaign}
            propertyName = "description"
            registerInput = {this.registerInput}
            optional = {{
              placeholder: "description"
            }}
          />
          <button onClick={this.update}>
            update
          </button>
        </div>
    }

    @autobind
    update() {
      this.collectInputs()
      this.state.campaign.forGameMasterUpdate().then((campaign)=>{
        if (!campaign.isValid()) {
          this.setState({campaign})
          return
        }
         
        ApplicationComponent.instance.flashMessageQueue.addMessage(
          "updated successfully"
        )

        if (this.props.onUpdateSuccess) {
          this.props.onUpdateSuccess(campaign)
        }
      })     
    }

}
