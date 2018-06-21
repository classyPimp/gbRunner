import { Property } from '../../modelLayer/annotations/Property'
import { BaseModel } from '../../modelLayer/BaseModel'
import { HasOne } from '../../modelLayer/annotations/HasOne'
import { HasMany } from '../../modelLayer/annotations/HasMany'
import { ModelCollection } from '../../modelLayer/ModelCollection'
import { RequestOptions, Route } from '../../modelLayer/annotations/ModelRoute'
import { Campaign } from "./Campaign"
import { User } from './User'
import { GameCharacter } from './GameCharacter'
import { Word } from './Word'
import { Gift } from './Gift'

export class GenericGenericLink extends BaseModel {

    static className = "genericGenericLink"

    @Property
    id: number

    @Property
    updatedAt: string

    @Property
    createdAt: string

    @Property
    leftModelId: number

    @Property
    leftModelType: string

    @Property
    rightModelId: number

    @Property
    rightModelType: string

    @Property
    category: string

    @Property
    subCategory: string

    @Property
    currentStatus: string

    @Property
    description: string

    @HasOne("Campaign")
    campaign: Campaign

    @HasOne("User")
    user: User

    @HasMany("GameCharacter")
    gameCharacters: ModelCollection<GameCharacter>

    @HasOne("Word")
    word: Word

    @HasOne("Gift")
    gift: Gift

}
