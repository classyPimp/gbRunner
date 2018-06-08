import { Property } from '../../modelLayer/annotations/Property'
import { BaseModel } from '../../modelLayer/BaseModel'
import { HasOne } from '../../modelLayer/annotations/HasOne'
import { HasMany } from '../../modelLayer/annotations/HasMany'
import { ModelCollection } from '../../modelLayer/ModelCollection'
import { RequestOptions, Route } from '../../modelLayer/annotations/ModelRoute'

import { GameCharacter } from './GameCharacter'

export class InfluenceExpenditure extends BaseModel {

    static className = "influenceExpenditure"

    @Property
    id: number

    @Property
    updatedAt: string

    @Property
    createdAt: string

    @Property
    category: string
    @Property
    spentOn: string
    @Property
    amountSpent: number
    @Property
    gameCharacterId: number

        @HasOne("GameCharacter")
        gameCharacter: GameCharacter

}