package models.uploadedimage

import orm.uploadedimagegeneratedrepository.UploadedImageValidatorTrait

class UploadedImageValidator(model: UploadedImage) : UploadedImageValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

}