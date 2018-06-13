import { BaseReactComponent } from "../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { SideBar } from './SideBar'

export class MainWithSidebarComponent extends BaseReactComponent {

    render(){
        return <div>
          <SideBar/>
          
        </div>
    }

}
