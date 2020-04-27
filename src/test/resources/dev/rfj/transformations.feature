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

  Scenario: A shearing transformation moves x in proportion to y
    Given T ← shearing(1, 0, 0, 0, 0, 0)
    And "p" <- point(2.0, 3.0, 4.0)
    Then T * p = point(5.0, 3.0, 4.0)

  Scenario: A shearing transformation moves x in proportion to z
    Given T ← shearing(0, 1, 0, 0, 0, 0)
    And "p" <- point(2.0, 3.0, 4.0)
    Then T * p = point(6.0, 3.0, 4.0)

  Scenario: A shearing transformation moves y in proportion to x
    Given T ← shearing(0, 0, 1, 0, 0, 0)
    And "p" <- point(2.0, 3.0, 4.0)
    Then T * p = point(2.0, 5.0, 4.0)

  Scenario: A shearing transformation moves y in proportion to z
    Given T ← shearing(0, 0, 0, 1, 0, 0)
    And "p" <- point(2.0, 3.0, 4.0)
    Then T * p = point(2.0, 7.0, 4.0)

  Scenario: A shearing transformation moves z in proportion to x
    Given T ← shearing(0, 0, 0, 0, 1, 0)
    And "p" <- point(2.0, 3.0, 4.0)
    Then T * p = point(2.0, 3.0, 6.0)

  Scenario: A shearing transformation moves z in proportion to y
    Given T ← shearing(0, 0, 0, 0, 0, 1)
    And "p" <- point(2.0, 3.0, 4.0)
    Then T * p = point(2.0, 3.0, 7.0)

  Scenario: Individual transformations are applied in sequence
    Given "p" <- point(1.0, 0.0, 1.0)
    # π / 2 = 1.57079632679
    And A ← rotation_x(1.57079632679)
    And B ← scaling(5, 5, 5)
    And C ← translation(10, 5, 7)
  # apply rotation first
    When p2 ← A * p
    Then p2 = point(1.0, -1.0, 0.0)
  # then apply scaling
    When p3 ← B * p2
    Then p3 = point(5.0, -5.0, 0.0)
  # then apply translation
    When p4 ← C * p3
    Then p4 = point(15.0, 0.0, 7.0)

  Scenario: Chained transformations must be applied in reverse order
    Given "p" <- point(1.0, 0.0, 1.0)
    # π / 2 = 1.57079632679
    And A ← rotation_x(1.57079632679)
    And B ← scaling(5, 5, 5)
    And C ← translation(10, 5, 7)
    When T ← C * B * A
    Then T * p = point(15.0, 0.0, 7.0)