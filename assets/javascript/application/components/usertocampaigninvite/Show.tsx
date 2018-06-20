import { BaseReactComponent } from "../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { UserToCampaignInvite } from '../../models/UserToCampaignInvite'
import autobind from 'autobind-decorator'

export class Show extends BaseReactComponent {

    state: {
      userToCampaignInvite: UserToCampaignInvite
    } = {
      userToCampaignInvite: null
    }

    componentDidMount() {
      let invitationToken = this.props.match.params.invitationToken as string
      UserToCampaignInvite.show({wilds: {invitationToken}}).then((userToCampaignInvite) =>{
        this.setState({userToCampaignInvite})
      })
    }

    render(){
        if (!this.state.userToCampaignInvite) {
          return <div>
            ...Loading
          </div>
        }
        if (this.state.userToCampaignInvite.isAccepted) {
          return <div>
            you have already accepted invite
          </div>
        }
        return <div>
          <p>
            you've been invited to campaign: TODO: link to CampaignComponents.Show
          </p>
          <button onClick={this.acceptInvitation}>
            accept invitation
          </button>
        </div>
    }

    @autobind
    acceptInvitation() {
      this.state.userToCampaignInvite.joinCampaign().then((invitationToken)=>{
        if (!invitationToken.isValid()) {
          alert("error")
          return
        }
        alert("invite accepted")
        this.props.history.replace("/")
      })
    }

}
