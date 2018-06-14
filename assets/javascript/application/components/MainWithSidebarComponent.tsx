import { BaseReactComponent } from "../../reactUtils/BaseReactComponent"
import * as React from 'react'
import { SideBar } from './SideBar'

export class MainWithSidebarComponent extends BaseReactComponent {

    render(){
        return <div>
          <div className="pure-g">
            <div className="pure-u-1-8">
              <SideBar/>          
            </div>
            <div className="pure-u-7-8">

            </div>
          </div>
        </div>
    }

}
