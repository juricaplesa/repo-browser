package dev.plesa.domain.common

import java.lang.Exception

class IncompleteDataException(message: String = "Received data is incomplete. More results might have been found, but also might not"): Exception(message)

class MaximumRequestsException(message: String = "You have reached request count limit per minute"): Exception(message)

class NextRepositoriesNotFoundException(message: String = "There are no next paginated requests for repositories"): Exception(message)

