import { BaseReactComponent } from "../../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { ModelCollection } from '../../../../modelLayer/ModelCollection';
import { User } from "../../../models/User";

export class Index extends BaseReactComponent {

    state: {
        users: ModelCollection<User>
    } = {
        users: new ModelCollection()
    }


    componentDidMount() {
        User.managementIndex().then((users)=>{
            this.setState({users})
        })
    }

    render(){
        return <div>
            {this.state.users.map((it)=>{
                console.log(it);
                return <div key={it.id}>
                    <p>
                        {it.name}
                    </p>
                    <p>
                        {it.account.email}
                    </p>
                </div>
            })}
        </div>
    }

}
