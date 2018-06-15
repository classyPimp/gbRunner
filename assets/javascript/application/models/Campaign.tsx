import { Property } from '../../modelLayer/annotations/Property'
import { BaseModel } from '../../modelLayer/BaseModel'
import { HasOne } from '../../modelLayer/annotations/HasOne'
import { HasMany } from '../../modelLayer/annotations/HasMany'
import { ModelCollection } from '../../modelLayer/ModelCollection'
import { RequestOptions, Route } from '../../modelLayer/annotations/ModelRoute'

import { GenericGenericLink } from './GenericGenericLink'

export class Campaign extends BaseModel {

    static className = "campaign"

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

    @HasMany("GenericGenericLink")
    linksToUsers: ModelCollection<GenericGenericLink>
    
}