class CreateGenericModelProperties < ActiveRecord::Migration[5.1]
  def change
    create_table :generic_model_properties do |t|
      t.references :model, polymorphic: true
      t.string :value
      t.string :name
      t.string :category
      t.string :sub_category
      t.timestamps
    end
  end
end
