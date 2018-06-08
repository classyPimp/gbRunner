import { Property } from '../../modelLayer/annotations/Property'
import { BaseModel } from '../../modelLayer/BaseModel'
import { HasOne } from '../../modelLayer/annotations/HasOne'
import { HasMany } from '../../modelLayer/annotations/HasMany'
import { ModelCollection } from '../../modelLayer/ModelCollection'
import { RequestOptions, Route } from '../../modelLayer/annotations/ModelRoute'

import { Campaign } from './Campaign'

export class ItemBlueprint extends BaseModel {

    static className = "itemBlueprint"

    @Property
    id: number

    @Property
    updatedAt: string

    @Property
    createdAt: string

    @Property
    specificForCampaignId: number
    @Property
    category: string
    @Property
    subcategory: string
    @Property
    name: string
    @Property
    description: string
    @Property
    baseAc: number
    @Property
    DiceCount: number
    @Property
    DiceValue: number
    @Property
    DependsOnAttribute: string

        @HasOne("Campaign")
        specificForCampaign: Campaign

}