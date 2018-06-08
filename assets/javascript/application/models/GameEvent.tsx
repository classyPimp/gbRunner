import { Property } from '../../modelLayer/annotations/Property'
import { BaseModel } from '../../modelLayer/BaseModel'
import { HasOne } from '../../modelLayer/annotations/HasOne'
import { HasMany } from '../../modelLayer/annotations/HasMany'
import { ModelCollection } from '../../modelLayer/ModelCollection'
import { RequestOptions, Route } from '../../modelLayer/annotations/ModelRoute'

import { Campaign } from './Campaign'
import { UploadedImage } from './UploadedImage'

export class GameEvent extends BaseModel {

    static className = "gameEvent"

    @Property
    id: number

    @Property
    updatedAt: string

    @Property
    createdAt: string

    @Property
    category: string
    @Property
    subCategory: string
    @Property
    description: string
    @Property
    campaignId: number

        @HasOne("Campaign")
        campaign: Campaign
        @HasMany("UploadedImage")
        uploadedImages: ModelCollection<UploadedImage>

}