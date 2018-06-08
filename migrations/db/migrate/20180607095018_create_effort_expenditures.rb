class CreateEffortExpenditures < ActiveRecord::Migration[5.1]
  def change
    create_table :effort_expenditures do |t|
      t.string :category
      t.string :spent_on
      t.integer :amount_spent
      t.references :game_character, foreign_key: true
      t.timestamps
    end
  end
end
