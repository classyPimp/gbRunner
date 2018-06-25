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

    @Property
    isAbility: boolean
    
    @HasOne("GameCharacter")
    gameCharacter: GameCharacter

    @HasMany("StatModifier")
    statModifiers: ModelCollection<StatModifier>

    @HasOne("Item")
    blueprint: Item

    @Route("GET", {url: "/api/item/as-blueprint/for-admin"})
    static asBluePrintForAdminIndex: (options?: RequestOptions) => Promise<ModelCollection<Item>>

    static afterAsBluePrintForAdminIndexRequest(options: RequestOptions) {
        this.afterIndexRequest(options)
    }

    @Route("GET", {url: "/api/item/for-admin"})
    static forAdminIndex: (options?: RequestOptions) => Promise<ModelCollection<Item>>

    static afterForAdminIndexRequest(options: RequestOptions) {
        this.afterIndexRequest(options)
    }

    @Route("POST", {url: "/api/item/for-admin"})
    forAdminCreate: (options?: RequestOptions) => Promise<Item>

    beforeForAdminCreateRequest(options: RequestOptions) {
        this.beforeCreateRequest(options)
    }

    afterForAdminCreateRequest(options: RequestOptions) {
        this.afterCreateRequest(options)
    }

    @Route("GET", {url: "/api/item/category/form-feed"})
    static categoryFormFeedIndex: (options?: RequestOptions) => Promise<ModelCollection<Item>>

    static afterCategoryFormFeedIndexRequest(options: RequestOptions) {
      console.log("afterIndexRequest")
      this.afterIndexRequest(options)
    }

    @Route("GET", {url: "/api/item/category/form-feed/armor-subcategory"})
    static categoryFormFeedArmorSubCategoryIndex: (options?: RequestOptions) => Promise<ModelCollection<Item>>

    static afterCategoryFormFeedArmorSubCategoryIndexRequest(options: RequestOptions) {
      this.afterIndexRequest(options)
    }

    @Route("GET", {url: "/api/item/category/form-feed/weapon-subcategory"})
    static categoryFormFeedWeaponSubcategoryIndex: (options?: RequestOptions) => Promise<ModelCollection<Item>>

    static afterCategoryFormFeedWeaponSubcategoryIndexRequest(options: RequestOptions) {
      this.afterIndexRequest(options)
    }

}