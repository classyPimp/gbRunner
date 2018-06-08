class CreateCharacterBlueprints < ActiveRecord::Migration[5.1]
  def change
    create_table :character_blueprints do |t|
      t.references :specific_for_campaign, index: true, references: :campaigns
      t.integer :strength
      t.integer :level
      t.integer :dexterity
      t.integer :constitution
      t.integer :wisdom
      t.integer :intelligence
      t.integer :charisma
      t.integer :hardiness_base
      t.integer :evasion_base
      t.integer :spirit_base
      t.integer :hitpoints_maximum
      t.integer :effort_total
      t.integer :influence_total
      t.integer :dominion_total
      t.integer :current_wealth
      t.integer :dominion_earned_per_month
      t.string :description
      t.timestamps
    end
  end
end
