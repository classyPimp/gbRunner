import { BaseReactComponent } from "../../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { Gift } from '../../../models/Gift'
import { ModelCollection } from '../../../../modelLayer/ModelCollection'

export class Index extends BaseReactComponent {

    props: {
      wordId?: number
      onSelect?: (gift: Gift)=>any
    }

    state: {
      gifts: ModelCollection<Gift>
    } = {
      gifts: new ModelCollection()
    }

    componentDidMount() {
      Gift.ofWordIndex({wilds: {wordId: this.props.wordId.toString()}}).then((gifts)=>{
        this.setState({gifts})
      })
    }

    render(){
        return <div>
          {this.state.gifts.map((gift)=>{
            return <div key={gift.id}>
              <p>
                {gift.name}
              </p>
              <p>
                {gift.description}
              </p>
              {this.props.onSelect &&
                <button onClick={()=>{this.props.onSelect(gift)}}>
                  select
                </button>
              }
            </div>
          })}
        </div>
    }

}
