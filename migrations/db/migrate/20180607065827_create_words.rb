class CreateWords < ActiveRecord::Migration[5.1]
  def change
    create_table :words do |t|
      t.string :name
      t.string :description
      t.references :specific_for_campaign, references: :campaigns
      t.timestamps
    end
  end
end
