import { BaseReactComponent } from "../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { User } from '../../models/User';
import { ModelCollection } from '../../../modelLayer/ModelCollection';
import { Model } from '../../../../hilfhund/js/models/Model';
import autobind from 'autobind-decorator'

export class Index extends BaseReactComponent {

    props: {
        selectable?: boolean
        onSelect?: (user: User) => any
    }

    state: {
        users: ModelCollection<User>
    } = {
        users: new ModelCollection()
    }

    componentDidMount() {
        User.index().then((users)=>{
            this.setState({users})
        })
    }

    render(){
        return <div>
            {this.state.users.map((user)=>{
                return <div key={user.id}>
                    <p>
                        {user.name}
                    </p>
                    {this.props.selectable &&
                        <button onClick={()=>{this.selectUser(user)}}>
                            select
                        </button>
                    }
                </div>
            })}
        </div>
    }

    @autobind
    selectUser(user: User) {
      this.props.onSelect(user)
    }

}
