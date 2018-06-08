package models.gameentity

import orm.gameentitygeneratedrepository.GameEntityValidatorTrait

class GameEntityValidator(model: GameEntity) : GameEntityValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

}