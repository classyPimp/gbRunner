import { New as ForGameMasterNew } from "./forgamemaster/New"
import { Index as ForGameMasterIndex } from './forgamemaster/Index'
import { Main as ForGameMasterMain } from './forgamemaster/Main';
import { Show as ForGameMasterShow } from './forgamemaster/Show'
import { Edit as ForGameMasterEdit } from './forgamemaster/Edit'
import { Index as ForPlayerIndex } from './forplayer/Index'
import { Show as ForPlayerShow } from './forplayer/Show'
import { Main as ForPlayerMain } from './forplayer/Main'

export let CampaignComponents = {
    forGameMaster: {
        New: ForGameMasterNew,
        Index: ForGameMasterIndex,
        Main: ForGameMasterMain,
        Show: ForGameMasterShow,
        Edit: ForGameMasterEdit,
    },
    forPlayer: {
      Index: ForPlayerIndex,
      Show: ForPlayerShow,
      Main: ForPlayerMain,
    }
}