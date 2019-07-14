package com.example.basearchitecture.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Address
 */
class Address {

    /**
     * Zip code value
     */
    @SerializedName("codigoPostal")
    @Expose
    private var zipCode: String? = null
    /**
     * Colony object
     */
    @SerializedName("colonia")
    @Expose
    private var colony: Colony? = null
    /**
     * Street value
     */
    @SerializedName("calle")
    @Expose
    private var street: String? = null
    /**
     * Interior number value
     */
    @SerializedName("numeroInterior")
    @Expose
    private var interiorNumber: String? = null
    /**
     * Exterior number value
     */
    @SerializedName("numeroExterior")
    @Expose
    private var exteriorNumber: String? = null

    /**
     * Method to get zip code
     *
     * @return zip code
     */
    fun getZipCode(): String? {
        return zipCode
    }

    /**
     * Method to set zip code
     *
     * @param zipCode zip code
     */
    fun setZipCode(zipCode: String) {
        this.zipCode = zipCode
    }

    /**
     * Method to get colony object
     *
     * @return colony object
     */
    fun getColony(): Colony? {
        return colony
    }

    /**
     * Method to set colony object
     *
     * @param colony colony object
     */
    fun setColony(colony: Colony) {
        this.colony = colony
    }

    /**
     * Method to get street value
     *
     * @return street value
     */
    fun getStreet(): String? {
        return street
    }

    /**
     * Method to set street value
     *
     * @param street street value
     */
    fun setStreet(street: String) {
        this.street = street
    }

    /**
     * Method to get interior number value
     *
     * @return interior number value
     */
    fun getInteriorNumber(): String? {
        return interiorNumber
    }

    /**
     * Method to set interior number value
     *
     * @param interiorNumber interior number value
     */
    fun setInteriorNumber(interiorNumber: String) {
        this.interiorNumber = interiorNumber
    }

    /**
     * Method to get exterior number value
     *
     * @return exterior number value
     */
    fun getExteriorNumber(): String? {
        return exteriorNumber
    }

    /**
     * Method to set exterior number value
     *
     * @param exteriorNumber exterior number value
     */
    fun setExteriorNumber(exteriorNumber: String) {
        this.exteriorNumber = exteriorNumber
    }

}