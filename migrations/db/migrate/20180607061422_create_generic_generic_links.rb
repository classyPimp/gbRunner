class CreateGenericGenericLinks < ActiveRecord::Migration[5.1]
  def change
    create_table :generic_generic_links do |t|
      t.references :left_model, polymorphic: true, index: {name: "g_g_l_l_m"}
      t.references :right_model, polymorphic: true, index: {name: "g_g_l_r_m"}
      t.string :category
      t.string :sub_category
      t.string :current_status
      t.string :description
      t.timestamps
    end
  end
end
