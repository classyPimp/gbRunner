import { BaseReactComponent } from "../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { Word } from '../../models/Word'
import { ModelCollection } from '../../../modelLayer/ModelCollection'
import autobind from 'autobind-decorator'


export class Index extends BaseReactComponent {

    props: {
      onSelect: (word: Word)=>any
    }

    state: {
      words: ModelCollection<Word>
    } = {
      words: new ModelCollection<Word>()
    }

    componentDidMount() {
      Word.index().then((words)=>{
        this.setState(words)
      })
    }

    render(){
        return <div>
          {this.state.words.map((word)=>{
            return <div key={word.id}>
              <p>
                {word.name}
              </p>
              <p>
                {word.description}
              </p>
              {this.props.onSelect &&
                <button onClick={()=>{this.props.onSelect(word)}}>
                  select
                </button>
              }
            </div>
          })}
        </div>
    }

}
