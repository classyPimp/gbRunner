class CreateUserToCampaignInvites < ActiveRecord::Migration[5.1]
  def change
    create_table :user_to_campaign_invites do |t|
      t.references :campaign, foreign_key: true
      t.references :user_that_is_invited, index: true, references: :user
      t.references :user_that_invites, index: true, references: :user
      t.string :invitation_token
      t.timestamp :is_accepted
      t.timestamp :is_rejected
      t.timestamps
    end
  end
end
