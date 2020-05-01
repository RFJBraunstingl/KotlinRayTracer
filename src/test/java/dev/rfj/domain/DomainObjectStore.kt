package dev.rfj.domain

import dev.rfj.domain.shapes.Sphere
import dev.rfj.matrix.Matrix

class MatrixMap: HashMap<String, Matrix>()

class RayMap: HashMap<String, Ray>()

class SphereMap: HashMap<String, Sphere>()

class IntersectionMap: HashMap<String, Intersection>()