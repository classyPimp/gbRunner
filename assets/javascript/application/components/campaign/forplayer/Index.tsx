import { BaseReactComponent } from "../../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { ModelCollection } from '../../../../modelLayer/ModelCollection';
import { Campaign } from '../../../models/Campaign';
import { Link } from 'react-router-dom'

export class Index extends BaseReactComponent {

    state: {
        campaigns: ModelCollection<Campaign>
    } = {
        campaigns: new ModelCollection()
    }

    componentDidMount() {
        Campaign.forPlayerIndex().then((campaigns)=>{
            console.log(campaigns)
            this.setState({campaigns})
        })
    }

    render(){
        return <div>
            {this.state.campaigns.map((campaign)=>{
                return <div key={campaign.id}>
                    <p>
                      <Link to={`/campaign/for-player/${campaign.id}`}>
                        {campaign.name}
                      </Link>
                    </p>
                    <p>
                        {campaign.description}
                    </p>
                </div>
            })}
        </div>
    }

}
