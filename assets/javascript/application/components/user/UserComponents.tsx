import { BaseReactComponent } from "../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import {New as SessionNew} from './session/New'
import { New as RegistrationNew } from './registration/New'
import { LoginStatus } from './LoginStatus'

export let UserComponents = {
  session: {
    New: SessionNew
  },
  registration: {
    New: RegistrationNew
  },
  LoginStatus,
}