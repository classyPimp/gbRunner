import { Index as AsBlueprintForAdminIndex } from './asblueprint/foradmin/Index'
import { Index as ForAdminIndex } from './foradmin/Index'
import { New as ForAdminNew } from './foradmin/New'
import { Main as ForAdminMain } from './foradmin/Main'

export let ItemComponents = {

    asBlueprint: {
        forAdmin: {
            Index: AsBlueprintForAdminIndex
        }
    },
    forAdmin: {
        New: ForAdminNew,
        Index: ForAdminIndex,
        Main: ForAdminMain
    }

}