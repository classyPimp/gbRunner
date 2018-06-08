class CreateImages < ActiveRecord::Migration[5.1]
  def change
    create_table :uploaded_images do |t|
      t.references :owner, polymorphic: true
      t.string :file_name
      t.timestamps
    end
  end
end
