package routes

import controllers.HomeController
import controllers.session.SessionController
import controllers.user.UserRegistrationController
import router.RoutesDrawer
import router.src.Router
import servletUtils.SimpleFileServer
import servletUtils.ToDefaultServletForwarder

/**
 * Created by Муса on 01.11.2017.
 */
class RoutesConfig(override val router: Router): RoutesDrawer(router) {

    override fun run() {
        get("/favicon.ico") {
            ToDefaultServletForwarder().forward(it)
        }

        get("/public/*") {
            SimpleFileServer(
                    App.component.publicFolderConfig().pathToPublicFolderParentDir
            ).serveFile(it)
        }

        get("/uploads/*") {
            SimpleFileServer(
                    ""
            ).serveFile(it)
        }

        get("/*") {
            HomeController(it).get()
        }

        namespace("/api") {
            namespace("/session") {
                post("") {
                    SessionController(it).create()
                }
            }

            namespace("/user") {
                namespace("/registration") {
                    post("") {
                        UserRegistrationController(it).create()
                    }
                }
            }

        }
    }

}

