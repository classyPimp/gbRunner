import { Property } from '../../modelLayer/annotations/Property'
import { BaseModel } from '../../modelLayer/BaseModel'
import { HasOne } from '../../modelLayer/annotations/HasOne'
import { HasMany } from '../../modelLayer/annotations/HasMany'
import { ModelCollection } from '../../modelLayer/ModelCollection'
import { RequestOptions, Route } from '../../modelLayer/annotations/ModelRoute'

import { Campaign } from './Campaign'
import { GenericGenericLink } from './GenericGenericLink'
import { Gift } from './Gift'

export class Word extends BaseModel {

    static className = "word"

    @Property
    id: number

    @Property
    updatedAt: string

    @Property
    createdAt: string

    @Property
    name: string
    @Property
    description: string
   
    @Property
    specificForCampaignId: number

    @HasOne("Campaign")
    specificForCampaign: Campaign

    @HasMany("GenericGenericLink")
    linksToCharacters: ModelCollection<GenericGenericLink>

    @HasMany("Gift")
    gifts: ModelCollection<Gift>

    @Route("GET", {url: "/api/words"})
    static index: (options?: RequestOptions) => Promise<ModelCollection<Word>>

}