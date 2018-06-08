import { Property } from '../../modelLayer/annotations/Property'
import { BaseModel } from '../../modelLayer/BaseModel'
import { HasOne } from '../../modelLayer/annotations/HasOne'
import { HasMany } from '../../modelLayer/annotations/HasMany'
import { ModelCollection } from '../../modelLayer/ModelCollection'
import { RequestOptions, Route } from '../../modelLayer/annotations/ModelRoute'

import { Campaign } from './Campaign'
import { GenericGenericLink } from './GenericGenericLink'

export class CharacterBlueprint extends BaseModel {

    static className = "characterBluePrint"

    @Property
    id: number

    @Property
    updatedAt: string

    @Property
    createdAt: string

    @Property
    specificForCampaignId: number
    
    @Property
    strength: number
    
    @Property
    level: number
    
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
    spiritBase: number
    
    @Property
    hitpointsMaximum: number
    
    @Property
    effortTotal: number
    
    @Property
    influenceTotal: number
    
    @Property
    dominionTotal: number
    
    @Property
    currentWealth: number
    
    @Property
    dominionEarnedPerMonth: number
    
    @Property
    description: string

    @HasOne("Campaign")
    specificForCampaign: Campaign

    @HasMany("GenericGenericLink")
    linksToCharacters: ModelCollection<GenericGenericLink>

}