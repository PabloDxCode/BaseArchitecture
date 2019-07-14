package com.example.basearchitecture.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Colony
 */
class Colony {

    /**
     * Neighborhood object
     */
    @SerializedName("barrio")
    @Expose
    private var neighborhood: InternalProperties? = null
    /**
     * Municipality object
     */
    @SerializedName("municipio")
    @Expose
    private var municipality: InternalProperties? = null
    /**
     * City object
     */
    @SerializedName("ciudad")
    @Expose
    private var city: InternalProperties? = null
    /**
     * State object
     */
    @SerializedName("estado")
    @Expose
    private var state: InternalProperties? = null

    /**
     * Method to get neighborhood object
     *
     * @return neighborhood object
     */
    fun getNeighborhood(): InternalProperties? {
        return neighborhood
    }

    /**
     * Method to set neighborhood object
     *
     * @param neighborhood neighborhood object
     */
    fun setNeighborhood(neighborhood: InternalProperties) {
        this.neighborhood = neighborhood
    }

    /**
     * Method to get municipality object
     *
     * @return municipality object
     */
    fun getMunicipality(): InternalProperties? {
        return municipality
    }

    /**
     * Method to set municipality object
     *
     * @param municipality municipality object
     */
    fun setMunicipality(municipality: InternalProperties) {
        this.municipality = municipality
    }

    /**
     * Method to get city object
     *
     * @return city object
     */
    fun getCity(): InternalProperties? {
        return city
    }

    /**
     * Method to set city object
     *
     * @param city city object
     */
    fun setCity(city: InternalProperties) {
        this.city = city
    }

    /**
     * Method to get state object
     *
     * @return state object
     */
    fun getState(): InternalProperties? {
        return state
    }

    /**
     * Method to set state object
     *
     * @return state object
     */
    fun setState(state: InternalProperties) {
        this.state = state
    }

}