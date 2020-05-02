Feature: Rays

  Scenario: Creating and querying a ray
    Given "origin" <- point(1.0, 2.0, 3.0)
    And "direction" <- vector(4.0, 5.0, 6.0)
    When r ← ray(origin, direction)
    Then r.origin = origin
    And r.direction = direction

  Scenario: Computing a point from a distance
    Given r ← ray(point(2.0, 3.0, 4.0), vector(1.0, 0.0, 0.0))
    Then position(r, 0) = point(2.0, 3.0, 4.0)
    And position(r, 1) = point(3.0, 3.0, 4.0)
    And position(r, -1) = point(1.0, 3.0, 4.0)
    And position(r, 2.5) = point(4.5, 3.0, 4.0)

  Scenario: Translating a ray
    Given r ← ray(point(1, 2, 3), vector(0, 1, 0))
    And M ← translation(3, 4, 5)
    When r2 ← transform(r, M)
    Then r2.origin = point(4, 6, 8)
    And r2.direction = vector(0, 1, 0)

  Scenario: Scaling a ray
    Given r ← ray(point(1, 2, 3), vector(0, 1, 0))
    And M ← scaling(2, 3, 4)
    When r2 ← transform(r, M)
    Then r2.origin = point(2, 6, 12)
    And r2.direction = vector(0, 3, 0)
