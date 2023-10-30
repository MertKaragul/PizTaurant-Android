package com.mertkaragul.piztaurant.Model.InformationModel

import com.mertkaragul.piztaurant.Enum.EInformationStatus

data class InformationModel(
    val title : String,
    val description : String,
    val status : EInformationStatus
)
