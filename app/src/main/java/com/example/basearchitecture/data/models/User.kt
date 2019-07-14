package com.example.basearchitecture.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date

/**
 * User
 */
class User {

    /**
     * User id value
     */
    @SerializedName("idUsuario")
    @Expose
    private var userId: String? = null
    /**
     * Notification simulations value
     */
    @SerializedName("notificacionSimulaciones")
    @Expose
    private var notificationSimulations: Boolean = false
    /**
     * Cellphone object
     */
    @SerializedName("celular")
    @Expose
    private var cellphone: Cellphone? = null
    /**
     * Email value
     */
    @SerializedName("email")
    @Expose
    private var email: String? = null
    /**
     * Name value
     */
    @SerializedName("nombre")
    @Expose
    private var name: String? = null
    /**
     * Activation date value
     */
    @SerializedName("fechaActivacion")
    @Expose
    private var activationDate: Long? = null
    /**
     * Hash validation date
     */
    @SerializedName("fechaValidacionHash")
    @Expose
    private var hashValidationDate: Long? = null
    /**
     * Last login date
     */
    @SerializedName("fechaUltimoLogin")
    @Expose
    private var lastLoginDate: Long? = null
    /**
     * Born day value
     */
    @SerializedName("fechaNacimiento")
    @Expose
    private var bornDay: Long? = null
    /**
     * User status value
     */
    @SerializedName("estatusUsuario")
    @Expose
    private var userStatus: String? = null
    /**
     * Clipert client id
     */
    @SerializedName("idClienteClipert")
    @Expose
    private var clipertClientId: String? = null
    /**
     * Address object
     */
    @SerializedName("direccion")
    @Expose
    private var address: Address? = null
    /**
     * Drive smart enabled state
     */
    @SerializedName("driveSmartEnabled")
    @Expose
    private var driveSmartEnabled: Boolean = false
    /**
     * Last name value
     */
    @SerializedName("apellido")
    @Expose
    private var lastName: String? = null
    /**
     * Password value
     */
    @SerializedName("password")
    @Expose
    private var password: String? = null

    /**
     * Method to get user id value
     *
     * @return user id value
     */
    fun getUserId(): String? {
        return userId
    }

    /**
     * Method to set user id value
     *
     * @param user id value
     */
    fun setUserId(userId: String) {
        this.userId = userId
    }

    /**
     * Method to get notification simulations value
     *
     * @return notification simulations value
     */
    fun getNotificationSimulations(): Boolean {
        return notificationSimulations
    }

    /**
     * Method to set notification simulations value
     *
     * @param notificationSimulations notification simulations value
     */
    fun setNotificationSimulations(notificationSimulations: Boolean) {
        this.notificationSimulations = notificationSimulations
    }

    /**
     * Method to get cellphone object
     *
     * @return cellphone object
     */
    fun getCellphone(): Cellphone? {
        return cellphone
    }

    /**
     * Method to set cellphone object
     *
     * @param cellphone cellphone object
     */
    fun setCellphone(cellphone: Cellphone) {
        this.cellphone = cellphone
    }

    /**
     * Method to get email value
     */
    fun getEmail(): String? {
        return email
    }

    /**
     * Method to set email value
     */
    fun setEmail(email: String) {
        this.email = email
    }

    /**
     * Method to get name value
     *
     * @return name value
     */
    fun getName(): String? {
        return name
    }

    /**
     * Method to set name value
     *
     * @param name name value
     */
    fun setName(name: String) {
        this.name = name
    }

    /**
     * Method to get activation date value
     *
     * @return activation date value
     */
    fun getActivationDate(): Date {
        return Date(activationDate!!)
    }

    /**
     * Method to set activation date value
     *
     * @param activationDate activation date value
     */
    fun setActivationDate(activationDate: Long?) {
        this.activationDate = activationDate
    }

    /**
     * Method to get hash validation date
     *
     * @return hash validation date
     */
    fun getHashValidationDate(): Date {
        return Date(hashValidationDate!!)
    }

    /**
     * Method to set hash validation date
     *
     * @param hashValidationDate hash validation date
     */
    fun setHashValidationDate(hashValidationDate: Long?) {
        this.hashValidationDate = hashValidationDate
    }

    /**
     * Method to get last login date
     *
     * @return last login date
     */
    fun getLastLoginDate(): Date {
        return Date(lastLoginDate!!)
    }

    /**
     * Method to set last login date
     *
     * @param lastLoginDate last login date
     */
    fun setLastLoginDate(lastLoginDate: Long?) {
        this.lastLoginDate = lastLoginDate
    }

    /**
     * Method to get born day value
     *
     * @return born day value
     */
    fun getBornDay(): Date {
        return Date(bornDay!!)
    }

    /**
     * Method to set born day value
     *
     * @param bornDay born day value
     */
    fun setBornDay(bornDay: Long?) {
        this.bornDay = bornDay
    }

    /**
     * Method to get user status value
     *
     * @return user status value
     */
    fun getUserStatus(): String? {
        return userStatus
    }

    /**
     * Method to set user status value
     *
     * @param userStatus user status value
     */
    fun setUserStatus(userStatus: String) {
        this.userStatus = userStatus
    }

    /**
     * Method to get clipert client id
     *
     * @return clipert client id
     */
    fun getClipertClientId(): String? {
        return clipertClientId
    }

    /**
     * Method to set clipert client id
     *
     * @param clipertClientId clipert client id
     */
    fun setClipertClientId(clipertClientId: String) {
        this.clipertClientId = clipertClientId
    }

    /**
     * Method to get address object
     *
     * @return address object
     */
    fun getAddress(): Address? {
        return address
    }

    /**
     * Method to set address object
     *
     * @param address address object
     */
    fun setAddress(address: Address) {
        this.address = address
    }

    /**
     * Method to get drive smart enable state
     *
     * @return drive smart enable state
     */
    fun getDriveSmartEnabled(): Boolean {
        return driveSmartEnabled
    }

    /**
     * Method to set drive smart enable state
     *
     * @param driveSmartEnabled drive smart enable state
     */
    fun setDriveSmartEnabled(driveSmartEnabled: Boolean) {
        this.driveSmartEnabled = driveSmartEnabled
    }

    /**
     * Method to get last name value
     *
     * @return last name value
     */
    fun getLastName(): String? {
        return lastName
    }

    /**
     * Method to set last name value
     *
     * @param lastName last name value
     */
    fun setLastName(lastName: String) {
        this.lastName = lastName
    }

    /**
     * Method to get password value
     *
     * @return password value
     */
    fun getPassword(): String? {
        return password
    }

    /**
     * Method to set password value
     *
     * @param password password value
     */
    fun setPassword(password: String) {
        this.password = password
    }

}