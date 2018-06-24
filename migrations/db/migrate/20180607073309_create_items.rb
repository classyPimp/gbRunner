class CreateItems < ActiveRecord::Migration[5.1]
  def change
    create_table :items do |t|
      t.string :category
      t.string :sub_category
      t.string :name
      t.string :description
      t.references :owner, references: "characters"
      t.boolean :is_equipped
      t.boolean :is_in_inventory
      t.references :item_blueprint, foreign_key: true
      t.references :campaign, foreign_key:true
      t.boolean :is_blueprint
      t.references :blueprint, references: :items
      t.boolean :is_ability
      t.timestamps
    end
  end
end
