import { BaseReactComponent } from "../../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { ModelCollection } from '../../../../modelLayer/ModelCollection'
import { Item } from '../../../models/Item'

export class Index extends BaseReactComponent {

    state: {
      items: ModelCollection<Item>
    } = {
      items: new ModelCollection()
    }

    componentDidMount() {
      Item.forAdminIndex().then((items)=>{
        this.setState({items})
      }) 
    }

    render(){
        return <div>
          {this.state.items.map((item)=>{
            return <div>
              <p>
                name: {item.name}
              </p>
              <p>
                description: {item.description}
              </p>
              <p>     
                category: {item.category}
              </p>      
              <h3>
                stat modifiers: 
              </h3>   
              {item.statModifiers.map((statModifier)=>{
                return <div>
                  <p>
                    category: {statModifier.category}
                  </p>
                  <p>
                    sub category: {statModifier.subCategory}
                  </p>
                  <p>
                    value: {statModifier.value}
                  </p>
                </div>
              })}
            </div>
          })}
        </div>
    }


}
