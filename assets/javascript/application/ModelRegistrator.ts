import {ModelRegistry} from '../modelLayer/ModelRegistry'

import { Account } from './models/Account'

import { User } from './models/User'
import { Campaign } from './models/Campaign'
import { GameCharacter } from './models/GameCharacter'
import { CharacterBlueprint } from './models/CharacterBlueprint'
import { DominionExpenditure } from './models/DominionExpenditure'
import { EffortExpenditure } from './models/EffortExpenditure'
import { GameEntity } from './models/GameEntity'
import { GameEvent } from './models/GameEvent'
import { GenericGenericLink } from './models/GenericGenericLink'
import { Gift } from './models/Gift'
import { InfluenceExpenditure } from './models/InfluenceExpenditure'
import { Item } from './models/Item'
import { ItemBlueprint } from './models/ItemBlueprint'
import { UploadedImage } from './models/UploadedImage'
import { Word } from './models/Word'


export class ModelRegistrator {
  //THIS UGLY HACK SOLVES SOME NASTY CIRCULAR DEPENDENCIES BUGS!
  static run(){
    ModelRegistry.registeredModels = {
      Account,
      User,    
      Campaign,
      GameCharacter,
      CharacterBlueprint,
      DominionExpenditure,
      EffortExpenditure,
      GameEntity,
      GameEvent,
      GenericGenericLink,
      Gift,
      InfluenceExpenditure,
      Item,
      ItemBlueprint,
      UploadedImage,
      Word              
    }
  }

}

