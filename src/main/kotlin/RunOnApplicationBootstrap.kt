
import orm.dependencymanagement.OrmDependenciesManager
import router.src.Router
import dependencymanagement.MicroFrameworkDependencyManager
import hilfhund.routes.HilfHundRoutesAdder
import models.user.services.SuperUserManager
import models.userrole.PredefinedUserRoleManager
import models.word.tojsonserializers.PredefinedWordManager


/**
 * Created by Муса on 01.11.2017.
 */
class RunOnApplicationBootstrap: Runnable {

    override fun run() {
        initializeApplicationComponent()
        injectRequired()
        drawRoutes()
        ensurePredefinedRolesArePersistedInDb()
        ensureSuperUserIsPersistedInDb()
        ensurePredefinedWordsAndGiftsArePersisted()
    }

    private fun initializeApplicationComponent() {
        App.component = daggerComponents.DaggerApplicationComponent.builder().build()
    }

    private fun injectRequired() {
        OrmDependenciesManager.provider = OrmDependenciesProvider
        MicroFrameworkDependencyManager.provider = MicroframeworkDependenciesProvider
    }

    private fun drawRoutes() {
        routes.RoutesConfig(Router).run()
        HilfHundRoutesAdder.run()
    }

    private fun ensurePredefinedRolesArePersistedInDb() {
        PredefinedUserRoleManager.ensurePredefinedRolesPersistedInDbOtherwiseCreateMissing()
    }

    private fun ensureSuperUserIsPersistedInDb() {
        SuperUserManager.ensureSuperUserExistsInDatabaseOtherwiseCreateIt()
    }

    private fun ensurePredefinedWordsAndGiftsArePersisted() {
        PredefinedWordManager.ensurePredefinedWordsAndGiftsArePersisted()
    }


}