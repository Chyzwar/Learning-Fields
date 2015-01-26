class CreateProviders < ActiveRecord::Migration
  def change
    create_table :providers do |t|
      t.string :providerName
      
      t.timestamps
    end
  end
end
