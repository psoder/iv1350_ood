package util

import java.lang.Exception

/**
 * An exception that is thrown when a service isn't available.
 * 
 * @param message is the exception message.
 */
class NoSuchServiceException(message: String) : Exception(message) {}
