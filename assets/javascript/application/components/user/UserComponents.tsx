import { BaseReactComponent } from "../../../reactUtils/BaseReactComponent"
import * as React from 'react'
import {New as SessionNew} from './session/New'
import { New as RegistrationNew } from './registration/New'
import { LoginStatus } from './LoginStatus'
import { Index as ManagementIndex } from './management/Index';
import { Main as ManagementMain } from './management/Main'

export let UserComponents = {
  session: {
    New: SessionNew
  },
  registration: {
    New: RegistrationNew
  },
  LoginStatus,
  management: {
    Main: ManagementMain,
    Index: ManagementIndex
  },
}