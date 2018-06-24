import { BaseReactComponent } from "../../../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { ModelCollection } from '../../../../../modelLayer/ModelCollection';
import { Item } from '../../../../models/Item';

export class Index extends BaseReactComponent {

    state: {
        items: ModelCollection<Item>
    } = {
        items: new ModelCollection<Item>()
    }

    componentDidMount() {
        Item.asBluePrintForAdminIndex().then((items)=>{
            this.setState({items})
        })
    }

    render(){
        return <div>
            {this.state.items.map((item)=>{
                return <div>
                    <p>
                        {item.name}
                    </p>
                    <p>
                        {item.description}
                    </p>
                    <p>
                        {item.category}
                    </p>
                    <p>
                        {item.subcategory}
                    </p>
                    <p>
                        stat modifiers:
                    </p>
                    {item.statModifiers.map((statModifier)=>{
                        return <div>
                            <p>
                                {statModifier.category}
                            </p>
                            <p>
                                {statModifier.subCategory}
                            </p>
                            <p>
                                {statModifier.value}
                            </p>
                        </div>
                    })}
                </div>
            })}
        </div>
    }

}
