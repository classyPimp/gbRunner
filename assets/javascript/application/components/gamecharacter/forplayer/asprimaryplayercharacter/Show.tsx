import { BaseReactComponent } from "../../../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { GameCharacter } from '../../../../models/GameCharacter'
import { Link } from 'react-router-dom'
import autobind from 'autobind-decorator'
import { Modal } from '../../../shared/Modal'
import { GameCharacterComponents } from '../../GameCharacterComponents'

export class Show extends BaseReactComponent {

    state: {
      gameCharacter: GameCharacter
      noCharacterCreated: boolean
    } = {
      gameCharacter: null,
      noCharacterCreated: false
    }

    modal: Modal

    componentDidMount() {
        GameCharacter.forPlayerPrimaryCharacterOfCampaignShow({wilds: {campaignId: this.props.match.params.campaignId}}).then((gameCharacter)=>{
          gameCharacter.validate()
          console.log(gameCharacter.errors)
          console.log(gameCharacter.containsSpecificError("general", "NO_CHARACTER_CREATED"))
          if (gameCharacter.containsSpecificError("general", "NO_CHARACTER_CREATED")) {
            this.setState({noCharacterCreated: true})
            return
          }
          this.setState({gameCharacter})
        })
    }

    render(){
        let gameCharacter = this.state.gameCharacter
        return <div>
          <Modal ref={(it)=>{this.modal = it}}/>
          {this.state.noCharacterCreated && 
            <div>
              <button onClick={this.initCreateCharacter}>
                create character
              </button>
            </div>
          }
          {gameCharacter &&
            <div>
              <p>
                name: {gameCharacter.name}
              </p>
              <p>
                level: {gameCharacter.level}
              </p>
              <p> 
                biography: {gameCharacter.description}
              </p>
              <p>
                strength: {gameCharacter.strength}
              </p>
              <p>
                dexterity: {gameCharacter.dexterity}
              </p>
              <p>
                constitution: {gameCharacter.constitution}
              </p>
              <p>
                wisdom: {gameCharacter.wisdom}
              </p>
              <p>
                intelligence: {gameCharacter.intelligence}
              </p>
              <p>
                charisma: {gameCharacter.charisma}
              </p>
            </div>
          }
        </div>
    }

    @autobind
    initCreateCharacter() {
      this.modal.open(
        <GameCharacterComponents.forPlayer.asprimaryplayercharacter.New
          campaignId={this.props.match.params.campaignId}
        />
      )
    }

}
