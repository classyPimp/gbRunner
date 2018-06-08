import { Property } from '../../modelLayer/annotations/Property'
import { BaseModel } from '../../modelLayer/BaseModel'
import { HasOne } from '../../modelLayer/annotations/HasOne'
import { HasMany } from '../../modelLayer/annotations/HasMany'
import { ModelCollection } from '../../modelLayer/ModelCollection'
import { RequestOptions, Route } from '../../modelLayer/annotations/ModelRoute'

import { ItemBlueprint } from './ItemBlueprint'
import { GameCharacter } from './GameCharacter'

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
    baseAc: number
    @Property
    diceCount: number
    @Property
    diceValue: number
    @Property
    dependsOnAttribute: string
    @Property
    ownerId: number
    @Property
    isEquipped: string
    @Property
    isInInventory: string
    @Property
    itemBlueprintId: number
    @Property
    campaignId: number

    @HasOne("ItemBlueprint")
    blueprint: ItemBlueprint
    @HasOne("GameCharacter")
    gameCharacter: GameCharacter

}