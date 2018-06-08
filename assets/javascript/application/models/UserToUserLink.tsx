import { Property } from '../../modelLayer/annotations/Property'
import { BaseModel } from '../../modelLayer/BaseModel'
import { HasOne } from '../../modelLayer/annotations/HasOne'
import { HasMany } from '../../modelLayer/annotations/HasMany'
import { ModelCollection } from '../../modelLayer/ModelCollection'
import { RequestOptions, Route } from '../../modelLayer/annotations/ModelRoute'
import { User } from "./User"
import { UserRole } from "./UserRole"

export class UserToUserRoleLink extends BaseModel {

  className = "userToUserRoleLink"

  @Property
  id: number

  @Property
  createdAt: string

  @Property
  updatedAt: string

  @Property
  userId: number

  @Property
  userRoleId: number

  @HasOne("User")
  user: User

  @HasOne("UserRole")
  userRole: UserRole

}