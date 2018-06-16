import { Property } from '../../modelLayer/annotations/Property'
import { BaseModel } from '../../modelLayer/BaseModel'
import { HasOne } from '../../modelLayer/annotations/HasOne'
import { HasMany } from '../../modelLayer/annotations/HasMany'
import { ModelCollection } from '../../modelLayer/ModelCollection';
import { RequestOptions, Route } from '../../modelLayer/annotations/ModelRoute';

import { GenericGenericLink } from './GenericGenericLink'
import { Index } from '../components/user/management/Index';

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

    @Route("POST", {url: "/api/campaign/for-game-master"})
    forGameMasterCreate: (options?: RequestOptions) => Promise<Campaign>

    beforeForGameMasterCreateRequest(options: RequestOptions) {
        this.beforeCreateRequest(options)
    }

    afterForGameMasterCreateRequest(options: RequestOptions) {
        this.afterCreateRequest(options)
    }

    @Route("GET", {url: "/api/campaign/for-game-master"})
    static forGameMasterIndex: (options?: RequestOptions) => Promise<ModelCollection<Campaign>>
    
    static afterForGameMasterIndexRequest(options: RequestOptions) {
        this.afterIndexRequest(options)
    }
}