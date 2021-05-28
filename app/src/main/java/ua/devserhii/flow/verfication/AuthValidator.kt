package ua.devserhii.flow.verfication

import ua.devserhii.flow.verfication.exceptions.NameNotValidException
import ua.devserhii.flow.verfication.exceptions.PasswordNotValidException
import ua.devserhii.flow.verfication.exceptions.PhoneNotValidException
import javax.inject.Inject

class AuthValidator  @Inject constructor() {
    fun validatePhone(phone: String) {
        if (!phoneRegex.matches(phone)) throw PhoneNotValidException()
    }

    fun validatePassword(password: String) {
        if (!passwordRegex.matches(password)) throw PasswordNotValidException()
    }

    fun validateName(name: String) {
        if (!nameRegex.matches(name)) throw NameNotValidException()
    }

    private companion object {
        val passwordRegex = Regex("(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,}")
        val phoneRegex = Regex("^\\+380[5-9][0-9]\\d{7}\$")
        val nameRegex = Regex("^(\\p{L}|\\s){2,}\$")
    }
}
