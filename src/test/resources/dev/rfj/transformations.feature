Feature: Matrix Transformations

  Scenario: Multiplying by a translation matrix
    Given T ← translation(5, -3, 2)
    And "p" <- point(-3.0, 4.0, 5.0)
    Then T * p = point(2.0, 1.0, 7.0)

  Scenario: Multiplying by the inverse of a translation matrix
    Given T ← translation(5, -3, 2)
    And I ← inverse(T)
    And "p" <- point(-3.0, 4.0, 5.0)
    Then I * p = point(-8.0, 7.0, 3.0)

  Scenario: Translation does not affect vectors
    Given T ← translation(5, -3, 2)
    And "v" <- vector(-3.0, 4.0, 5.0)
    Then T * v = v

  Scenario: A scaling matrix applied to a point
    Given T ← scaling(2, 3, 4)
    And "p" <- point(-4.0, 6.0, 8.0)
    Then T * p = point(-8.0, 18.0, 32.0)

  Scenario: A scaling matrix applied to a vector
    Given T ← scaling(2, 3, 4)
    And "v" <- vector(-4.0, 6.0, 8.0)
    Then T * v = vector(-8.0, 18.0, 32.0)

  Scenario: Multiplying by the inverse of a scaling matrix
    Given T ← scaling(2, 3, 4)
    And I ← inverse(T)
    And "v" <- vector(-4.0, 6.0, 8.0)
    Then I * v = vector(-2.0, 2.0, 2.0)

  Scenario: Reflection is scaling by a negative value
    Given T ← scaling(-1, 1, 1)
    And "p" <- point(2.0, 3.0, 4.0)
    Then T * p = point(-2.0, 3.0, 4.0)

  Scenario: Rotating a point around the x axis
    Given "p" <- point(0.0, 1.0, 0.0)
    # π / 4 = 0.78539816339
    And HALF_QUARTER ← rotation_x(0.78539816339)
    # π / 2 = 1.57079632679
    And FULL_QUARTER ← rotation_x(1.57079632679)
    # √2/2 = 0.70710678118
    Then HALF_QUARTER * p = point(0.0, 0.70710678118, 0.70710678118)
    And FULL_QUARTER * p = point(0.0, 0.0, 1.0)

  Scenario: The inverse of an x-rotation rotates in the opposite direction
    Given "p" <- point(0.0, 1.0, 0.0)
    # π / 4 = 0.78539816339
    And HALF_QUARTER ← rotation_x(0.78539816339)
    And I ← inverse(HALF_QUARTER)
    # √2/2 = 0.70710678118
    Then I * p = point(0.0, 0.70710678118, -0.70710678118)

  Scenario: Rotating a point around the y axis
    Given "p" <- point(0.0, 0.0, 1.0)
    # π / 4 = 0.78539816339
    And HALF_QUARTER ← rotation_y(0.78539816339)
    # π / 2 = 1.57079632679
    And FULL_QUARTER ← rotation_y(1.57079632679)
    # √2/2 = 0.70710678118
    Then HALF_QUARTER * p = point(0.70710678118, 0.0, 0.70710678118)
    And FULL_QUARTER * p = point(1.0, 0.0, 0.0)

  Scenario: Rotating a point around the z axis
    Given "p" <- point(0.0, 1.0, 0.0)
    # π / 4 = 0.78539816339
    And HALF_QUARTER ← rotation_z(0.78539816339)
    # π / 2 = 1.57079632679
    And FULL_QUARTER ← rotation_z(1.57079632679)
    # √2/2 = 0.70710678118
    Then HALF_QUARTER * p = point(-0.70710678118, 0.70710678118, 0.0)
    And FULL_QUARTER * p = point(-1.0, 0.0, 0.0)
