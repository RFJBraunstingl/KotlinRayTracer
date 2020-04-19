package dev.rfj.domain.store.exception

class TupleNotFoundException(name: String = "unknown"): RuntimeException("Could not find Tuple with name $name")
