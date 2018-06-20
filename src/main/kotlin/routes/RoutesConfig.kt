package routes

import controllers.HomeController
import controllers.campaign.forgamemaster.CampaignForGameMasterController
import controllers.campaign.forplayer.CampaignForPlayerController
import controllers.session.SessionController
import controllers.user.UserController
import controllers.user.UserRegistrationController
import controllers.user.management.UserManagementController
import controllers.usertocampaigninvite.UserToCampaignInviteController
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
                delete("") {
                    SessionController(it).delete()
                }
            }

            namespace("/user") {
                get("") {
                    UserController(it).index()
                }
                namespace("/management") {
                    get("") {
                        UserManagementController(it).index()
                    }
                }
                namespace("/registration") {
                    post("") {
                        UserRegistrationController(it).create()
                    }
                }
            }

            namespace("/campaign") {
                namespace("/for-game-master") {
                    get("") {
                        CampaignForGameMasterController(it).index()
                    }
                    post("") {
                        CampaignForGameMasterController(it).create()
                    }
                    get("/:campaignId") {
                        CampaignForGameMasterController(it).show()
                    }
                    get("/:campaignId/edit") {
                        CampaignForGameMasterController(it).edit()
                    }
                    put("/:campaignId") {
                        CampaignForGameMasterController(it).update()
                    }
                }
                namespace("/for-player") {
                    get("") {
                        CampaignForPlayerController(it).index()
                    }
                    get("/:campaignId") {
                        CampaignForPlayerController(it).show()
                    }
                }
            }

            namespace("/user-to-campaign-invite") {
                post("/:campaignId/create-invite") {
                    UserToCampaignInviteController(it).create()
                }
                get("/joinCampaign/:invitationToken") {
                    UserToCampaignInviteController(it).acceptInvite()
                }
                get("/:invitationToken") {
                    UserToCampaignInviteController(it).show()
                }
            }

        }
    }

}

