import { BaseReactComponent } from "../../../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { GameCharacter } from '../../../../models/GameCharacter'
import { Link } from 'react-router-dom'

export class Show extends BaseReactComponent {

    state: {
      gameCharacter: GameCharacter
      noCharacterCreated: boolean
    } = {
      gameCharacter: null,
      noCharacterCreated: false
    }

    componentDidMount() {
        GameCharacter.forPlayerAsPrimaryCharacterShow({wilds: {campaignId: this.props.match.params.campaignId}}).then((gameCharacter)=>{
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
          {this.state.noCharacterCreated && 
            <div>
              <button>
                create character
              </button>
            </div>
          }
          {gameCharacter &&
            <div>
              <p>
                {gameCharacter.name}
              </p>
            </div>
          }
        </div>
    }

}
