package util

import kotlin.Exception

/**
 * En exception that is thrown when a service isn't available.
 */
class NoSuchServiceException(message: String) : Exception(message) {}
