class AddCategoryToGifts < ActiveRecord::Migration[5.1]
  def change
    add_column :gifts, :category, :string
  end
end
