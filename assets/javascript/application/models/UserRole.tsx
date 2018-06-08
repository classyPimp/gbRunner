import { Property } from '../../modelLayer/annotations/Property'
import { BaseModel } from '../../modelLayer/BaseModel'
import { HasOne } from '../../modelLayer/annotations/HasOne'
import { HasMany } from '../../modelLayer/annotations/HasMany'
import { ModelCollection } from '../../modelLayer/ModelCollection'
import { RequestOptions, Route } from '../../modelLayer/annotations/ModelRoute'
import { User } from "./User"

export class UserRole extends BaseModel {

  static className = "userRole"

  @Property
  id: number

  @Property
  createdAt: string

  @Property
  updatedAt: string

  @Property
  isSpecific: boolean

  @Property
  specificToId: number

  @Property
  specificToType: number

  @Property
  name: string

  @Property
  userId: number

  @HasOne("User")
  user: User

}