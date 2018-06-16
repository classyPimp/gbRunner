import { BaseReactComponent } from "../../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { ModelCollection } from '../../../../modelLayer/ModelCollection';
import { Campaign } from '../../../models/Campaign';

export class Index extends BaseReactComponent {

    state: {
        camapigns: ModelCollection<Campaign>
    } = {
        camapigns: new ModelCollection()
    }

    componentDidMount() {
        Campaign.forGameMasterIndex().then((campaigns)=>{
            this.setState({campaigns})
        })
    }

    render(){
        return <div>
            {this.state.camapigns.map((campaign)=>{
                return <div key={campaign.id}>
                    <p>
                        {campaign.name}
                    </p>
                    <p>
                        {campaign.description}
                    </p>
                </div>
            })}
        </div>
    }

}
