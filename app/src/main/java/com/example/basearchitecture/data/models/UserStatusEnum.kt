package com.example.basearchitecture.data.models

/**
 * UserStatusEnum
 */
enum class UserStatusEnum {

    PENDIENTE_ACTIVACION,
    ACTIVO,
    INEXISTENTE,
    INACTIVO,
    SIN_VALOR,
    BLOQUEADO;

    companion object {

        /**
         * Method to get status
         *
         * @param status status in string form
         *
         * @return this
         */
        fun getStatus(status: String): UserStatusEnum {
            for (userStatusEnum in UserStatusEnum.values()) {
                if (userStatusEnum.toString() == status) {
                    return userStatusEnum
                }
            }
            return SIN_VALOR
        }
    }

}