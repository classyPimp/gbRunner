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

}