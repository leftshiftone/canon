package canon.exception

class CanonException : RuntimeException {

    constructor(message: String): super(message)

    constructor(ex: Exception): super(ex)
}
