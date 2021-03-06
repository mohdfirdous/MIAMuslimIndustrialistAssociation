package com.nexusinfo.mia_muslimindustrialistassociation.models

import java.io.Serializable

/**
 * Created by firdous on 1/13/2018.
 */
class ProductModel : ItemModel(), Serializable{

    override fun setItemType() {
        itemType = ItemModel.TYPE_PRODUCT
    }

    var productId: Int? = 0
    var productName: String? = null

    var memberId: Int? = 0
    var memberName: String? = null
    var memberDesignation: String? = null
    var companyName: String? = null

    var categoryId: Int? = 0
    var categoryName: String? = null

    var subCategoryId: Int? = 0
    var subCategoryName: String? = null

    var specification: String? = null
    var photo: ByteArray? = null
    var isActive: Boolean? = null
    var companyId: String? = null
    var branchCoce: String? = null
}