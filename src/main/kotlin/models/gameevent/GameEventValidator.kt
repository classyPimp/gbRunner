package models.gameevent

import orm.gameeventgeneratedrepository.GameEventValidatorTrait

class GameEventValidator(model: GameEvent) : GameEventValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

}