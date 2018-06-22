import { BaseReactComponent } from "../../../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { PlainInputElement } from '../../../../../reactUtils/plugins/formable/formElements/PlainInput';
import { MixinFormableTrait } from '../../../../../reactUtils/plugins/formable/MixinFormableTrait';
import { GameCharacter } from '../../../../models/GameCharacter';
import autobind from 'autobind-decorator'
import { GenericGenericLink } from '../../../../models/GenericGenericLink'
import { Modal } from '../../../shared/Modal'
import { WordComponents } from '../../../word/WordComponents'
import { Word } from '../../../../models/Word'
import { Gift } from '../../../../models/Gift'
import { GiftComponents } from '../../../gift/GiftComponents'
import { ModelCollection } from '../../../../../modelLayer/ModelCollection'
import { ErrorsShow } from '../../../shared/ErrorsShow';

export class New extends MixinFormableTrait(BaseReactComponent) {

    props: {
      campaignId?: number
    }

    state: {
      gameCharacter: GameCharacter
      wordPointsLeft: number
      giftPointsLeft: number
    } = {
      gameCharacter: new GameCharacter(),
      wordPointsLeft: 3,
      giftPointsLeft: 6
    }

    modal: Modal

    render(){
        return <div>
            <ErrorsShow
              errors={this.state.gameCharacter.getErrorsFor("general")}
            />
            <Modal ref={(it)=>{this.modal = it}}/>
            <PlainInputElement
              model={this.state.gameCharacter}
              propertyName="name"
              registerInput={this.registerInput}
              optional={{
                placeholder: "name"
              }}
            />
            <PlainInputElement
              model={this.state.gameCharacter}
              propertyName="description"
              registerInput={this.registerInput}
              optional={{
                placeholder: "description"
              }}
            />
            <h3>
              attributes:
            </h3>
            <PlainInputElement
              model={this.state.gameCharacter}
              propertyName="strength"
              registerInput={this.registerInput}
              parseAsInt={true}
              optional={{
                placeholder: "strength"
              }}
            />
            <PlainInputElement
              model={this.state.gameCharacter}
              propertyName="dexterity"
              registerInput={this.registerInput}
              parseAsInt={true}
              optional={{
                placeholder: "dexterity"
              }}
            />
            <PlainInputElement
              model={this.state.gameCharacter}
              propertyName="constitution"
              registerInput={this.registerInput}
              parseAsInt={true}
              optional={{
                placeholder: "constitution"
              }}
            />
            <PlainInputElement
              model={this.state.gameCharacter}
              propertyName="wisdom"
              registerInput={this.registerInput}
              parseAsInt={true}
              optional={{
                placeholder: "wisdom"
              }}
            />
            <PlainInputElement
              model={this.state.gameCharacter}
              propertyName="intelligence"
              registerInput={this.registerInput}
              parseAsInt={true}
              optional={{
                placeholder: "intelligence"
              }}
            />
            <PlainInputElement
              model={this.state.gameCharacter}
              propertyName="charisma"
              registerInput={this.registerInput}
              parseAsInt={true}
              optional={{
                placeholder: "charisma"
              }}
            />
            <h3>
              words and gifts (wordsToChoose {this.state.wordPointsLeft}, point to spend on gifts: {this.state.giftPointsLeft}):
            </h3>
            <div>
              {this.state.gameCharacter.linksToWords.map((linkToWord)=>{
                return <div key={linkToWord.word.id}>
                  <p>
                    {linkToWord.word.name}
                  </p>
                  {this.linksToGiftsWithWordId(linkToWord.word.id).map((linkToGift)=>{
                    return <div key={linkToGift.gift.id}>
                      <p>
                        {linkToGift.gift.name}
                      </p>
                      <p>
                        {linkToGift.gift.description}
                      </p>
                      <button onClick={()=>{this.removeLinkToGift(linkToGift)}}>
                        remove this gift
                      </button>
                    </div>
                  })}
                  <button onClick={()=>{this.initGiftForWordAddition(linkToWord.word)}}>
                    add gift of this word
                  </button>
                  <button onClick={()=>{this.removeLinkToWord(linkToWord)}}>
                    remove word
                  </button>
                </div>
              })}
              {this.state.wordPointsLeft > 0 &&
                <button onClick={this.initWordAddition}>
                  add word
                </button>
              }
            </div>
            <div>
              <h3>

              </h3>
            </div>
            <button onClick={this.submit}>
               submit
            </button>
        </div>
    }

    @autobind
    linksToGiftsWithWordId(id: number): ModelCollection<GenericGenericLink> {
      let links = new ModelCollection<GenericGenericLink>()
      this.state.gameCharacter.linksToGifts.forEach((it)=>{
        if (it.gift.wordId === id) {
          links.push(it)
        }
      })
      return links
    }

    @autobind
    submit() {
      this.collectInputs()
      this.state.gameCharacter.forPlayerPrimaryCharacterOfCampaignCreate({wilds: {campaignId: this.props.campaignId.toString()}})
      .then((gameCharacter)=>{
        if (!gameCharacter.isValid()) {
          console.log(gameCharacter.linksToGifts)
          this.setState({gameCharacter})
        } else {
          alert("success")
        }
      })
    }

    @autobind
    removeLinkToWord(linkToWord: GenericGenericLink) {
      this.state.gameCharacter.linksToWords.filter((it)=>{
        return it !== linkToWord
      })
      let wordPointsLeft = this.state.wordPointsLeft += 1
      this.linksToGiftsWithWordId(linkToWord.word.id).forEach((it)=>{
        if (it.gift.category == "LESSER_GIFT") {
          this.state.giftPointsLeft += 1
        } else {
          this.state.giftPointsLeft += 2
        }
        this.state.gameCharacter.linksToGifts.filter((link)=>{
          return link !== it
        })
      })
      this.setState({wordPointsLeft})
    }

    @autobind
    initWordAddition() {
      this.modal.open(
        <WordComponents.Index 
          onSelect={this.onWordSelect}
        />
      )
    }

    @autobind
    onWordSelect(word: Word) {
      let link = new GenericGenericLink()
      link.word = word
      this.state.gameCharacter.linksToWords.push(link)
      this.state.wordPointsLeft = this.state.wordPointsLeft -= 1
      this.modal.close()
      this.forceUpdate()
    }

    @autobind
    initGiftForWordAddition(word: Word) {
      this.modal.open(
        <GiftComponents.ofWord.Index
          wordId={word.id}
          onSelect={(gift: Gift)=>{this.addGift(gift)}}
        />
      )
    }

    @autobind
    removeLinkToGift(linkToGift: GenericGenericLink) {
      this.state.gameCharacter.linksToGifts.filter((it)=>{
        return it !== linkToGift
      })
      if (linkToGift.gift.category == "LESSER_GIFT") {
        this.state.giftPointsLeft += 1
      } else {
        this.state.giftPointsLeft += 2
      }
      this.forceUpdate()
    }

    @autobind
    addGift(gift: Gift) {
      let link = new GenericGenericLink()
      link.gift = gift
      this.state.gameCharacter.linksToGifts.push(link)
      if (gift.category == "LESSER_GIFT") {
        this.state.giftPointsLeft -= 1
      } else {
        this.state.giftPointsLeft -= 2
      }
      this.modal.close()
      this.forceUpdate()
    }

}
