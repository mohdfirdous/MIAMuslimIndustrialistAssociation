package com.nexusinfo.mia_muslimindustrialistassociation.models

import java.io.Serializable
import java.util.*

/**
 * Created by firdous on 1/13/2018.
 */
class MemberModel : Serializable {
    var memberId: Int? = 0
    var name: String? = null
    var mobile: String? = null
    var email: String? = null
    var residentialAddress: String? = null
    var officeAddress: String? = null

    var productCount: Int? = 0
    var serviceCount: Int? = 0
    var ratings: Float? = 0f

    var designationId: Int? = 0
    var designation: String? = null

    var departmentId: Int? = 0
    var departmentName: String? = null

    var companyName: String? = null

    var categoryId: Int? = 0
    var categoryName: String? = null

    var contactPersonName: String? = null
    var contactPersonMobile: String? = null

    var photo: ByteArray? = null
    var gender: String? = null
    var manufacture: String? = null
    var mSMELarge: String? = null
    var service: String? = null
    var sSMELarge: String? = null
    var professionKnowledge: String? = null
    var association: String? = null
    var others: String? = null
    var noOfManagers: Int? = 0
    var noOfEmployees: Int? = 0
    var otherInformation: String? = null
    var idCardNo: String? = null
    var membershipDate: Date? = null
    var yearOfEstablishment: String? = null
    var membershipNo: String? = null
    var membershipTypeID: Int? = 0
    var membershipType: String? = null
    var registrationNo: String? = null
    var profMembershipNo: String? = null
}