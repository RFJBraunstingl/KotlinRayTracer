package dev.rfj.domain.intersection

import java.util.*
import kotlin.collections.ArrayList

class IntersectionCollection(
        list: List<Intersection>
): ArrayList<Intersection>(list) {

    private var intersections: List<Intersection> = list.sortedBy { it.t }

    constructor(vararg intersections: Intersection) : this(listOf<Intersection>(*intersections))

    fun hit(): Optional<Intersection> {
        return intersections.stream()
                .filter { it.t >= 0 }
                .findFirst()
    }
}