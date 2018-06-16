import { BaseReactComponent } from "../../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { MixinFormableTrait } from '../../../../reactUtils/plugins/formable/MixinFormableTrait';
import { Campaign } from '../../../models/Campaign';
import { PlainInputElement } from '../../../../reactUtils/plugins/formable/formElements/PlainInput';
import autobind from "autobind-decorator";
import { ApplicationComponent } from '../../ApplicationComponent';
import { FlashMessageQueue } from '../../shared/FlashMessageQueue';

export class New extends MixinFormableTrait(BaseReactComponent) {
    
    state: {
        campaign: Campaign
    } = {
        campaign: new Campaign()
    }

    render(){
        return <div>
            <PlainInputElement
                model={this.state.campaign}
                registerInput={this.registerInput}
                propertyName="name"
                optional={{
                    placeholder: "campaign name"
                }}
            />
            <PlainInputElement
                model={this.state.campaign}
                propertyName="description"
                registerInput={this.registerInput}
                optional={{
                    placeholder: "description"
                }}
            />
            <button onClick={this.submit}>
                create
            </button>
        </div>
    }

    @autobind
    submit() {
        this.collectInputs()
        this.state.campaign.forGameMasterCreate().then((campaign)=>{
            if (!campaign.isValid()) {
                this.setState({campaign})
                return
            }
            ApplicationComponent.instance.flashMessageQueue.addMessage(
                "campaign created"
            )
        })
    }

}
