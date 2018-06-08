class CreateItems < ActiveRecord::Migration[5.1]
  def change
    create_table :items do |t|
      t.string :category
      t.string :sub_category
      t.string :name
      t.string :description
      t.integer :base_ac
      t.integer :dice_count
      t.integer :dice_value
      t.string :depends_on_attribute
      t.references :owner, references: "characters"
      t.boolean :is_equipped
      t.boolean :is_in_inventory
      t.references :item_blueprint, foreign_key: true
      t.references :campaign, foreign_key:true
      t.timestamps
    end
  end
end
