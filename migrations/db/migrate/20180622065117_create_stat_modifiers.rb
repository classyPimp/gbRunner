class CreateStatModifiers < ActiveRecord::Migration[5.1]
  def change
    create_table :stat_modifiers do |t|
      t.string :category
      t.string :sub_category
      t.references :game_character, foreign_key: true
      t.references :item, foreign_key: true
      t.boolean :is_blueprint
      t.integer :value
      t.string :non_standard_value
      t.timestamps
    end
  end
end
