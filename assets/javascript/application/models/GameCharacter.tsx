import { Property } from '../../modelLayer/annotations/Property'
import { BaseModel } from '../../modelLayer/BaseModel'
import { HasOne } from '../../modelLayer/annotations/HasOne'
import { HasMany } from '../../modelLayer/annotations/HasMany'
import { ModelCollection } from '../../modelLayer/ModelCollection'
import { RequestOptions, Route } from '../../modelLayer/annotations/ModelRoute'

import { UploadedImage } from './UploadedImage'
import { CharacterBlueprint } from './CharacterBlueprint'
import { GenericGenericLink } from './GenericGenericLink'

export class GameCharacter extends BaseModel {

    static className = "character"

    @Property
    id: number

    @Property
    updatedAt: string

    @Property
    createdAt: string

    @Property
    campaignId: number
    
    @Property
    strength: number
    
    @Property
    level: number
    
    @Property
    experiencePoints: number
   
    @Property
    dexterity: number
    
    @Property
    constitution: number
   
    @Property
    wisdom: number
   
    @Property
    intelligence: number
   
    @Property
    charisma: number
   
    @Property
    hardinessBase: number
   
    @Property
    evasionBase: number
   
    @Property
    hitpointsMaximum: number
   
    @Property
    hitpointsCurrent: number
   
    @Property
    effortTotal: number
   
    @Property
    effortAvailable: number
   
    @Property
    influenceTotal: number
   
    @Property
    influenceAvailable: number
   
    @Property
    dominionTotal: number
   
    @Property
    dominionAvailable: number
   
    @Property
    currentWealth: number
   
    @Property
    dominionEarnedPerMonth: number
   
    @Property
    category: string
   
    @Property
    characterBlueprintId: number
   
    @Property
    characterSubCategory: string
   
    @Property
    name: string
   
    @Property
    description: string

    @HasMany("UploadedImage")
    uploadedImages: ModelCollection<UploadedImage>

    @HasOne("CharacterBlueprint")
    characterBlueprint: CharacterBlueprint
    
    @HasMany("GenericGenericLink")
    linksToUsers: ModelCollection<GenericGenericLink>


    @Route("GET", {url: "/api/game-character/:campaignId"})
    static forPlayerAsPrimaryCharacterShow: (options?: RequestOptions) => Promise<GameCharacter>

    static afterForPlayerAsPrimaryCharacterShowRequest(options: RequestOptions) {
      this.afterShowRequest(options)
    }
}