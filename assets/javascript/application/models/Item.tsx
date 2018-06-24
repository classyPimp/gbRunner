import { Property } from '../../modelLayer/annotations/Property'
import { BaseModel } from '../../modelLayer/BaseModel'
import { HasOne } from '../../modelLayer/annotations/HasOne'
import { HasMany } from '../../modelLayer/annotations/HasMany'
import { ModelCollection } from '../../modelLayer/ModelCollection'
import { RequestOptions, Route } from '../../modelLayer/annotations/ModelRoute';

import { ItemBlueprint } from './ItemBlueprint'
import { GameCharacter } from './GameCharacter'
import { StatModifier } from './StatModifier';
import { Promise } from 'es6-promise';
import { ReplOptions } from 'repl';

export class Item extends BaseModel {

    static className = "item"

    @Property
    id: number

    @Property
    updatedAt: string

    @Property
    createdAt: string

    @Property
    category: string
    
    @Property
    subcategory: string
    
    @Property
    name: string
    
    @Property
    description: string
    
    @Property
    ownerId: number
    
    @Property
    isEquipped: string
    
    @Property
    isInInventory: string
    
    @Property
    blueprintId: number

    @Property
    isBlueprint: boolean
    
    @Property
    campaignId: number
    
    @HasOne("GameCharacter")
    gameCharacter: GameCharacter

    @HasMany("StatModifier")
    statModifiers: ModelCollection<StatModifier>

    @HasOne("Item")
    blueprint: Item

    @Route("GET", {url: "/api/as-blueprint/for-admin"})
    static asBluePrintForAdminIndex: (options?: RequestOptions) => Promise<ModelCollection<Item>>

    static afterAsBluePrintForAdminIndexRequest(options: RequestOptions) {
        this.afterIndexRequest(options)
    }

    @Route("GET", {url: "/api/for-admin"})
    static forAdminIndex: (options?: RequestOptions) => Promise<ModelCollection<Item>>

    static afterForAdminIndexRequest(options: RequestOptions) {
        this.afterIndexRequest(options)
    }

    @Route("POST", {url: "/api/for-admin"})
    forAdminCreate: (options?: RequestOptions) => Promise<Item>

    beforeForAdminCreateRequest(options: RequestOptions) {
        this.beforeCreateRequest(options)
    }

    afterForAdminCreateRequest(options: RequestOptions) {
        this.afterCreateRequest(options)
    }

}