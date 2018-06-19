import { Property } from '../../modelLayer/annotations/Property'
import { BaseModel } from '../../modelLayer/BaseModel'
import { HasOne } from '../../modelLayer/annotations/HasOne'
import { HasMany } from '../../modelLayer/annotations/HasMany'
import { ModelCollection } from '../../modelLayer/ModelCollection'
import { RequestOptions, Route } from '../../modelLayer/annotations/ModelRoute'

import { User } from './User'
import { Campaign } from './Campaign';

export class UserToCampaignInvite extends BaseModel {

    static className = "userToCampaignInvite"

    @Property
    id: number

    @Property
    updatedAt: string

    @Property
    createdAt: string

    @Property
    campaignId: number
    
    @Property
    userThatIsInvitedId: number
    
    @Property
    userThatInvitesId: number
    
    @Property
    invitationToken: string
    
    @Property
    isAccepted: string
    
    @Property
    isRejected: string

    @HasOne("User")
    userToInvite: User
    
    @HasOne("User")
    userThatInvites: User

    @HasOne("Campaign")
    campaign: Campaign

}