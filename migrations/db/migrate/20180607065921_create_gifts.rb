class CreateGifts < ActiveRecord::Migration[5.1]
  def change
    create_table :gifts do |t|
      t.references :word, foreign_key: true
      t.string :name
      t.string :description
      t.references :specific_for_campaign, references: :campaigns
      t.timestamps
    end
  end
end
