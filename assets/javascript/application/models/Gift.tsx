import { Property } from '../../modelLayer/annotations/Property'
import { BaseModel } from '../../modelLayer/BaseModel'
import { HasOne } from '../../modelLayer/annotations/HasOne'
import { HasMany } from '../../modelLayer/annotations/HasMany'
import { ModelCollection } from '../../modelLayer/ModelCollection'
import { RequestOptions, Route } from '../../modelLayer/annotations/ModelRoute'

import { Campaign } from './Campaign'
import { Word } from './Word'

export class Gift extends BaseModel {

    static className = "gift"

    @Property
    id: number

    @Property
    updatedAt: string

    @Property
    createdAt: string

    @Property
    wordId: number
    @Property
    name: string
    @Property
    description: string
    @Property
    specificForCampaignId: number

        @HasOne("Campaign")
        specificForCampaign: Campaign
        @HasOne("Campaign")
        specificForCampaign: Campaign
        @HasOne("Word")
        word: Word

}