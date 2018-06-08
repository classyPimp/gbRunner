class CreateGameEvents < ActiveRecord::Migration[5.1]
  def change
    create_table :game_events do |t|
      t.string :category
      t.string :sub_category
      t.string :description
      t.references :campaign, foreign_key: true
      t.timestamps
    end
  end
end
