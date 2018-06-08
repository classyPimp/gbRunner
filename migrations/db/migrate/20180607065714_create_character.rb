class CreateCharacter < ActiveRecord::Migration[5.1]
  def change
    create_table :game_characters do |t|
      t.references :campaign, foreign_key: true
      t.integer :strength
      t.integer :level
      t.integer :experience_points
      t.integer :dexterity
      t.integer :constitution
      t.integer :wisdom
      t.integer :intelligence
      t.integer :charisma
      t.integer :hardiness_base
      t.integer :evasion_base
      t.integer :spirit_base
      t.integer :hitpoints_maximum
      t.integer :hitpoints_current
      t.integer :effort_total
      t.integer :effort_available
      t.integer :influence_total
      t.integer :influence_available
      t.integer :dominion_total
      t.integer :dominion_available
      t.integer :current_wealth
      t.integer :dominion_earned_per_month
      t.string :category
      t.references :character_blueprint, foreign_key: true
      t.string :character_sub_category
      t.string :name
      t.string :description
      t.timestamps
    end
  end
end
