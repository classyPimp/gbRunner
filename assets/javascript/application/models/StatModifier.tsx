import { Property } from '../../modelLayer/annotations/Property'
import { BaseModel } from '../../modelLayer/BaseModel'
import { HasOne } from '../../modelLayer/annotations/HasOne'
import { HasMany } from '../../modelLayer/annotations/HasMany'
import { ModelCollection } from '../../modelLayer/ModelCollection'
import { RequestOptions, Route } from '../../modelLayer/annotations/ModelRoute'

import { GameCharacter } from './GameCharacter'
import { Item } from './Item'

export class StatModifier extends BaseModel {

    static className = "statModifier"

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
    gameCharacterId: number
    
    @Property
    itemId: number
    
    @Property
    isBlueprint: string
    
    @Property
    value: number
    
    @Property
    nonStandartValue: string

    @HasOne("GameCharacter")
    gameCharacter: GameCharacter
    
    @HasOne("Item")
    item: Item

    @Route("GET", {url: "/api/stat-modifier/category/form-feed"})
    static categoryFormFeedIndex: (options?: RequestOptions) => Promise<ModelCollection<StatModifier>>

    static afterCategoryFormFeedIndexRequest(options: RequestOptions) {
      this.afterIndexRequest(options)
    }

    @Route("GET", {url: "/api/stat-modifier/category/form-feed/attack-subcategory"})
    static categoryAsAttackSubcategoryFormFeedIndex: (options?: RequestOptions) => Promise<ModelCollection<StatModifier>>

    static afterCategoryAsAttackSubcategoryFormFeedIndexRequest(options: RequestOptions) {
      this.afterIndexRequest(options)
    }

    @Route("GET", {url: "/api/stat-modifier/category/form-feed/armor-subcategory"})
    static categoryAsArmorSubcategoryFormFeedIndex: (options?: RequestOptions) => Promise<ModelCollection<StatModifier>>

    static afterCategoryAsArmorSubcategoryFormFeedIndexRequest(options: RequestOptions) {
      this.afterIndexRequest(options)
    }  

    @Route("GET", {url: "/api/stat-modifier/category/form-feed/saving-throw-penalty-subcategory"})
    static categoryAsSavingThrowPenaltySubcategoryFormFeedIndex: (options?: RequestOptions) => Promise<ModelCollection<StatModifier>>

    static afterCategoryAsSavingThrowPenaltySubcategoryFormFeedIndexRequest(options: RequestOptions) {
      this.afterIndexRequest(options)
    }  




}