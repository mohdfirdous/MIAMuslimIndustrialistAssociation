package com.nexusinfo.mia_muslimindustrialistassociation.models

import java.io.Serializable

/**
 * Created by firdous on 1/13/2018.
 */
class ServiceModel : ItemModel(), Serializable{

    override fun setItemType() {
        itemType = ItemModel.TYPE_SERVICE
    }

    var serviceId: Int? = 0
    var serviceName: String? = null

    var memberId: Int? = 0
    var memberName: String? = null
    var memberDesignation: String? = null
    var companyName: String? = null

    var serviceDescription: String? = null
    var photo: ByteArray? = null
    var isActive: Boolean? = null
    var companyId: String? = null
    var branchCoce: String? = null
}