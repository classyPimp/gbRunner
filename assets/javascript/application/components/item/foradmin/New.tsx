import { BaseReactComponent } from "../../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { Item } from '../../../models/Item'
import { MixinFormableTrait } from '../../../../reactUtils/plugins/formable/MixinFormableTrait'
import { StatModifier } from '../../../models/StatModifier'
import { PlainInput } from '../../formelements/PlainInput'
import { DropDownSelectServerFed } from '../../formelements/DropdownSelectServerFed'
import { PlainSelect } from '../../formelements/PlainSelect'
import autobind from 'autobind-decorator'
import { Modal } from '../../shared/Modal'
import { ItemComponents } from '../ItemComponents'

export class New extends MixinFormableTrait(BaseReactComponent) {

    state: {
      item: Item
    } = {
      item: null
    }

    constructor(...args: Array<any>) {
      super(...args)
      let item = new Item()
      item.reactKey = this.reactKeyTracker += 1
      this.state.item = item
    }

    reactKeyTracker: number = 0
    modal: Modal

    render(){
        if (!this.state.item) {
          return <div>

          </div>
        }
        return <div>
          <Modal ref={(it)=>{this.modal = it}}/>
          <button onClick={this.initFromBluePrintCreation}>
            choose from blueprint
          </button>
          <PlainInput
            model = {this.state.item}
            propertyName="name"
            registerInput={this.registerInput}
            optional={{
              placeholder: "name"
            }}
          />
          <PlainInput
            model = {this.state.item}
            propertyName={"description"}
            registerInput={this.registerInput}
            optional={{
              placeholder: "description"
            }}
          />
          <DropDownSelectServerFed
            model={this.state.item}
            propertyName={"category"}
            registerInput={this.registerInput}
            propertyToShow="category"
            propertyToSelect="category"
            preselected={this.state.item.category}
            queryingFunction={Item.categoryFormFeedIndex.bind(Item)}
            onSelect={(value)=>{this.hadleItemCategorySelect(value)}}
            onCancelSelect={()=>{this.hadleItemCategorySelect(null)}}
            optional= {{
              placeholder: "select category"
            }}
          />

          {this.dropDownSelectForItemSubcategory(this.state.item)}

          <PlainSelect
            model={this.state.item}
            propertyName="isAbility"
            registerInput={this.registerInput}
            preselected={this.state.item.isAbility}
            options={{
              "is ability": true,
              "is item": false
            }}
          />
          <p>
            stat modifiers: 
          </p>
          {this.state.item.statModifiers.map((statModifier)=>{
            console.log(statModifier.category)
            return <div key={statModifier.reactKey}>
              <DropDownSelectServerFed
                model={statModifier}
                propertyName="category"
                registerInput={this.registerInput}
                propertyToSelect={"category"}
                propertyToShow="category"
                queryingFunction={StatModifier.categoryFormFeedIndex.bind(StatModifier)}
                preselected={statModifier.category}
                onSelect={(value)=>{this.handleStatModifierCategorySelect(value, statModifier)}}
                onCancelSelect={()=>{this.handleStatModifierCategorySelect(null, statModifier)}}
                optional={{
                  placeholder: "select category"
                }}
              />
              
              {this.dropdownSelectForStatModifierSubCategory(statModifier)}
              
              <PlainInput
                model={statModifier}
                propertyName="value"
                registerInput={this.registerInput}
                parseAsInt={true}
                optional={{
                  placeholder: "value",
                }}
              />
              <button onClick={()=>{this.removeStatModifier(statModifier)}}>
                remove this stat modifier
              </button>
            </div>
          })}
          <button onClick={this.addStatModifier}>
            add stat modifier
          </button>
        </div>
    }

    @autobind
    dropDownSelectForItemSubcategory(item: Item): any {
      let queryingFunction = null
      let category = item.category
      if (category === "ARMOR") {
        queryingFunction = Item.categoryFormFeedArmorSubCategoryIndex.bind(Item)
      } else if (category === "WEAPON") {
        queryingFunction = Item.categoryFormFeedWeaponSubcategoryIndex.bind(Item)
      }
      if (queryingFunction) {
        return <DropDownSelectServerFed
          model={item}
          propertyName="subcategory"
          registerInput={this.registerInput}
          propertyToSelect="subcategory"
          propertyToShow="subcategory"
          queryingFunction={queryingFunction}
          preselected={item.subcategory}
          optional={{
            placeholder: "select subcategory"
          }}
        />
      } else {
        return null
      }
    }

    @autobind
    dropdownSelectForStatModifierSubCategory(statModifier: StatModifier): any {
      let queryingFunction = null
      let category = statModifier.category
      if (category === "ATTACK") {
        queryingFunction = StatModifier.categoryAsAttackSubcategoryFormFeedIndex.bind(StatModifier)
      } else if (category === "ARMOR") {
        queryingFunction = StatModifier.categoryAsArmorSubcategoryFormFeedIndex.bind(StatModifier)
      } else if (category === "SAVING_THROW_PENALTY") {
        queryingFunction = StatModifier.categoryAsSavingThrowPenaltySubcategoryFormFeedIndex.bind(StatModifier)
      }
      if (queryingFunction) {
        return <DropDownSelectServerFed
          model={statModifier}
          propertyName="subCategory"
          registerInput={this.registerInput}
          propertyToShow="subCategory"
          propertyToSelect="subCategory"
          queryingFunction={queryingFunction}
          preselected={statModifier.subCategory}
          optional={{
            placeholder: "select subcategory"
          }}
        />
      } else {
        return null
      }
    }

    @autobind
    removeStatModifier(statModifier: StatModifier) {
      this.state.item.statModifiers.filter((it)=>{
        return it !== statModifier
      })
      this.forceUpdate()
    }

    @autobind
    addStatModifier() {
      let statModifier = new StatModifier()
      statModifier.reactKey = this.reactKeyTracker += 1
      this.state.item.statModifiers.push(statModifier)
      this.forceUpdate()      
    }

    @autobind
    initFromBluePrintCreation() {
      this.modal.open(
        <ItemComponents.asBlueprint.forAdmin.Index
          onSelect={this.buildItemFromBluePrint}
        />
      )
    }

    @autobind
    buildItemFromBluePrint(blueprintItem: Item) {
      blueprintItem.id = null
      blueprintItem.reactKey = this.reactKeyTracker += 1
      blueprintItem.statModifiers.forEach((it)=>{
        it.id = null
        it.reactKey = this.reactKeyTracker += 1
      })
      this.modal.close()
      this.setState({item: null}, ()=>{
        this.setState({item: blueprintItem})
      })

    }

    @autobind
    hadleItemCategorySelect(value: any) {
      this.state.item.category = value
      this.forceUpdate()
    }

    @autobind
    handleStatModifierCategorySelect(value: any, statModifier: StatModifier) {
      statModifier.category = value
      this.forceUpdate()
    }

}
