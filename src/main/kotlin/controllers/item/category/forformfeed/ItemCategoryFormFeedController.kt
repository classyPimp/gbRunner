package controllers.item.category.forformfeed

import controllers.ApplicationControllerBase
import models.item.Item
import models.item.tojsonserializers.category.forformfeed.ItemCategoryForFormFeedIndexToJsonSerializer
import router.src.ServletRequestContext

class ItemCategoryFormFeedController(context: ServletRequestContext) : ApplicationControllerBase(context) {

    fun indexCategories() {
        val dummyForTheSakeOfPassingToSelectFormItemsWithCategories = Item.Categories.values()
                .map { it.toString() }
                .mapTo(mutableListOf()) { Item().also { item -> item.category = it} }

        renderJson(
                ItemCategoryForFormFeedIndexToJsonSerializer.onSuccess(dummyForTheSakeOfPassingToSelectFormItemsWithCategories)
        )
    }

    fun indexArmorSubCategories() {
        val dummyForTheSakeOfPassingToFormFeedItemsWithArmorSubcategories = Item.ArmorSubCategories.values()
                .map { it.toString() }
                .mapTo(mutableListOf()) { Item().also { item -> item.subcategory = it } }

        renderJson(
                ItemCategoryForFormFeedIndexToJsonSerializer.onSuccess(dummyForTheSakeOfPassingToFormFeedItemsWithArmorSubcategories)
        )
    }

    fun indexWeaponSubCategories() {
        val dummyForTheSakeOfPassingToFormFeedItemsWithWeaponCategories = Item.WeaponSubCategories.values()
                .map { it.toString() }
                .mapTo(mutableListOf()) { Item().also { item -> item.subcategory = it } }

        renderJson(
                ItemCategoryForFormFeedIndexToJsonSerializer.onSuccess(dummyForTheSakeOfPassingToFormFeedItemsWithWeaponCategories)
        )
    }

}