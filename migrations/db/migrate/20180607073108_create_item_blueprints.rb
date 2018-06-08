class CreateItemBlueprints < ActiveRecord::Migration[5.1]
  def change
    create_table :item_blueprints do |t|
      t.references :specific_for_campaign, references: :campaigns
      t.string :category
      t.string :subcategory
      t.string :name
      t.string :description
      t.integer :base_ac
      t.integer :dice_count
      t.integer :dice_value
      t.string :depends_on_attribute
      t.timestamps
    end
  end
end
