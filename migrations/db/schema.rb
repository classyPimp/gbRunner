# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20180608063047) do

  # These are extensions that must be enabled in order to support this database
  enable_extension "plpgsql"

  create_table "accounts", force: :cascade do |t|
    t.string "password"
    t.string "email"
    t.bigint "user_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["user_id"], name: "index_accounts_on_user_id"
  end

  create_table "avatars", force: :cascade do |t|
    t.string "file_name"
    t.bigint "user_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["user_id"], name: "index_avatars_on_user_id"
  end

  create_table "campaigns", force: :cascade do |t|
    t.string "name"
    t.string "description"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "character_blueprints", force: :cascade do |t|
    t.bigint "specific_for_campaign_id"
    t.integer "strength"
    t.integer "level"
    t.integer "dexterity"
    t.integer "constitution"
    t.integer "wisdom"
    t.integer "intelligence"
    t.integer "charisma"
    t.integer "hardiness_base"
    t.integer "evasion_base"
    t.integer "spirit_base"
    t.integer "hitpoints_maximum"
    t.integer "effort_total"
    t.integer "influence_total"
    t.integer "dominion_total"
    t.integer "current_wealth"
    t.integer "dominion_earned_per_month"
    t.string "description"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["specific_for_campaign_id"], name: "index_character_blueprints_on_specific_for_campaign_id"
  end

  create_table "dominion_expenditures", force: :cascade do |t|
    t.string "category"
    t.string "spent_on"
    t.integer "amount_spent"
    t.bigint "game_character_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["game_character_id"], name: "index_dominion_expenditures_on_game_character_id"
  end

  create_table "effort_expenditures", force: :cascade do |t|
    t.string "category"
    t.string "spent_on"
    t.integer "amount_spent"
    t.bigint "game_character_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["game_character_id"], name: "index_effort_expenditures_on_game_character_id"
  end

  create_table "game_characters", force: :cascade do |t|
    t.bigint "campaign_id"
    t.integer "strength"
    t.integer "level"
    t.integer "experience_points"
    t.integer "dexterity"
    t.integer "constitution"
    t.integer "wisdom"
    t.integer "intelligence"
    t.integer "charisma"
    t.integer "hardiness_base"
    t.integer "evasion_base"
    t.integer "spirit_base"
    t.integer "hitpoints_maximum"
    t.integer "hitpoints_current"
    t.integer "effort_total"
    t.integer "effort_available"
    t.integer "influence_total"
    t.integer "influence_available"
    t.integer "dominion_total"
    t.integer "dominion_available"
    t.integer "current_wealth"
    t.integer "dominion_earned_per_month"
    t.string "category"
    t.bigint "character_blueprint_id"
    t.string "character_sub_category"
    t.string "name"
    t.string "description"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["campaign_id"], name: "index_game_characters_on_campaign_id"
    t.index ["character_blueprint_id"], name: "index_game_characters_on_character_blueprint_id"
  end

  create_table "game_entities", force: :cascade do |t|
    t.string "category"
    t.string "sub_category"
    t.string "description"
    t.bigint "campaign_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["campaign_id"], name: "index_game_entities_on_campaign_id"
  end

  create_table "game_events", force: :cascade do |t|
    t.string "category"
    t.string "sub_category"
    t.string "description"
    t.bigint "campaign_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["campaign_id"], name: "index_game_events_on_campaign_id"
  end

  create_table "generic_generic_links", force: :cascade do |t|
    t.string "left_model_type"
    t.bigint "left_model_id"
    t.string "right_model_type"
    t.bigint "right_model_id"
    t.string "category"
    t.string "sub_category"
    t.string "current_status"
    t.string "description"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["left_model_type", "left_model_id"], name: "g_g_l_l_m"
    t.index ["right_model_type", "right_model_id"], name: "g_g_l_r_m"
  end

  create_table "gifts", force: :cascade do |t|
    t.bigint "word_id"
    t.string "name"
    t.string "description"
    t.bigint "specific_for_campaign_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["specific_for_campaign_id"], name: "index_gifts_on_specific_for_campaign_id"
    t.index ["word_id"], name: "index_gifts_on_word_id"
  end

  create_table "influence_expenditures", force: :cascade do |t|
    t.string "category"
    t.string "spent_on"
    t.integer "amount_spent"
    t.bigint "game_character_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["game_character_id"], name: "index_influence_expenditures_on_game_character_id"
  end

  create_table "item_blueprints", force: :cascade do |t|
    t.bigint "specific_for_campaign_id"
    t.string "category"
    t.string "subcategory"
    t.string "name"
    t.string "description"
    t.integer "base_ac"
    t.integer "dice_count"
    t.integer "dice_value"
    t.string "depends_on_attribute"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["specific_for_campaign_id"], name: "index_item_blueprints_on_specific_for_campaign_id"
  end

  create_table "items", force: :cascade do |t|
    t.string "category"
    t.string "sub_category"
    t.string "name"
    t.string "description"
    t.integer "base_ac"
    t.integer "dice_count"
    t.integer "dice_value"
    t.string "depends_on_attribute"
    t.bigint "owner_id"
    t.boolean "is_equipped"
    t.boolean "is_in_inventory"
    t.bigint "item_blueprint_id"
    t.bigint "campaign_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["campaign_id"], name: "index_items_on_campaign_id"
    t.index ["item_blueprint_id"], name: "index_items_on_item_blueprint_id"
    t.index ["owner_id"], name: "index_items_on_owner_id"
  end

  create_table "uploaded_images", force: :cascade do |t|
    t.string "owner_type"
    t.bigint "owner_id"
    t.string "file_name"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["owner_type", "owner_id"], name: "index_uploaded_images_on_owner_type_and_owner_id"
  end

  create_table "user_roles", force: :cascade do |t|
    t.string "name"
    t.boolean "is_specific"
    t.string "specific_to_type"
    t.bigint "specific_to_id"
    t.bigint "user_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["user_id"], name: "index_user_roles_on_user_id"
  end

  create_table "user_to_user_role_links", force: :cascade do |t|
    t.bigint "user_id"
    t.bigint "user_role_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["user_id"], name: "index_user_to_user_role_links_on_user_id"
    t.index ["user_role_id"], name: "index_user_to_user_role_links_on_user_role_id"
  end

  create_table "users", force: :cascade do |t|
    t.string "name"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "words", force: :cascade do |t|
    t.string "name"
    t.string "description"
    t.bigint "specific_for_campaign_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["specific_for_campaign_id"], name: "index_words_on_specific_for_campaign_id"
  end

  add_foreign_key "accounts", "users"
  add_foreign_key "avatars", "users"
  add_foreign_key "dominion_expenditures", "game_characters"
  add_foreign_key "effort_expenditures", "game_characters"
  add_foreign_key "game_characters", "campaigns"
  add_foreign_key "game_characters", "character_blueprints"
  add_foreign_key "game_entities", "campaigns"
  add_foreign_key "game_events", "campaigns"
  add_foreign_key "gifts", "words"
  add_foreign_key "influence_expenditures", "game_characters"
  add_foreign_key "items", "campaigns"
  add_foreign_key "items", "item_blueprints"
  add_foreign_key "user_to_user_role_links", "user_roles"
  add_foreign_key "user_to_user_role_links", "users"
end
